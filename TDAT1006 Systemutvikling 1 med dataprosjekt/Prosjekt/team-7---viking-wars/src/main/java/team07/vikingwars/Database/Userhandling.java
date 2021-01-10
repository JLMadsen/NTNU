package team07.vikingwars.Database;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import team07.vikingwars.Model.Player;
import team07.vikingwars.Model.Town;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Class for handling user login and creation.
 * This class is also responsible for the hashing and salting of passwords.
 */
public class Userhandling {
    private Database db;

    /**
     * Constructor for userhandling.
     */

    public Userhandling() {
        db = Database.instance();
        db.setConnection();
    }

    /**
     * @param username
     * @param password1
     * @param password2
     * @return true or false depending on if a new user is created
     */
    public boolean newUser(String username, String password1, String password2) {
        ArrayList<String> usernames = db.getUsernames();
        //Enforces minimum password requirements.
        if (usernames.contains(username)) {
            return false;
        }
        if(username.length() > 16 || username.length() < 3 || username.contains(" ")){
            return false;
        }
        if(password1.length() > 16 || password1.length() < 3 || password1.contains(" ")){
            return false;
        }
        if (!password1.equals(password2)) {
            return false;
        }

        byte[] salt = generateSalt();
        byte[] passwordAsBytes = password1.getBytes(StandardCharsets.UTF_8);
        byte[] pwsalt = combineSaltAndPw(salt, passwordAsBytes);

        String password = hash(pwsalt);

        if (db.newUser(username, password, new String(salt))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param username
     * @return new users
     */
    public Player getNewUserFromDB(String username){
        return db.getPlayer(username);
    }

    /**
     * @param userId
     * @return towns from specific player
     */
    public ArrayList<Town> getTownsPlayerFromDB(int userId){
        return db.getTownsPlayer(userId);
    }

    /**
     * @param username
     * @param password
     * @return return true or false depending on if login is ok or not
     */
    public boolean login(String username, String password) {
        if (getUserId(username) < 0) {
            return false;
        }
        String[] res = Database.instance().getEncryptedPassword(username);
        if (res == null) {
            return false;
        }
        byte[] pwsalt = combineSaltAndPw(res[1].getBytes(), password.getBytes());
        String userPw = hash(pwsalt);
        if (userPw.equals(res[0])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param username
     * @return user id of player with username, returns -1 if no user with username
     */
    public int getUserId(String username) {
        return db.getUserId(username);
    }

    /**
     * Generates salt used for security.
     * @return salt
     */
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);
        byte[] encodedSalt = Base64.getEncoder().encode(salt);
        return encodedSalt;
    }

    /**
     * Combines salt and password
     * @param salt
     * @param pw
     * @return
     */
    public byte[] combineSaltAndPw(byte[] salt, byte[] pw) {
        byte[] pwsalt = new byte[salt.length + pw.length];
        for (int i = 0; i < salt.length; i++) {
            pwsalt[i] = salt[i];
        }
        for (int i = 0; i < pw.length; i++) {
            pwsalt[i + salt.length] = pw[i];
        }
        return pwsalt;
    }

    /**
     * @param saltedPassword
     * @return hashed and salted password
     */
    // Have to fetch salt from DB if user exist...
    public String hash(byte[] saltedPassword) {
        try {
            HashCode result = Hashing.sha256().hashBytes(saltedPassword);

            String hashedAndSalted = result.toString();
            return hashedAndSalted;
        } catch (Exception e) {
            System.out.println("Error in password hashing");
        }
        return null;
    }

    /**
     * Method for fetching all the usernames.
     * This is needed for checking if your username is already taken.
     * @return Arraylist<String> usernames
     */
    public ArrayList<String> getUsernames() {
        return db.getUsernames();
    }
}
