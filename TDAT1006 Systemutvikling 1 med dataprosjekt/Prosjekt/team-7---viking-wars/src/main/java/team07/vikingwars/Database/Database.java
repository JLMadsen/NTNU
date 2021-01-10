package team07.vikingwars.Database;

import team07.vikingwars.Model.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import static team07.vikingwars.Controller.FXMLDocumentController.MAXGRIDX;
import static team07.vikingwars.Controller.FXMLDocumentController.MAXGRIDY;

/**
 * Database class for communication between game and MySQL server.
 * The class is a singelton.
 */
public class Database {

    private static Database instance;
    private Connection connection;
    private String url;

    /**
     * Constructor that establishes a connection to the server.
     */
    private Database() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream(".properties"));
            url = prop.getProperty("dburl");
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error in establishing connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Only for testing
     */
    public void setConnection() {
        try {
            if (connection == null && connection.isClosed()) {
                connection = DriverManager.getConnection(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Singelton to ensure only one instance of database.
     *
     * @return instance
     */
    public static Database instance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Close connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in closing connection");
        }
    }

    /**
     * Sets autocommit on or of
     * 
     * @param value either true or false
     */
    public void setAutoCommit(boolean value) {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Commits the statements
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param message
     * @return true or false based on success or fail
     */
    public boolean insert(String message, int playerId) {
        try {
            PreparedStatement query = connection
                    .prepareStatement("INSERT INTO chat VALUES(default, ?, default, ?)");
            query.setString(1, message);
            query.setInt(2, playerId);
            if (query.execute()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * for reading chat messages.
     * @return chat messages
     */
    public ResultSet read() {
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM chat");
            ResultSet messages = query.executeQuery();
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the latest chat messages
     *
     * @param index
     * @param localPlayerId
     * @return
     */
    public ResultSet readAfterIndex(int index, int localPlayerId) {
        try {
            PreparedStatement query = connection
                    .prepareStatement("SELECT * FROM chat WHERE message_id > ? AND user_id != ?");
            query.setInt(1, index);
            query.setInt(2, localPlayerId);
            ResultSet messages = query.executeQuery();
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param username
     * @return Salted password and salt
     */
    public String[] getEncryptedPassword(String username) {
        try {
            PreparedStatement query = connection.prepareStatement("SELECT password, salt FROM user WHERE username = ?");
            query.setString(1, username);
            ResultSet passAndSalt = query.executeQuery();
            if (!passAndSalt.next()) {
                return null;
            }
            String[] result = { passAndSalt.getString("password"), passAndSalt.getString("salt") };
            query.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new user
     *
     * @param username
     * @param password
     * @return true or false if user has been created or not.
     */
    public boolean newUser(String username, String password, String salt) {
        try {
            PreparedStatement query = connection.prepareStatement("INSERT INTO user VALUES (default, ?, ?, ?)");
            query.setString(1, username);
            query.setString(2, password);
            query.setString(3, salt);
            query.execute();
            return getUserId(username) == -1 ? false : true; // if user doesn't exist, return false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the user ID that matches the username in the input
     *
     * @param username
     * @return
     */
    public int getUserId(String username) {
        try {
            PreparedStatement query = connection.prepareStatement("SELECT user_id FROM user WHERE username = ?");
            query.setString(1, username);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                int id = result.getInt("user_id");
                query.close();
                return id;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @return an arraylist of already existing usernames
     */
    public ArrayList<String> getUsernames() {
        try {
            ArrayList<String> result = new ArrayList<>();
            PreparedStatement query = connection.prepareStatement("SELECT username FROM user WHERE 1");
            ResultSet names = query.executeQuery();
            while (names.next()) {
                result.add(names.getString("username"));
            }
            query.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * For updating town name in database.
     * @param newTownName
     * @param townid
     */
    public void updateTownName(String newTownName, int townid) {
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE town set townname = ? where town_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, newTownName);
            stmt.setInt(2, townid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the towns to the given userid
     *
     * @param userid
     * @return ArrayList(Town)
     */
    public ArrayList<Town> getTownsPlayer(int userid) {

        PreparedStatement stmt = null;
        ResultSet result = null;

        try {

            ArrayList<Town> towns = new ArrayList<Town>();
            String query = "SELECT * FROM town WHERE user_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, userid);
            result = stmt.executeQuery();

            while (result.next()) {

                ArrayList<Building> buildings = new ArrayList<>();
                buildings.add(new Barracks(result.getInt("barracks")));
                buildings.add(new RaidersHut(result.getInt("raiding")));
                buildings.add(new TownHall(result.getInt("townhall")));
                buildings.add(new Ironmine(result.getInt("ironmine")));
                buildings.add(new Woodcutter(result.getInt("woodcutter")));
                buildings.add(new Farm(result.getInt("farm")));
                buildings.add(new Wall(result.getInt("wall")));

                ArrayList<Resource> resources = new ArrayList<>();
                resources.add(new Silver(result.getInt("silver")));
                resources.add(new Iron(result.getInt("iron")));
                resources.add(new Wood(result.getInt("wood")));

                ArrayList<Unit> units = new ArrayList<>();
                units.add(new Spearman(result.getInt("spearman")));
                units.add(new Archer(result.getInt("archer")));
                units.add(new Cavalry(result.getInt("cavalry")));

                towns.add(new Town(buildings, resources, units, result.getInt("x"), result.getInt("y"),
                        result.getString("townname"), userid, result.getInt("town_id")));
            }
            return towns;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates a statement that updates the resources in the database
     *
     * @param resources
     * @param townid
     * @return
     */
    public PreparedStatement updateResources(int[] resources, int townid) {
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE town set silver = silver + ?, iron = iron + ?, wood = wood + ? where town_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, resources[0]); // silver
            stmt.setInt(2, resources[1]); // iron
            stmt.setInt(3, resources[2]); // wood
            stmt.setInt(4, townid);
            return stmt;
        } catch (SQLException e) {
            System.out.println("Statement failed");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the statement that updates the amount of each unit in the database
     *
     * @param change a int list containing the changes in the different unit types
     * @param townid the town id the changes are made for
     * @return the statement
     */

    public PreparedStatement updateUnits(int[] change, int townid) {
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE town set spearman = spearman + ?, archer = archer + ?, cavalry = cavalry + ? where town_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, change[0]);
            stmt.setInt(2, change[1]);
            stmt.setInt(3, change[2]);
            stmt.setInt(4, townid);
            return stmt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the building given what building it is and which town it is upgraded
     * in and put in the database
     *
     * @param building
     * @param townid
     */

    public PreparedStatement updateBuilding(Building building, int townid) {
        PreparedStatement stmt = null;
        try {
            String query = "UPDATE town set " + building.getName() + " = " + building.getName()
                    + " + ? where town_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setInt(2, townid);
            return stmt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserts a new town into the database creates a random x and y coordinate
     * until it finds a combination that is not in use
     *
     * @param townname
     * @param userid
     */
    public boolean insertTown(String townname, int userid) {

        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet result = null;

        try {

            String query2 = "SELECT x,y from town";
            stmt2 = connection.prepareStatement(query2);
            result = stmt2.executeQuery();

            boolean[][] coords = new boolean[MAXGRIDY][MAXGRIDX];
            while (result.next()) {
                coords[result.getInt("y")][result.getInt("x")] = true;
            }

            result.last();
            int rows = result.getRow();
            result.beforeFirst();

            if (rows == MAXGRIDY * MAXGRIDX) {
                return false;
            }
            Random random = new Random();
            int x = random.nextInt(MAXGRIDX);
            int y = random.nextInt(MAXGRIDY);
            while (coords[y][x]) {
                x = random.nextInt(MAXGRIDX);
                y = random.nextInt(MAXGRIDY);
            }

            String query = "INSERT INTO town(x, y, townname, user_id) VALUES(?,?,?,?);";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, x);
            stmt.setInt(2, y);
            stmt.setString(3, townname);
            stmt.setInt(4, userid);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {

                if (result != null) {
                    result.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Used for fetching all players on initializing.
     * @param index
     * @return
     */
    public ArrayList<Player> getPlayers(int index) {
        try {
            ArrayList<Player> result = new ArrayList<>();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM user WHERE user_id > ?");
            query.setInt(1, index);
            ResultSet players = query.executeQuery();

            while (players.next()) {
                String username = players.getString("username");
                int id = players.getInt("user_id");
                result.add(new Player(new ArrayList<Town>(), username, id));
            }
            players.close();
            query.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * getPlayer(String) Method that takes in an username, searches the database,
     * and if found; returns the given username as an Player object
     *
     * @param searchWord
     * @return Player
     */
    public Player getPlayer(String searchWord) {
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM user WHERE username=?");
            query.setString(1, searchWord);
            ResultSet player = query.executeQuery();
            player.next();

            String username = player.getString("username");
            int id = player.getInt("user_id");
            ArrayList<Town> towns = new ArrayList<Town>();
            Player result = new Player(towns, username, id);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns all the battles a certain player has been in
     *
     * @param index
     * @param localPlayerTownId
     * @return
     */
    public PreparedStatement getPlayerBattlesAfterIndex(int index, int localPlayerTownId) {
        PreparedStatement query;

        try {
            query = connection.prepareStatement(
                    "SELECT attack_id, attackerTown, defenderTown FROM attack WHERE (defenderTown = ? OR attackerTown = ?) AND attack_id > ? AND ticksToBattle > -1");
            query.setInt(1, localPlayerTownId);
            query.setInt(2, localPlayerTownId);
            query.setInt(3, index);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insert a new battle in the attack table.
     * 
     * @param attackId
     * @param attUnits
     * @param defendId
     * @param ticksBeforeAttack
     */
    public void insertBattle(int attackId, ArrayList<Unit> attUnits, int defendId, int ticksBeforeAttack) {
        PreparedStatement query = null;

        try {
            query = connection.prepareStatement("INSERT INTO attack VALUES(DEFAULT, ?,?,?, ?, ?,?)");
            query.setInt(1, attackId);
            query.setInt(2, attUnits.get(0).getTroopCount());
            query.setInt(3, attUnits.get(1).getTroopCount());
            query.setInt(4, attUnits.get(2).getTroopCount());
            query.setInt(5, defendId);
            query.setInt(6, ticksBeforeAttack);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param userId
     * @return
     */
    public ArrayList<Timestamp> getTownLastUpdated(int userId) {
        PreparedStatement query = null;
        ArrayList<Timestamp> ret = new ArrayList<>();
        try {

            query = connection.prepareStatement("SELECT timesinceupdate FROM town WHERE user_id = ?");
            query.setInt(1, userId);
            ResultSet s = query.executeQuery();
            while (s.next()) {
                ret.add(s.getTimestamp("timesinceupdate"));
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @return all towns.
     */
    public ArrayList<Town> getAllTowns() {
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {

            ArrayList<Town> towns = new ArrayList<Town>();
            String query = "SELECT * FROM town";
            stmt = connection.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {

                ArrayList<Building> buildings = new ArrayList<>();
                buildings.add(new Barracks(result.getInt("barracks")));
                buildings.add(new RaidersHut(result.getInt("raiding")));
                buildings.add(new TownHall(result.getInt("townhall")));
                buildings.add(new Ironmine(result.getInt("ironmine")));
                buildings.add(new Woodcutter(result.getInt("woodcutter")));
                buildings.add(new Farm(result.getInt("farm")));
                buildings.add(new Wall(result.getInt("wall")));

                ArrayList<Resource> resources = new ArrayList<>();
                resources.add(new Silver(result.getInt("silver")));
                resources.add(new Iron(result.getInt("iron")));
                resources.add(new Wood(result.getInt("wood")));

                ArrayList<Unit> units = new ArrayList<>();
                units.add(new Spearman(result.getInt("spearman")));
                units.add(new Archer(result.getInt("archer")));
                units.add(new Cavalry(result.getInt("cavalry")));

                towns.add(new Town(buildings, resources, units, result.getInt("x"), result.getInt("y"),
                        result.getString("townname"), result.getInt("user_id"), result.getInt("town_id")));
            }
            return towns;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get town with given index.
     * @param index
     * @return
     */
    public ArrayList<Town> getTownsAfterIndex(int index) {
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {

            ArrayList<Town> towns = new ArrayList<Town>();
            String query = "SELECT * FROM town WHERE town_id > ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, index);
            result = stmt.executeQuery();

            while (result.next()) {

                ArrayList<Building> buildings = new ArrayList<>();
                buildings.add(new Barracks(result.getInt("barracks")));
                buildings.add(new RaidersHut(result.getInt("raiding")));
                buildings.add(new TownHall(result.getInt("townhall")));
                buildings.add(new Ironmine(result.getInt("ironmine")));
                buildings.add(new Woodcutter(result.getInt("woodcutter")));
                buildings.add(new Farm(result.getInt("farm")));
                buildings.add(new Wall(result.getInt("wall")));

                ArrayList<Resource> resources = new ArrayList<>();
                resources.add(new Silver(result.getInt("silver")));
                resources.add(new Iron(result.getInt("iron")));
                resources.add(new Wood(result.getInt("wood")));

                ArrayList<Unit> units = new ArrayList<>();
                units.add(new Spearman(result.getInt("spearman")));
                units.add(new Archer(result.getInt("archer")));
                units.add(new Cavalry(result.getInt("cavalry")));

                towns.add(new Town(buildings, resources, units, result.getInt("x"), result.getInt("y"),
                        result.getString("townname"), result.getInt("user_id"), result.getInt("town_id")));
            }
            return towns;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Collects data from a battle and makes a report displaying this data
     *
     * @param attackId
     * @param localTowns
     * @return
     */
    public String getBattleReport(int attackId, int[] localTowns) {
        PreparedStatement query = null;
        try {
            query = connection.prepareStatement(
                    "SELECT attack.*, attack_report.*, town.townname, town.town_id FROM attack_report JOIN attack ON attack.attack_id = attack_report.attack_id JOIN town ON town.town_id = attack.attackerTown OR town.town_id = attack.defenderTown WHERE attack_report.attack_id = ?");
            query.setInt(1, attackId);
            query.executeQuery();
            ResultSet result = query.executeQuery();
            int attSpearmanLoss = 0;
            int attArcherLoss = 0;
            int attCavalryLoss = 0;
            int defSpearmanLoss = 0;
            int defArcherLoss = 0;
            int defCavalryLoss = 0;
            int attTown = 0;
            int defTown = 0;
            String attTownName = "";
            String defTownName = "";
            boolean hadResults = false;
            while (result.next()) {
                hadResults = true;
                // System.out.println(result.getString("townname"));
                attSpearmanLoss = result.getInt("attSpearmanLoss");
                attArcherLoss = result.getInt("attArcherLoss");
                attCavalryLoss = result.getInt("attCavalryLoss");
                defSpearmanLoss = result.getInt("defSpearmanLoss");
                defArcherLoss = result.getInt("defArcherLoss");
                defCavalryLoss = result.getInt("defCavalryLoss");
                attTown = result.getInt("attackerTown");
                defTown = result.getInt("defenderTown");
                if (attTown == result.getInt("town_id")) {
                    attTownName += result.getString("townname");
                }
                if (defTown == result.getInt("town_id")) {
                    defTownName += result.getString("townname");
                }
            }
            if (hadResults) {
                BattleReport br = new BattleReport(attSpearmanLoss, attArcherLoss, attCavalryLoss, defSpearmanLoss,
                        defArcherLoss, defCavalryLoss, attTown, defTown, attTownName, defTownName, localTowns);
                System.out.println("(getBattleReports()) hadResults: Got here");
                String report = br.report();
                return report;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
