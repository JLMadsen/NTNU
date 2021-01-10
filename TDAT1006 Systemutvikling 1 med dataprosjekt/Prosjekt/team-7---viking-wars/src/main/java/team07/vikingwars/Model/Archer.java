package team07.vikingwars.Model;

/**
 * Archer class,
 * subclass of Unit
 */

public class Archer extends Unit {

    /**
     * Constructor for archer
     * @param troopCount
     */
    public Archer(int troopCount) {
        super("Archer", 100, troopCount, 40, 0,10,0,1);
    }

    /**
     * Deep copy for the Archer constructor.
     * @param u
     * @return
     */
    @Override
    public Unit copy(Unit u) {
        return new Archer(u.getTroopCount());
    }

}

