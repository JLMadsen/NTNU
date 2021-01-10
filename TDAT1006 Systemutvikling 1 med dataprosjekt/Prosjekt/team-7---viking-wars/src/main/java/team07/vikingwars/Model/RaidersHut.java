package team07.vikingwars.Model;

/**
 * This building will give the town a steady income of silver
 * Subclass of Building
 */
public class RaidersHut extends Building {

    private int baseProduction, resourcePerTurn;

    /**
     * @param level
     */
    public RaidersHut(int level) {
        super("Raiding", new int[] { 10, 20, 20 }, level, 1.0, 20,
                "Creates silver per tick. Amount varies based on level");
        this.baseProduction = 3;
        this.resourcePerTurn = (int) (baseProduction * Math.pow(level, 1.5));
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
        resourcePerTurn = (int) (baseProduction * Math.pow(level, 1.5));
    }

    /**
     * @return toString()
     */
    @Override
    public String getInfo() {
        return super.toString() + " Resources per turn: " + resourcePerTurn;
    }
}