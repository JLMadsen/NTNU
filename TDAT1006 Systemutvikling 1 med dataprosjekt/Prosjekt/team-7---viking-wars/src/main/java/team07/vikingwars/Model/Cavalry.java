package team07.vikingwars.Model;

/**
 * Cavalry class,
 * subclass of Unit
 */

public class Cavalry extends Unit {

    /**
     * Constructor for Cavalry
     * @param troopCount
     */

    public Cavalry(int troopCount) {
        super("Cavalry", 100, troopCount, 75, 50,50,50,2);
    }

    /**
     * @param u
     * @return deep copy of the unit's constructor
     */
    @Override
    public Unit copy(Unit u) {
        return new Cavalry(u.getTroopCount());
    }

}
