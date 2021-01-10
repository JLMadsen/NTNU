package team07.vikingwars.Model;


/**
 * Barracks is a production building, it produces units.
 * This class is a subclass of Building.
 */
public class Barracks extends Building {


    /**
     * Constructor for barracks
     * @param level
     */
    public Barracks(int level) {
        super("Barracks", new int[] {50,50,50}, level, 1.0,20,"This is where you train different troops");
    }
}
