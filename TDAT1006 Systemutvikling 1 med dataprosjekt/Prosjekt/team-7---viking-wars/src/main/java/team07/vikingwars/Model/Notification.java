package team07.vikingwars.Model;

/**
 *  Notification class is used for notification list.
 */

public class Notification {
    String report = "";

    /**
     * Constructor
     * @param s
     */
    public Notification(String s) {
        report = s;
    }

    /**
     *
     * @return report
     */
    public String toString() {
        return report;
    }
}