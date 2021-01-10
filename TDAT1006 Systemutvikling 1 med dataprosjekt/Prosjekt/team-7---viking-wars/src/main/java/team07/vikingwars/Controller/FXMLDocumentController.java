package team07.vikingwars.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import team07.vikingwars.Database.Database;
import team07.vikingwars.Database.Userhandling;
import team07.vikingwars.Model.*;
import team07.vikingwars.Model.MethodWrapping.MethodWrapper;
import javafx.scene.*;
import javafx.application.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * FXMLDocumentController is used for controlling the JavaFX UI All UI nodes and
 * methods for manipulating them are here.
 */
public class FXMLDocumentController implements Initializable {

    private Game game;
    private Userhandling userhandling = new Userhandling();
    private Player player;
    private ArrayList<Player> players;
    private Town currentSelectedTown;
    public static final int MAXGRIDX = 15;
    public static final int MAXGRIDY = 10;
    private ObservableList<Notification> notifications;
    private ObservableList<String> chat, towninfolist, attackOrTradeList;
    private ObservableList<Town> townlist;
    private ObservableList<AnchorPane> townhallupgradelist, unitlist;

    @FXML
    private AnchorPane Town_map, BarracksPane, TownHallPane, RaidingPane, AttackorTrade, LoginScreen, NewUserScreen,
            TownListPane, Leaderboard, LumberMillPane, IronMinePane, FarmPane, anchorpaneAllBuildings, RenameTownPane,
            TutorialPane, GameOverPane, WarningPane;
    @FXML
    private Text AttackorTradeTitle, FarmText, IronMineText, LumberMillText, BarracksInfoText, TownHallInfoText,
            RaidingInfoText, TownNameText, warninginfotext;
    @FXML
    private ListView AttackorTradeList, TownInfoList, ListviewTownHall, ListviewBarracks, Townlist_list, chatListView;
    @FXML
    private TextField LogInUsername, CreateUserUsername, RenameTownTextField, chatTextField, BarracksTextField;
    @FXML
    private PasswordField LogInPassword, CreateUserPassword1, CreateUserPassword2;
    @FXML
    private ListView<Notification> Notification_list;
    @FXML
    private ListView<Player> Leaderboard_list;
    @FXML
    private GridPane AttackorTradeGrid;
    @FXML
    private Button AttackEnemyTownBtn;
    @FXML
    private ListView<Town> Town_list;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller.
     * Makes sure the right JavaFX anchorpanes are visible.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NewUserScreen.setVisible(false);
        Leaderboard.setVisible(false);
        RenameTownPane.setVisible(false);
        TownHallPane.setVisible(false);
        BarracksPane.setVisible(false);
        IronMinePane.setVisible(false);
        LumberMillPane.setVisible(false);
        RaidingPane.setVisible(false);
        FarmPane.setVisible(false);
        WarningPane.setVisible(false);
        TownListPane.setVisible(false);
        GameOverPane.setVisible(false);
        TutorialPane.setVisible(false);

