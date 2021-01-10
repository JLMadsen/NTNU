package team07.vikingwars.Model;

/**
 * Woodcutter is a production building, it produces wood.
 * This class is a subclass of Building.
 */
public class Woodcutter extends Building {

    private int baseProduction;
    private int resourcePerTurn;

    /**
     * Constructor for woodcutter.
     * @param level
     */
    public Woodcutter(int level) {
        super("Woodcutter", new int[]{20,20,10}, level, 1.0, 20, "Creates wood per tick. Amount varies based on level");
        this.baseProduction = 3;
        this.resourcePerTurn = (int)(baseProduction * Math.pow(level,1.5));
    }

    /**
     * @return baseProduction
     */
    public int getBaseProduction() {
        return baseProduction;
    }

    /**
     * @return resourcePerTurn
     */
    public int getResourcePerTurn() {
        return resourcePerTurn;
    }

    /**
     * Updates resource per turn.
     * @param level
     */
    @Override
    public void setLevel(int level) {
        this.level = level;
        resourcePerTurn = (int)(baseProduction * Math.pow(level,1.5));
    }

    /**
     * @return super.toString() and toString().
     */
    @Override
    public String getInfo() {
        return super.toString() + " Resources per turn: " + resourcePerTurn;

    }
}