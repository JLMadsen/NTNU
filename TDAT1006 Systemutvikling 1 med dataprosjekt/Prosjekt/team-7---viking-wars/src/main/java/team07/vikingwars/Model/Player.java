package team07.vikingwars.Model;

import java.util.ArrayList;

/**
 * The game contains players, the user is a player. Players contains towns, a
 * name, id and a score.
 */

public class Player implements Comparable<Player> {

    private ArrayList<Town> towns;
    private String Name;
    private int id;
    private int score = 0;

    /**
     * Constructor
     * @param towns
     * @param Name
     * @param id
     */

    public Player(ArrayList<Town> towns, String Name, int id) {
        this.towns = towns;
        this.Name = Name;
        this.id = id;
        updateScore();
    }

    /**
     * Return all the towns the specific player owns.
     * @return towns
     */
    public ArrayList<Town> getTowns() {
        return towns;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return Name;
    }

    /**
     * Used with the database to identify each player.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Adds a new town to ArrayList towns
     * 
     * @param newTown
     */
    public void addTown(Town newTown) {
        if (!towns.contains(newTown)) {
            towns.add(newTown);
            updateScore();
        }
    }

    /**
     * Removes a town from the ArrayList of towns.
     * @param townToRemove
     */
    public void removeTown(Town townToRemove) {
        if (towns.contains(townToRemove)) {
            towns.remove(townToRemove);
            updateScore();
        }
    }

    /**
     * Increases the player's score according to input
     */
    public void updateScore() {
        score = 0;
        for (Town t : towns) {
            score += t.getScore();
        }
    }

    /**
     *
     * @return score
     */

    public int getScore() {
        return score;
    }

    /**
     * Sorts players from highest score to lowest score.
     * 
     * @param o
     * @return difference in score
     */
    @Override
    public int compareTo(Player o) {
        return o.getScore() - getScore();
    }

    /**
     *
     * @return info about player
     */
    public String toString() {
        return "Player: " + Name + " - Towns: " + towns.size() + " - Score: " + score;
    }
}
