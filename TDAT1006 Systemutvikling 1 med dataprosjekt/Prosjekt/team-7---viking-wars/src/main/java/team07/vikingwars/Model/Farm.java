package team07.vikingwars.Model;

/**
 *Farm is a limiting building, that limits the amount of units the town is available to train.
 *This class is a subclass of Building.
 */
public class Farm extends Building {

    private int capacity;
    private int baseCapacity;

    /**
     * Constructor for farm
     * @param level
     */
    public Farm(int level) {
        super("Farm", new int[]{30,30,30}, level, 1.5, 20, "The town's army capacity depends on the food production");
        baseCapacity = 20;
        capacity = (int)(baseCapacity * Math.pow(level,2));
    }

    /**
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Increases the capacity in the town
     * @param level
     */
    @Override
    public void setLevel(int level) {
        this.level = level;
        capacity = (int)(baseCapacity * Math.pow(level,2));
    }

    /**
     * @return super.toString() and toString().
     */
    public String getInfo() {
        return super.toString() + " Capacity: " + capacity;

    }
}
