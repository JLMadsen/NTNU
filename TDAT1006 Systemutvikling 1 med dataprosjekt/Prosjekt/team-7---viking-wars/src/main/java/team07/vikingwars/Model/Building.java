package team07.vikingwars.Model;

/**
 * Building super class
 *
 * subclasses: Raiding - Town hall - Barracks - Woodcutter - Farm - Ironmine - Wall.
 */

public abstract class Building {

    private final String name;
    protected int level, hiddenLevel, silvercost, woodcost, ironcost, maxLevel;
    protected String description;
    protected double priceFactor;
    protected int[] basecosts;

    /**
     * Constructor for the building class.
     * @param name used for identifying building class in database.
     * @param basecosts starting cost of the building.
     * @param level
     * @param priceFactor the factor which the price increases when upgraded.
     * @param maxLevel
     * @param description
     */
    public Building(String name, int[] basecosts, int level, double priceFactor, int maxLevel, String description) {
        this.name = name;
        this.level = level;
        this.hiddenLevel = level;
        this.basecosts = basecosts;
        this.silvercost = (int)(Math.pow(hiddenLevel,2)*basecosts[0]*priceFactor);
        this.ironcost = (int)(Math.pow(hiddenLevel,2)*basecosts[1]*priceFactor);
        this.woodcost = (int)(Math.pow(hiddenLevel,2)*basecosts[2]*priceFactor);
        this.maxLevel = maxLevel;
        this.priceFactor = priceFactor;
        this.description = description;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return silvercost
     */
    public int getSilverCost() {
        return silvercost;
    }

    /**
     * @return woodcost
     */
    public int getWoodcost() {
        return woodcost;
    }

    /**
     * @return ironcost
     */
    public int getIroncost() {
        return ironcost;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return maxLevel
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * @return hiddenlevel
     */
    public int getHiddenLevel() {
        return hiddenLevel;
    }

    /**
     * Checks if the building is available for a upgrade.
     *
     * @return true if available false if not
     */
    public boolean upgrade() {
        if (hiddenLevel < maxLevel) {
            hiddenLevel++;
            this.silvercost = (int)(hiddenLevel*hiddenLevel*basecosts[0]*priceFactor);
            this.ironcost = (int)(hiddenLevel*hiddenLevel*basecosts[1]*priceFactor);
            this.woodcost = (int)(hiddenLevel*hiddenLevel*basecosts[2]*priceFactor);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return toString
     */
    @Override
    public String toString() {
        return name + ". Level: " + level + " Cost: " + silvercost + " silver, " + ironcost + " iron, " + woodcost + " wood.";
    }

    /**
     * For fetching super.toString()
     * @return
     */
    public String getInfo() {
        return toString();
    }
}
