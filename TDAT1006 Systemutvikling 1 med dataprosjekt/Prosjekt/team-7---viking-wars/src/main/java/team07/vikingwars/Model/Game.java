package team07.vikingwars.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.lang.*;
import team07.vikingwars.Controller.FXMLDocumentController;
import team07.vikingwars.Database.Database;
import team07.vikingwars.Model.MethodWrapping.MethodWrapper;

/**
 * Game simulation,
 * ticks every five seconds
 */

public class Game implements Runnable {

    Thread t;
    FXMLDocumentController controller;
    ArrayList<ArrayList<PreparedStatement>> outgoingQueries;
    ArrayList<MethodWrapper> eventsFromController;
    long tickTimer = 0;
    static final long tickRateInMillis = 5000;

    long millisLast = 0;
    long uptime = 0;

    boolean playerLoggedIn = false;
    boolean exitGame = false;
    boolean prepareExit = false;

    /**
     * Variables that will be resolved by local player, i/e battles in which we are
     * defending
     */
    private ArrayList<Integer> battles;

    /**
     * Index variables for database fetches (only fetch newer than counter)
     */
    private int battleIndex = 0;
    private int chatIndex = 0;
    private int playerIndex = 0;
    private int townIndex = 0;

    /**
     * Constructor takes in a object of FXMLDocumentController
     * @param controller
     */

    public Game(FXMLDocumentController controller) {
        this.controller = controller;
        outgoingQueries = new ArrayList<>();
        battles = new ArrayList<>();
        eventsFromController = new ArrayList<>();
    }

