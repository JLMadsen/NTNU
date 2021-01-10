package team07.vikingwars.Model;

/**
 * Super class for units.
 *
 * Subclasses: Spearman - Archer - Cavalry
 */
public abstract class Unit {

    private final String name;
    private int health;
    private int troopCount;
    private final int damage, silver, wood, iron, index;

    /**
     * Super constructor for units.
     * @param name
     * @param health
     * @param troopCount
     * @param damage
     * @param silver
     * @param wood
     * @param iron
     * @param index
     */
    public Unit(String name, int health, int troopCount, int damage, int silver, int wood, int iron, int index) {
        this.name = name;
        this.health = health;
        this.troopCount = troopCount;
        this.damage = damage;
        this.silver = silver;
        this.wood = wood;
        this.iron = iron;
        this.index = index;
    }

    /**
     * Used on server.
     * @param u
     * @return copy of unit.
     */
    public abstract Unit copy(Unit u);

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return troopCount
     */
    public int getTroopCount() {
        return troopCount;
    }

    /**
     * @return index
     */
    public int getIndex(){
        return index;
    }

    /**
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return price
     */
    public int[] getPrice() {
        int[] price = {silver, iron, wood};
        return price;
    }

    /**
     * @param troopCount
     */
    public void setTroopCount(int troopCount) {
        this.troopCount = troopCount;
    }

    /**
     * Trains troops according to the user's input.
     * @param troops
     */
    public void addTroops(int troops) {
        this.troopCount += troops;
    }

    /**
     * Kills a certain amount of troops, depending on the input.
     * @param amount
     */
    public void death(int amount) {
        this.troopCount -= amount;
    }

    /**
     * @return toString()
     */
    public String toString() {
        return name + " | " + damage + " Damage | " + health + " Health | Cost: " + silver + " Silver, "+ wood +" Wood, "+ iron +" Iron." ;
    }
}
