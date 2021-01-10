package team07.vikingwars.Model;

/**
 * Spearman class.
 * Subclass of Unit.
 */
public class Spearman extends Unit {

    /**
     * @param troopCount
     */
    public Spearman(int troopCount) {
        super("Spearman", 100, troopCount, 50, 5,0,0, 0);
    }

    /**
     * @param u
     * @return deep copy of unit
     */
    @Override
    public Unit copy(Unit u) {
        return new Spearman(u.getTroopCount());
    }
}