        Town_map.setVisible(true);
        LoginScreen.setVisible(true);
    }

    /**
     * For initializing chat on startup.
     */
    public void initializeChat() {
        Database db = Database.instance();
        MethodWrapper a = new MethodWrapper() {

            @Override
            public void invoke() {
                ArrayList<String> chatMsgs = new ArrayList<>();
                ResultSet read = db.read();
                try {
                    while (read.next()) {
                        chatMsgs.add(getPlayerById(read.getInt("user_id")).getName() + ": \n" + read.getString("chat"));
                        if (game.getChatIndex() < read.getInt("message_id")) {
                            game.setChatIndex(read.getInt("message_id"));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                runLater(new Runnable() {
                    @Override
                    public void run() {
                        chat.addAll(chatMsgs);
                        chatListView.setItems(chat);
                        chatListView.scrollTo(chat.size() - 1);
                    }

                });
            }
        };
        game.addControllerEvent(a);
    }

    /**
     * @param newMessages
     */
    public void updateChat(ArrayList<String> newMessages) {
        chat.addAll(newMessages);
        chatListView.setItems(chat);
        chatListView.scrollTo(chat.size() - 1);
    }

    public int getLocalPlayerId() {

        return player.getId();
    }

    /**
     * Sends a chat message
     *
     * @param ae
     */
    @FXML
    public void onEnter(ActionEvent ae) {
        String message = chatTextField.getText();
        if (message.length() > 0) {
            chatListView.scrollTo(chat.size() - 1);
            chatTextField.setText("");
            chat.add(player.getName() + ":\n" + message);
            game.addControllerEvent(new MethodWrapper() {
                @Override
                public void invoke() {
                    Database db = Database.instance();
                    db.insert(message, player.getId()); // add to chat
                    game.updateChat();
                }
            });
        }
    }

    /**
     * addPlayer() Adds a Player object to the ArrayList(Player) players
     *
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Create a button and place it in the grid in map
     *
     * @param t the Town object to reference by the button, also contains the x and
     *          y position for the grid cell
     */
    public void addTownToUI(Town t) {
        Button b = new Button();
        b.setPrefSize(100, 100);
        b.getStyleClass().add("townbuttons");
        b.setText(t.getName());

        if (player.getTowns().contains(t)) {
            b.setStyle("-fx-background-image: url('https://drive.google.com/uc?id=1ju9u7T0O180C3ub8h6RAjT0gO9tbW7nb')");
            b.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    currentSelectedTown = t;
                    TownNameText.setText(currentSelectedTown.getName());
                    updateInfo();
                    TownClicked();
                }
            });
        } else {
            attackOrTrade(t, b);
        }
        for (Node n : grid.getChildren()) {
            if (grid.getColumnIndex(n) == t.getX() && grid.getRowIndex(n) == t.getY()) {
                grid.getChildren().remove(n);
                break;
            }
        }
        grid.add(b, t.getX(), t.getY());
    }

    /**
     * Method for creating AttackOrTradePane
     */
    public void attackOrTrade(Town t, Button b) {
        b.setStyle("-fx-background-image: url('https://drive.google.com/uc?id=1eA1jtm0oe7xhfCbzGD-wqKSTFADBDzYY')");
        b.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // temporary start
                Player p = getPlayerById(t.getOwner());
                ArrayList<Town> pTowns = p.getTowns();

                // temporary end
                attackOrTradeList.setAll("Player: " + p.getName(), "Town score: " + t.getScore());
                AttackorTradeList.setItems(attackOrTradeList);
                AttackorTradeTitle.setText(t.getName());
                /**
                 * Set the "Attack" Button to make a battle between <currently selected town>?
                 * and the last clicked enemy town
                 */
                AttackEnemyTownBtn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        int ticks = 1 + (int) sqrt((pow(t.getX() - currentSelectedTown.getX(), 2)
                                + pow(t.getY() - currentSelectedTown.getY(), 2)));

                        ArrayList<Unit> units = currentSelectedTown.getUnits();

                        int[] change = new int[units.size()];

                        int amount = 0;

                        for (int i = 0; i < units.size(); i++) {
                            change[i] = -units.get(i).getTroopCount();
                            amount += units.get(i).getTroopCount();
                        }
                        if (amount != 0) {
                            attack(currentSelectedTown.getTownId(), units, t.getTownId(), ticks, change);
                            ArrayList<Unit> temp = new ArrayList<>();
                            temp.add(new Spearman(0));
                            temp.add(new Archer(0));
                            temp.add(new Cavalry(0));

                            currentSelectedTown.setUnits(temp);
                            warningPane("Your town " + currentSelectedTown.getName() + " is attacking " + p.getName()
                                    + "'s town " + t.getName() + ".\nYour attack will arrive their town in ETA: "
                                    + ticks * 5 + " seconds.");
                            WarningPane.setVisible(true);
                            AttackorTrade.setVisible(false);
                            AttackorTradeGrid.setVisible((false));
                            addNotification(new Notification("Your town " + currentSelectedTown.getName()
                                    + " is attacking " + p.getName() + "'s town " + t.getName()
                                    + ".\nYour attack will arrive their town in ETA: " + ticks * 5 + " seconds."));
                        } else {
                            warningPane("You can not attack with 0 troops, go to the barracks to train some");
                        }
                    }

                });
                if (AttackorTrade.isVisible()) {
                    AttackorTrade.setVisible(false);
                    AttackorTradeGrid.setVisible((false));
                } else {
                    AttackorTrade.setVisible(true);
                    AttackorTradeGrid.setVisible(true);
                }
            }
        });
    }

    /**
     * @param playerId
     * @return
     */
    public Player getPlayerById(int playerId) {
        for (Player p : players) {
            if (p.getId() == playerId)
                return p;
        }
        return null;
    }

    /**
     * Used to manipulate the JavaFX window from threads other than the JavaFX
     * Application Thread
     *
     * @param runThis the Runnable to invoke
     */
    public void runLater(Runnable runThis) {
        Platform.runLater(runThis);
    }

    /**
     * Displays the global map
     */
    public void Map() { // add updates to map here
        Town_map.setVisible(false);
        TownHallPane.setVisible(false);
        BarracksPane.setVisible(false);
        RaidingPane.setVisible(false);
        TownListPane.setVisible(false);
        Leaderboard.setVisible(false);
        FarmPane.setVisible(false);
        LumberMillPane.setVisible(false);
        IronMinePane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Adds a button for upgrading buildings
     *
     * @param building
     */
    public void upgradeBuildingButton(Building building) {
        ArrayList<PreparedStatement> stmts = new ArrayList<>();

        ArrayList<Resource> resources = currentSelectedTown.getResources();

        int silverCost = building.getSilverCost();
        int ironCost = building.getIroncost();
        int woodCost = building.getWoodcost();
        int townId = currentSelectedTown.getTownId();

        if (resources.get(0).getAmount() >= silverCost && resources.get(1).getAmount() >= ironCost
                && resources.get(2).getAmount() >= woodCost && building.getHiddenLevel() < 20) {

            resources.get(0).addAmount(-silverCost);
            resources.get(1).addAmount(-ironCost);
            resources.get(2).addAmount(-woodCost);

            building.upgrade();
            int[] queryResources = { -silverCost, -ironCost, -woodCost };
            stmts.add(Database.instance().updateResources(queryResources, townId));
            stmts.add((Database.instance().updateBuilding(building, townId)));
            game.addQuery(stmts);
            addNotification(new Notification(
                    "Started upgrading " + building.getName() + " to level " + building.getHiddenLevel() + "."));
            warningPane("Started upgrading " + building.getName() + " to level " + building.getHiddenLevel() + ".");
        } else {
            if (building.getHiddenLevel() == 20) {
                warningPane("This building is already at the max level.");
            } else {
                warningPane("Not enough resources.");
            }
        }
    }

    /**
     * Adds options and panes for the barracks building
     */
    public void Barracks() { // add db-updates to barracks pane
        if (!BarracksPane.isVisible()) {
            anchorpaneAllBuildings.setVisible(true);
            BarracksPane.setVisible(true);

            /**
             * Create arrayslists for flexible initilization
             */
            unitlist = FXCollections.observableArrayList();
            ArrayList<Text> texts = new ArrayList<>();
            ArrayList<Button> buttons = new ArrayList<>();
            ArrayList<AnchorPane> panes = new ArrayList<>();
            ArrayList<Unit> units = currentSelectedTown.getUnits();

            /**
             * For each unit add a button and description in barracks for training
             */
            Building barracks = currentSelectedTown.getBuildings().get(0);
            int barrackslevel = barracks.getLevel();
            for (Unit unit : units) {
                Button b;
                if ((barrackslevel < 5 && (unit instanceof Archer))
                        || (barrackslevel < 10 && (unit instanceof Cavalry))) {
                    texts.add(new Text("Locked unit, level up barracks"));
                    b = new Button("Locked");
                } else {
                    texts.add(new Text(unit.toString()));
                    b = new Button("Train");
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                /**
                                 * if you want to train 0 or negative units, return
                                 */
                                Integer amount = Integer.valueOf(BarracksTextField.getText());

                                if (amount < 1) {
                                    return;
                                }
                                /**
                                 * call train method
                                 */
                                train(unit, amount);
                            } catch (Exception f) {
                                f.printStackTrace();
                                warningPane("Type in a number");
                            }
                        }
                    });
                }
                buttons.add(b);
                panes.add(new AnchorPane());
            }
            /**
             * For each unit add an anchorpane to listview with necessary info
             */
            for (int i = 0; i < buttons.size(); i++) {
                panes.get(i).getChildren().addAll(buttons.get(i), texts.get(i));
                panes.get(i).setRightAnchor(buttons.get(i), 5.0);
                panes.get(i).setBottomAnchor(texts.get(i), 5.0);
            }
            unitlist.addAll(panes);
            ListviewBarracks.setItems(unitlist);

        } else {
            xBarracks();
        }
    }

    /**
     * Adds a button for training troops of a certain type of unit
     * @param unit
     * @param n
     * @return boolean
     */
    public boolean train(Unit unit, int n) {

        /**
         * Checks if town has enough capacity, if not return false
         */
        if (!currentSelectedTown.canTrain(n)) {
            warningPane("Not enough capacity. Upgrade Farm first.");
            return false;
        }

        /**
         * Fetch info
         */
        int[] change = new int[3];
        change[unit.getIndex()] = n;
        int[] price = unit.getPrice();

        /**
         * Update price with respect to amount trained
         */
        for (int l = 0; l < change.length; l++) {
            price[l] = price[l] * n;
        }

        ArrayList<Resource> resources = currentSelectedTown.getResources();
        ArrayList<PreparedStatement> stmts = new ArrayList<>();

        /**
         * Checks if town has required resources, if not return false If it has enough,
         * local resources are updated
         */
        for (int i = 0; i < price.length; i++) {
            if (price[i] > resources.get(i).getAmount()) {
                warningPane("Not enough resources");
                return false;
            }
            resources.get(i).addAmount(-price[i]);
        }

        /**
         * sets new unit count
         */
        ArrayList<Unit> units = currentSelectedTown.getUnits();
        addNotification(new Notification("Started training " + n + " " + unit.getName()));
        warningPane("Started training " + n + " " + unit.getName());

        /**
         * database queries
         */
        for (int j = 0; j < price.length; j++) {
            if (price[j] > 0) {
                price[j] = -price[j];
            }
        }
        int townId = currentSelectedTown.getTownId();
        stmts.add(Database.instance().updateResources(price, townId));
        stmts.add(Database.instance().updateUnits(change, townId));
        game.addQuery(stmts);

        return true;
    }

    /**
     * Upgrades barracks.
     */
    public void upgradeBarracks() {
        upgradeBuildingButton(currentSelectedTown.getBuildings().get(0));
    }

    /**
     * Closes barracks and returns to town overview.
     */
    public void xBarracks() {
        BarracksPane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Adds options and panes for the town hall building.
     */
    public void TownHall() { // add db-updates to TownHall pane
        if (!TownHallPane.isVisible()) {
            anchorpaneAllBuildings.setVisible(true);
            TownHallPane.setVisible(true);
            BarracksPane.setVisible(false);
            RaidingPane.setVisible(false);
            updateTownHallContent();

        } else {
            xTownHall();
        }
    }

    /**
     * This method updates the info in the town hall overview.
     */
    public void updateTownHallContent() {
        ArrayList<Building> townbuildings = currentSelectedTown.getBuildings();
        ArrayList<Text> texts = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();
        ArrayList<AnchorPane> panes = new ArrayList<>();

        /**
         * Adds building description to texts
         */
        for (Building building : townbuildings) {
            texts.add(new Text(building.toString()));
        }
        /**
         * Adds button with upgrade method for all buildings
         */
        for (Building building : townbuildings) {
            Button b = new Button("Upgrade");
            b.setMinWidth(180);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    upgradeBuildingButton(building);
                    updateInfo();
                    xTownHall();
                    TownHall();
                }
            });
            buttons.add(b);
            panes.add(new AnchorPane());
        }
        /**
         * Adds a pane for each building
         */
        for (int i = 0; i < townbuildings.size(); i++) {
            panes.get(i).getChildren().addAll(buttons.get(i), texts.get(i));
            panes.get(i).setBottomAnchor(texts.get(i), 5.0);
            panes.get(i).setRightAnchor(buttons.get(i), 5.0);
        }
        /**
         * Adds observable list to listview
         */
        townhallupgradelist = FXCollections.observableArrayList();
        for (AnchorPane pane : panes) {
            townhallupgradelist.add(pane);
        }
        ListviewTownHall.setItems(townhallupgradelist);
    }

    /**
     * Closes town hall and returns to town overview.
     */
    public void xTownHall() {
        TownHallPane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Adds options and panes for the raiding.
     */
    public void Raiding() { // add db-updates to Raiding pane
        if (!RaidingPane.isVisible()) {
            anchorpaneAllBuildings.setVisible(true);
            RaidingPane.setVisible(true);
            BarracksPane.setVisible(false);
            TownHallPane.setVisible(false);
        } else {
            xRaiding();
        }
    }

    /**
     * Closes the rading and returns to town overview.
     */
    public void xRaiding() {
        RaidingPane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Upgrades the raiding.
     */
    public void upgradeRaidingHut() {
        upgradeBuildingButton(currentSelectedTown.getBuildings().get(1));
    }

    /**
     * For displaying town screen after you click on a town.
     */
    public void TownClicked() {
        Leaderboard.setVisible(false);
        TownListPane.setVisible(false);
        Town_map.setVisible(true);
    }

    /**
     * For attacking an enemy town.
     * @param attackId
     * @param attUnits
     * @param defendId
     * @param ticksBeforeAttack
     */
    public void attack(int attackId, ArrayList<Unit> attUnits, int defendId, int ticksBeforeAttack, int[] change) {
        game.addControllerEvent(new MethodWrapper() {
            @Override
            public void invoke() {
                Database.instance().insertBattle(attackId, attUnits, defendId, ticksBeforeAttack);
                try {
                    Database.instance().updateUnits(change, attackId).executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * For adding a notification to the notification list.
     * @param not
     */
    public void addNotification(Notification not) {
        notifications.add(not);
    }

    /**
     * Closes the "Attack or trade" pane.
     */
    public void xAttackorTrade() {
        AttackorTrade.setVisible(false);
        AttackorTradeGrid.setVisible((false));
    }

    /**
     * Adds a logout-button.
     */
    public void LogOutButton() {
        Map();
        AttackorTrade.setVisible(false);
        AttackorTradeGrid.setVisible(false);
        Town_map.setVisible(true);
        LoginScreen.setVisible(true);
    }

    /**
     * Adds a login-button and all its features.
     */
    public void LogInButton() {
        String password = LogInPassword.getText();
        String username = LogInUsername.getText();

        if (userhandling.login(username, password)) {
            townlist = FXCollections.observableArrayList();
            notifications = FXCollections.observableArrayList();
            attackOrTradeList = FXCollections.observableArrayList();

            chat = FXCollections.observableArrayList();
            chat.add("Chat:");
            chatListView.setItems(chat);

            AttackorTradeList.setItems(attackOrTradeList);
            Notification_list.setItems(notifications); // Changed for issue #40
            addNotification(new Notification("Notifications list: "));
            // Set current player
            for (int i = 0; i < players.size(); i++) {

                if (players.get(i).getName().toLowerCase().equals(username.toLowerCase())) {
                    player = players.get(i);
                    game.playerLoggedIn();
                    // Player has 0 towns
                    if (player.getTowns().size() == 0) {
                        GameOverPane.setVisible(true);
                        LoginScreen.setVisible(false);
                        return;
                    }
                    // Create towns
                    LoginScreen.setVisible(false);
                    onLogin();
                    return;
                }
            }
            LoginScreen.setVisible(false);
        } else {
            warningPane("Wrong username or password. Try again.");
        }
    }

    /**
     * Populates the map with players towns. Requires that the players and towns.
     * have already been fetched from database.
     */
    public void onLogin() {
        /**
         * adds all towns in databse to ui
         */
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Town> towns = players.get(i).getTowns();
            for (int j = 0; j < towns.size(); j++) {
                Town t = towns.get(j);
                addTownToUI(t);
            }
        }

        initializeChat();

        Town_list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        townlist = FXCollections.observableArrayList(player.getTowns());
        Town_list.setItems(townlist);
        Town_list.getSelectionModel().select(0);
        Town_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentSelectedTown = Town_list.getSelectionModel().getSelectedItem();
                TownNameText.setText(currentSelectedTown.getName());
                TownClicked();
                updateInfo();
            }
        });
        currentSelectedTown = Town_list.getSelectionModel().getSelectedItem();
        TownNameText.setText(currentSelectedTown.getName());
        updateInfo();
        LogInUsername.setText("");
        LogInPassword.setText("");
    }

    /**
     * Sets the screen where you can create a new user.
     */
    public void CreateUserScreen() {
        NewUserScreen.setVisible(true);
    }

    /**
     * for creating new user.
     */
    public void CreateNewUser() {
        String username = CreateUserUsername.getText();
        String password1 = CreateUserPassword1.getText();
        String password2 = CreateUserPassword2.getText();

        if (userhandling.newUser(username, password1, password2)) {
            NewUserScreen.setVisible(false);

            Player p = userhandling.getNewUserFromDB(username);
            // Fetches the new Player object and adds it to players (ArrayList<Player>)
            this.addPlayer(p);
            game.setPlayerIndex(p.getId());
            if(!Database.instance().insertTown("Default town", p.getId())) {
                warningPane("The map is full you can not create another town");
                return;
            }
            // Fetches the new player's towns and adds them to an ArrayList
            ArrayList<Town> playerTowns = userhandling.getTownsPlayerFromDB(p.getId());
            // Adds the fetched towns to "towns" in the new Player object
            for (int i = 0; i < playerTowns.size(); i++) {
                p.addTown(playerTowns.get(i));
            }
            LogInPassword.setText(password1);
            LogInUsername.setText(username);
            LogInButton();
            CreateUserUsername.setText("");
            CreateUserPassword1.setText("");
            CreateUserPassword2.setText("");
            NewUserScreen.setVisible(false);
            TutorialPane.setVisible(true);
            // Updates text field in "rename" window
            RenameTownTextField.setText(currentSelectedTown.getName());
            // Opens the "rename" window for the first town
            RenameTownPane.setVisible(true);
        } else {
            warningPane("Couldn't create user. \n Please make sure your passwords match, your username is between " +
                    "3 and 16 characters long and the username isn't taken.");
        }
    }

    /**
     * checks if input username already exists.
     */
    public void CheckUsername() {
        if (userhandling.getUsernames().contains(CreateUserUsername.getText())) {
            warningPane("Username already used");
        } else {
            warningPane("Username ok");
        }
    }

    /**
     * Sets the connection between the game and the controller.
     *
     * @param ref
     */
    public void setGameRef(Game ref) {
        game = ref;
    }

    /**
     * Displays the town list.
     */
    public void TownList() {
        TownListPane.setVisible(true);
        Leaderboard.setVisible(false);
    }

    /**
     * Closes the town list.
     */
    public void xTownList() {
        TownListPane.setVisible(false);
    }

    /**
     * Adds a leaderboard.
     */
    public void Leaderboard() {
        Leaderboard.setVisible(true);
        ArrayList<Player> sortedPlayersByScore = (ArrayList<Player>) players.clone();
        Collections.sort(sortedPlayersByScore);
        Leaderboard_list.getItems().setAll(sortedPlayersByScore);
        TownListPane.setVisible(false);
    }

    /**
     * Closes the leaderboard.
     */
    public void xLeaderboard() {
        Leaderboard.setVisible(false);
    }

    /**
     * @param players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Cancel button for Create new user screen.
     */
    public void canceluser() {
        NewUserScreen.setVisible(false);
    }

    /**
     * Returns all town id's for local player.
     * @return townIds
     */
    public int[] getLocalPlayersTownIds() {
        ArrayList<Town> towns = player.getTowns();
        int[] townIds = new int[towns.size()];
        for (int i = 0; i < townIds.length; i++) {
            townIds[i] = towns.get(i).getTownId();
        }
        return townIds;
    }

    /**
     * get town with townId.
     * @return town
     */
    public Town getTownById(int townId) {
        for (Player p : players) {
            for (Town t : p.getTowns()) {
                if (t.getTownId() == townId)
                    return t;
            }
        }
        return null;
    }

    /**
     * Per tick functions called by Game.
     */
    public ArrayList<PreparedStatement> updatePlayerResources() {
        ArrayList<PreparedStatement> updates = new ArrayList<>();
        ArrayList<Town> towns = player.getTowns();

        int[] resourceGain;

        for (Town t : towns) {
            resourceGain = new int[3];

            /**
             * All the resource per turn values are stored in a int[] list which is sent to
             * databse handler
             */
            RaidersHut raidersHut = (RaidersHut) t.getBuildings().get(1);
            resourceGain[0] = raidersHut.getResourcePerTurn();

            Ironmine ironmine = (Ironmine) t.getBuildings().get(3);
            resourceGain[1] = ironmine.getResourcePerTurn();

            Woodcutter woodcutter = (Woodcutter) t.getBuildings().get(4);
            resourceGain[2] = woodcutter.getResourcePerTurn();

            t.addResources(resourceGain);
            updates.add(Database.instance().updateResources(resourceGain, t.getTownId()));
        }
        return updates;
    }

    /**
     * For updating all UI information.
     * This method is called when you upgrade buildings or change town.
     */
    public void updateInfo() {
        /**
         * Add all towns and updates to map
         */
        for (Player p : players) {
            for (Town t : p.getTowns()) {
                // Update player score
                p.updateScore();
                addTownToUI(t);
            }
        }
        /**
         * upper right listview for changing between towns.
         */
        if (player.getTowns().size() != 0) {
            townlist.setAll(player.getTowns());
            Town_list.setItems(townlist);
        }
        /**
         * For changing town view if loosing currentselectedtown
         */
        if (!player.getTowns().contains(currentSelectedTown) && (player.getTowns().size() > 0)) {
            Town_map.setVisible(false);
            currentSelectedTown = player.getTowns().get(0);
            TownNameText.setText(currentSelectedTown.getName());
        }
        /**
         * update lower right info panel
         */
        if (currentSelectedTown != null) {
            towninfolist = FXCollections.observableArrayList();
            towninfolist.add("Town: " + currentSelectedTown.getName());

            ArrayList<Resource> updateresources = currentSelectedTown.getResources();
            for (Resource resource : updateresources) {
                towninfolist.add(resource.getName() + ": " + resource.getAmount());
            }
            ArrayList<Building> updatebuildings = currentSelectedTown.getBuildings();
            for (Building building : updatebuildings) {
                towninfolist.add(building.getName() + ": " + building.getLevel());
            }
            ArrayList<Unit> updateunits = currentSelectedTown.getUnits();
            for (Unit unit : updateunits) {
                towninfolist.add(unit.getName() + ": " + unit.getTroopCount());
            }
            towninfolist.add(
                    "Capacity: " + currentSelectedTown.getTroopCount() + " / " + currentSelectedTown.getCapacity());
            TownInfoList.setItems(towninfolist);

            /**
             * building descriptions
             */
            try {
                List<Text> textList = Arrays.asList(BarracksInfoText, RaidingInfoText, TownHallInfoText, IronMineText,
                        LumberMillText, FarmText);
                ArrayList<Text> infoText = new ArrayList<>();
                infoText.addAll(textList);

                for (int i = 0; i < infoText.size(); i++) {
                    textList.get(i).setText(
                            updatebuildings.get(i).getDescription() + " \n" + updatebuildings.get(i).getInfo());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * Town list pane info
             */
            ObservableList<String> townlistpaneinfo = FXCollections.observableArrayList();
            for (Town town : townlist) {
                townlistpaneinfo.add(town.getInfo());
            }
            Townlist_list.setItems(townlistpaneinfo);

            updateTownHallContent();

            // Update leaderboard
            ArrayList<Player> sortedPlayersByScore = (ArrayList<Player>) players.clone();
            Collections.sort(sortedPlayersByScore);
            Leaderboard_list.getItems().setAll(sortedPlayersByScore);
        }
    }

    /**
     * A warning pane that displays information on the screen.
     * @param text
     */
    public void warningPane(String text) {
        if(WarningPane.isVisible()){
            warninginfotext.setText("");
        }
        warninginfotext.setText(text);
        WarningPane.setVisible(true);
    }

    /**
     * Adds an OK button to the warning pane.
     */
    public void warningPaneOkButton() {
        WarningPane.setVisible(false);
        warninginfotext.setText("");
    }

    /**
     * Adds a button for the iron mine.
     */
    public void ironMineButton() {
        anchorpaneAllBuildings.setVisible(true);
        IronMinePane.setVisible(true);
    }

    /**
     * Closes the iron mine.
     */
    public void xIronMine() {
        IronMinePane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Adds a button for the lumber mill.
     */
    public void lumberMillButton() {
        LumberMillPane.setVisible(true);
        anchorpaneAllBuildings.setVisible(true);
    }

    /**
     * Closes the lumber mill.
     */
    public void xLumberMill() {
        LumberMillPane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Adds a button for the farm.
     */
    public void farmButton() {
        FarmPane.setVisible(true);
        anchorpaneAllBuildings.setVisible(true);
    }

    /**
     * Closes the farm.
     */
    public void xFarm() {
        FarmPane.setVisible(false);
        anchorpaneAllBuildings.setVisible(false);
    }

    /**
     * Upgrades the iron mine.
     */
    public void upgradeIronMine() {
        upgradeBuildingButton(currentSelectedTown.getBuildings().get(3));
    }

    /**
     * Upgrades the lumber mill.
     */
    public void upgradeLumberMill() {
        upgradeBuildingButton(currentSelectedTown.getBuildings().get(4));
    }

    /**
     * Upgrades the farm.
     */
    public void upgradeFarm() {
        upgradeBuildingButton(currentSelectedTown.getBuildings().get(5));
    }

    /**
     * Opens the rename town input display.
     */
    public void renameTownSelected() {
        RenameTownTextField.setText(currentSelectedTown.getName());
        if (RenameTownPane.isVisible()) {
            RenameTownPane.setVisible(false);
        } else {
            RenameTownPane.setVisible(true);
        }
    }

    /**
     * For renaming the current selected town.
     */
    public void renameTown() {
        currentSelectedTown.setName(RenameTownTextField.getText());
        Database.instance().updateTownName(RenameTownTextField.getText(), currentSelectedTown.getTownId());
        TownNameText.setText(currentSelectedTown.getName());
        RenameTownPane.setVisible(false);
    }

    /**
     * Gets updated information from database *Could be improved in conjunction with
     * changeOwner
     */
    public void updateInfoFromDB() {
        ArrayList<Player> removeTownsPLayer = new ArrayList<>();
        ArrayList<Player> addTownsPLayer = new ArrayList<>();
        ArrayList<Town> townsToRemove = new ArrayList<>();
        ArrayList<Town> townsToAdd = new ArrayList<>();
        ArrayList<Town> allTownsFromDB = Database.instance().getAllTowns();

        for (Player player : players) {
            for (Town oldTown : player.getTowns()) {
                for (Town newTown : allTownsFromDB) {
                    if (oldTown.getOwner() == newTown.getOwner() && oldTown.getTownId() == newTown.getTownId()) {
                        oldTown.setUnits(newTown.getUnits());
                        ArrayList<Building> buildings = oldTown.getBuildings();
                        ArrayList<Building> newBuildings = newTown.getBuildings();
                        for (int i = 0; i < buildings.size(); i++) {
                            buildings.get(i).setLevel(newBuildings.get(i).getLevel());
                        }
                        oldTown.setName(newTown.getName());
                    } else if (oldTown.getOwner() != newTown.getOwner() && oldTown.getTownId() == newTown.getTownId()) {
                        removeTownsPLayer.add(player);
                        townsToRemove.add(oldTown);
                        for (Player newOwner : players) {
                            if (newOwner.getId() == newTown.getOwner()) {
                                addTownsPLayer.add(newOwner);
                                townsToAdd.add(newTown);
                            }
                        }
                    }
                }
            }
        }
        changeOwner(removeTownsPLayer, addTownsPLayer, townsToRemove, townsToAdd);
    }

    /**
     * Changes town owner if it was taken over
     */
    public void changeOwner(ArrayList<Player> removeTownsPLayer, ArrayList<Player> addTownsPLayer,
            ArrayList<Town> townsToRemove, ArrayList<Town> townsToAdd) {

        for (Player losingPlayer : removeTownsPLayer) {
            for (Town removeTown : townsToRemove) {
                if (losingPlayer.getId() == removeTown.getOwner()) {
                    for (Player lPlayers : players) {
                        if (lPlayers.getId() == losingPlayer.getId()) {

                            if(townOwnedByLocalPlayer(removeTown.getTownId())) {
                                warningPane("Enemies have captured " + removeTown.getName() + "!");
                            }

                            lPlayers.removeTown(removeTown);

                            runLater(new Runnable() {

                                @Override
                                public void run() {

                                    if (lPlayers.getTowns().size() == 0) {
                                        if (lPlayers.getId() == player.getId()) {
                                            currentSelectedTown = null;
                                            Town_map.setVisible(false);
                                            WarningPane.setVisible(false);
                                            GameOverPane.setVisible(true);

                                        }
                                    } else {
                                        townlist = FXCollections.observableArrayList(lPlayers.getTowns());

                                        Town_list.setItems(townlist);
                                        Town_list.getSelectionModel().select(0);
                                        currentSelectedTown = Town_list.getSelectionModel().getSelectedItem();
                                        TownNameText.setText(currentSelectedTown.getName());
                                    }

                                }
                            });
                        }
                    }
                }
            }
        }

        for (Player winningPlayer : addTownsPLayer) {
            for (Town addTown : townsToAdd) {
                if (winningPlayer.getId() == addTown.getOwner()) {
                    for (Player wplayer : players) {
                        if (wplayer.getId() == winningPlayer.getId()) {
                            addTown.setOwner(wplayer.getId());
                            wplayer.addTown(addTown);

                            if(wplayer.getId() == player.getId()) {
                                warningPane("You have captured " + addTown.getName() + "!");
                            }

                        }
                    }
                    runLater(new Runnable() {
                        @Override
                        public void run() {
                            addTownToUI(addTown);
                        }
                    });
                }
            }
        }
    }

    /**
     * Adds newly created players to the local game state.
     */
    public void addNewPlayers(ArrayList<Player> newPlayers) {
        ArrayList<Player> playersToAdd = new ArrayList<>();
        for (Player player : newPlayers) {
            boolean exists = false;
            for (Player oldPlayer : players) {
                if (oldPlayer.getId() == player.getId()) {
                    exists = true;
                }
            }
            if (!exists) {
                playersToAdd.add(player);
            }
        }

        for (Player p : playersToAdd) {
            players.add(p);
            for (Town town : Database.instance().getTownsPlayer(p.getId())) {
                p.addTown(town);
                addTownToUI(town);
            }
        }
    }

    /**
     * Closes the tutorial pane.
     */
    public void xTutorial() {
        TutorialPane.setVisible(false);
    }

    /**
     * Exits game.
     */
    public void ExitGameButton() {
        Platform.exit();
    }

    /**
     * For creating a new town when you have lost the game.
     */
    public void GameOverNewTown() {
        if(!Database.instance().insertTown("default town", player.getId())) {
            warningPane("The map is full you can not create another town");
            return;
        }
        ArrayList<Town> town = Database.instance().getTownsPlayer(player.getId());
        for (Town t : town) {
            player.addTown(t);
        }
        onLogin();
        GameOverPane.setVisible(false);
        Town_map.setVisible(true);
        renameTownSelected();
        updateInfo();
    }

    /**
     * For logging out when you have lost.
     */
    public void GameOverLogout() {
        LogOutButton();
        GameOverPane.setVisible(false);
    }

    /**
     * For displaying the tutorial while in game.
     */
    public void TutorialButton() {
        TutorialPane.setVisible(true);
    }

    /**
     * Adds a town to to its owner
     */
    public void addTownToPlayer(Town t) {
        for (Player lPlayer : players) {
            if (lPlayer.getId() == t.getOwner()) {
                for (Town lTown : lPlayer.getTowns()) {
                    if (lTown.getTownId() == t.getTownId()) {
                        // Town already exists in the correct player
                        return;
                    }
                }
                // If we get here we have found the right player, and it didn't have the town in its list.
                lPlayer.addTown(t);
                return;
            }
        }
    }

    /**
     * Checks if the given townId is owned by the local player
     * 
     * @param townId
     * @return
     */
    public boolean townOwnedByLocalPlayer(int townId) {
        for (int i : getLocalPlayersTownIds()) {
            if (i == townId) {
                return true;
            }
        }
        return false;
    }
}