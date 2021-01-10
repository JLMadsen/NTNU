package team07.vikingwars.Model;


/**
 *Ironmine is a production building, it produces iron.
 *This class is a subclass of Building.
 */

public class Ironmine extends Building {

    private int baseProduction, resourcePerTurn;

    /**
     * Constructor for Ironmine
     */
    public Ironmine(int level) {
        super("Ironmine", new int[]{20,10,20}, level, 1.0, 20,
                "Creates iron per tick. Amount varies based on level");
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
     * Increases resourcePerTurn
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