    /**
     * The game loop
     */
    @Override
    public void run() {
        try {
            initialize();
            while (!playerLoggedIn) {
                if (prepareExit) {
                    break;
                }
                Thread.sleep(1L);
            }

            // Guards for when user has not actually logged in i/e game is being shut down.
            if (!prepareExit) {
                // When players log in, call these functions before the actual gameloop
                while (eventsFromController.size() > 0) {
                    getControllerEvent().invoke();
                }
                updateUI();
                catchUp();

                millisLast = System.currentTimeMillis();
                while (!exitGame) {
                    // Adds time spent in the last iteration to our tickTimer
                    tickTimer += System.currentTimeMillis() - millisLast;
                    // Run events from controller
                    while (eventsFromController.size() > 0) {
                        getControllerEvent().invoke();
                    }

                    if (tickTimer >= tickRateInMillis) {
                        uptime += tickTimer;
                        tickTimer -= tickRateInMillis; // Keep remainder to prevent tick drift.
                        // Upload our move(s) to DB
                        ArrayList<PreparedStatement> queries = getQuery();
                        Database db = Database.instance();
                        try {
                            db.setAutoCommit(false);
                            if (queries != null) {
                                for (PreparedStatement stmt : queries) {
                                    if (stmt != null) {
                                        stmt.executeUpdate();
                                    }
                                }
                                db.commit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (queries != null) {
                                    for (PreparedStatement stmt : queries) {
                                        if (stmt != null) {
                                            stmt.close();
                                        }
                                    }
                                }
                                db.setAutoCommit(true);

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        //Per tick updates from local player
                        for (PreparedStatement stmt : controller.updatePlayerResources()) {
                            stmt.executeUpdate();
                            stmt.close();
                        }
                        //Poll database for more changes.
                        // Get battles where we are the defending town
                        fetchNewBattles();
                        // Check if our involved battles are completed
                        checkBattleReports();
                        // Get New Chat-messages
                        updateChat();

                        // controller.changeOwner();

                        // Update the town info
                        controller.updateInfoFromDB();

                        // Adds new players and their towns to the map for the user live
                        fetchNewPlayers();

                        // Fetch new towns
                        fetchNewTowns();

                        // Update view.
                        updateUI();
                        System.out.println("Game.Loop.Tick: " + uptime);
                    }
                    millisLast = System.currentTimeMillis();
                    Thread.sleep(1L);
                } // End while
            }
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the new towns from the database based on the current town index.
     */

    private void fetchNewTowns() {
        ArrayList<Town> newTowns = Database.instance().getTownsAfterIndex(townIndex);
        for (Town t : newTowns) {
            if (t.getTownId() > townIndex)
                townIndex = t.getTownId();
            controller.addTownToPlayer(t);
        }
    }

    /**
     * Updates the chat by getting new messages.
     */
    public void updateChat() {
        ResultSet s = Database.instance().readAfterIndex(chatIndex, controller.getLocalPlayerId());
        ArrayList<String> newMessages = new ArrayList<>();
        try {
            while (s.next()) {
                newMessages.add(controller.getPlayerById(s.getInt("user_id")).getName() + ": " + s.getString("chat"));
                chatIndex = s.getInt("message_id");
            }
            controller.runLater(new Runnable() {

                @Override
                public void run() {
                    controller.updateChat(newMessages);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the UI
     */
    private void updateUI() {
        controller.runLater(new Runnable() {
            @Override
            public void run() {
                controller.updateInfo();
            }
        });

    }

    /**
     * @return chatIndex
     */
    public int getChatIndex() {
        return chatIndex;
    }

    /**
     * Changes the chatIndex
     * @param chatIndex
     */
    public void setChatIndex(int chatIndex) {
        this.chatIndex = chatIndex;
    }

    /**
     * Checks for new battle reports in the database and generates notifications if any are found.
     */
    private void checkBattleReports() {
        ArrayList<Integer> battlesToRemove = new ArrayList<>();
        int[] localTowns = controller.getLocalPlayersTownIds();
        for (Integer i : battles) {
            String report = Database.instance().getBattleReport(i, localTowns);
            if (report != null) {
                battlesToRemove.add(i);
                controller.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.addNotification(new Notification(report));
                        controller.warningPane(report);
                    }
                });
            }
        }
        for (Integer i : battlesToRemove) {
            battles.remove(i);
        }
    }

    /**
     * Fetch new battles from the database
     */
    private void fetchNewBattles() {
        try {
            int largestNewId = 0;
            int[] ourTownIds = controller.getLocalPlayersTownIds();
            for (int i = 0; i < ourTownIds.length; i++) {
                PreparedStatement stmt = Database.instance().getPlayerBattlesAfterIndex(battleIndex, ourTownIds[i]);
                ResultSet result = stmt.executeQuery();
                while (result.next()) {
                    int attackId = result.getInt("attack_id");
                    int attackingTown = result.getInt("attackerTown");
                    int defendingTown = result.getInt("defenderTown");

                    // Add attackId to our list so we can check
                    battles.add(new Integer(attackId));

                    // Update our latest battleIndex
                    if (attackId > largestNewId)
                        largestNewId = attackId;

                    if (controller.townOwnedByLocalPlayer(defendingTown)) {
                        String notificationString = "";
                        controller.runLater(new Runnable() {

                            @Override
                            public void run() {
                                controller.warningPane(controller.getTownById(attackingTown).getName()
                                        + " is attacking " + controller.getTownById(defendingTown).getName());
                            }

                        });
                        notificationString += controller.getTownById(attackingTown).getName() + " is attacking "
                                + controller.getTownById(defendingTown).getName();
                        String effFinal = notificationString;
                        controller.runLater(new Runnable() {
                            @Override
                            public void run() {

                                controller.addNotification(new Notification(effFinal));
                            }
                        });
                    }
                }
            }
            if (largestNewId > battleIndex) {
                battleIndex = largestNewId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetch new players so that the players that are running the game gets the new players created.
     */
    private void fetchNewPlayers() {
        ArrayList<Player> newPlayer = Database.instance().getPlayers(playerIndex);
        for (Player p : newPlayer) {
            if (p.getId() > playerIndex)
                playerIndex = p.getId();
        }
        controller.runLater(new Runnable() {

            @Override
            public void run() {
                controller.addNewPlayers(newPlayer);
            }
        });
    }

    /**
     * Starts the game thread
     */
    public void start() {
        t = new Thread(this, "GameLoop Thread");
        t.start();
    }

    /**
     * CLoses the database connection.
     */
    public void stop() {
        System.out.println("Closing game");
        Database.instance().closeConnection();
    }

    /**
     * Prepares for exit by changing flags.
     */
    public void prepareExit() {
        prepareExit = true;
        exitGame = true;
        playerLoggedIn = true;
    }

    /**
     * Sets the player login flag to true.
     */
    public void playerLoggedIn() {
        playerLoggedIn = true;
    }

    /**
     * Adds queries to be executed the next tick
     * @param query
     */
    public void addQuery(ArrayList<PreparedStatement> query) {
        synchronized (outgoingQueries) {
            outgoingQueries.add(query);
        }
    }

    /**
     *  Helper method to remove the first element of the queue.
     * @return
     */
    private ArrayList<PreparedStatement> getQuery() {
        synchronized (outgoingQueries) {
            if (outgoingQueries.size() > 0)
                return outgoingQueries.remove(0);
            else
                return null;
        }
    }

    /**
     * Adds a method wrapper to be executed by this thread.
     * @param wrapper
     */
    public void addControllerEvent(MethodWrapper wrapper) {
        synchronized (eventsFromController) {
            eventsFromController.add(wrapper);
        }
    }

    /**
     * Helper method that retrieves the first element of the queue.
     * @return
     */
    private MethodWrapper getControllerEvent() {
        synchronized (eventsFromController) {
            if (eventsFromController.size() > 0)
                return eventsFromController.remove(0);
            else
                return null;
        }
    }

    // TODO: Change database to not set lastupdated whenver town is updated, only when logging off.

    /**
     * Used to generate resources when player has been offline. Called when user logs in.
     */

    public void catchUp() {
        int playerId = controller.getLocalPlayerId();
        int[] playerTowns = controller.getLocalPlayersTownIds();
        ArrayList<Timestamp> s = Database.instance().getTownLastUpdated(playerId);
        for (int i = 0; i < s.size(); i++) {
            long secondsSinceLastUpdate = (System.currentTimeMillis() - s.get(i).getTime()) / 1000;
            int ticksLost = (int) secondsSinceLastUpdate / 5;
            Town t = controller.getTownById(playerTowns[i]);
            int[] resourceGain = new int[3];
            ArrayList<Building> tBuildings = t.getBuildings();
            RaidersHut raidersHut = (RaidersHut) tBuildings.get(1);
            resourceGain[0] = raidersHut.getResourcePerTurn() * ticksLost;

            Ironmine ironmine = (Ironmine) tBuildings.get(3);
            resourceGain[1] = ironmine.getResourcePerTurn() * ticksLost;

            Woodcutter woodcutter = (Woodcutter) tBuildings.get(4);
            resourceGain[2] = woodcutter.getResourcePerTurn() * ticksLost;

            t.addResources(resourceGain);
            try {
                Database.instance().updateResources(resourceGain, t.getTownId()).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initialize the game by getting all the players from the database and all the towns for the players.
     */
    public void initialize() {
        System.out.println("Game Init start");
        Database db = Database.instance();
        // Get all players
        ArrayList<Player> players = db.getPlayers(-1);
        // Get towns per playerid
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() > playerIndex) {
                playerIndex = players.get(i).getId();
            }
            Player p = players.get(i);
            if (p != null) {
                ArrayList<Town> towns = db.getTownsPlayer(p.getId());
                for (int j = 0; j < towns.size(); j++) {
                    p.addTown(towns.get(j));
                }
            }
        }
        controller.setPlayers(players);
        System.out.println("Game Init end");
    }

    /**
     * Sets the player index to the newest player's id
     * @param newPlayerIndex
     */
    public void setPlayerIndex(int newPlayerIndex) {
        if (newPlayerIndex > playerIndex) {
            this.playerIndex = newPlayerIndex;
        }
    }
}