package team07.vikingwars.Model;

import java.util.ArrayList;

/**
 * Town class.
 *
 * indexes: Buildings 0 Barracks 1 RaidingHut 2 Townhall 3 Ironmine 4 Woodcutter
 * 5 Farm 6 Wall
 *
 * Resources: 0 Silver 1 Iron 2 Wood
 *
 * Units: 0 Spearman 1 Archer 2 Cavalry
 */
public class Town {
    private int owner, x, y, townId;
    private ArrayList<Building> buildings;
    private ArrayList<Resource> resources;
    private ArrayList<Unit> units;
    private String name;
    private int score = 0;

    /**
     * Constructor.
     * @param buildings
     * @param resources
     * @param units
     * @param x
     * @param y
     * @param name
     * @param owner
     * @param townId
     */
    public Town(ArrayList<Building> buildings, ArrayList<Resource> resources, ArrayList<Unit> units, int x, int y,
            String name, int owner, int townId) {
        if (resources == null) {
            initResources();
        } else {
            this.resources = resources;
        }
        if (units == null) {
            initUnits();
        } else {
            this.units = units;
        }
        if (buildings == null) {
            initBuilding();
        } else {
            this.buildings = buildings;
        }
        this.x = x;
        this.y = y;
        this.name = name;
        this.owner = owner;
        this.townId = townId;
        updateScore();
    }

    /**
     * If contructor gets null values it initializes default values
     */
    public void initBuilding() {
        this.buildings = new ArrayList<>();
        buildings.add(new Barracks(1));
        buildings.add(new RaidersHut(1));
        buildings.add(new TownHall(1));
        buildings.add(new Ironmine(0));
        buildings.add(new Woodcutter(0));
        buildings.add(new Farm(1));
        buildings.add(new Wall(0));
    }

    /**
     * If contructor gets null values it initializes default values
     */
    public void initResources() {
        this.resources = new ArrayList<>();
        resources.add(new Silver(100));
        resources.add(new Iron(0));
        resources.add(new Wood(0));
    }

    /**
     * If contructor gets null values it initializes default values
     */
    public void initUnits() {
        this.units = new ArrayList<>();
        units.add(new Spearman(5));
        units.add(new Archer(0));
        units.add(new Cavalry(0));
    }

    /**
     * Calculates the score of the town, according to amount of buildings, resources
     * and units
     */
    public void updateScore() {
        score = 0;
        for (Building b : buildings) {
            score += b.level * 4;
        }

        for (Unit u : units) {
            score += u.getDamage() * u.getTroopCount() * 0.0001;
            score += u.getHealth() * u.getTroopCount() * 0.0001;
        }
    }

    /**
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds a quantity of a certain resource according to input
     * 
     * @param amount
     */
    public void addResources(int[] amount) { // starting with only one type of resources. Change this code when more is
                                             // added.
        for (int i = 0; i < resources.size(); i++) {
            resources.get(i).addAmount(amount[i]);
        }
        updateScore();
    }

    /**
     * Removes a quantity of a certain resource according to input
     * 
     * @param amount
     */
    public void removeResources(int[] amount) { // starting with only one type of resources. Change this code when more
                                                // is added.
        for (int i = 0; i < resources.size(); i++) {
            resources.get(i).addAmount(-(amount[i]));
        }
        updateScore();
    }

    /**
     * Town coordinates
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Town coordinates
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns town name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return ownerId
     * @return owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * @return capacity
     */
    public int getCapacity() {
        Farm farm = (Farm) buildings.get(5);
        return farm.getCapacity();
    }

    /**
     * Checks if the town's farm has the capacity required to train new troops
     * 
     * @param n
     * @return
     */
    public boolean canTrain(int n) {
        int totalTroops = 0;
        Farm farm = (Farm) buildings.get(5);
        for (Unit unit : units) {
            totalTroops += unit.getTroopCount();
        }
        if ((totalTroops + n) <= farm.getCapacity()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return buildings
     */
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    /**
     * @return units
     */
    public ArrayList<Unit> getUnits() {
        return units;
    }

    /**
     * @return resources
     */
    public ArrayList<Resource> getResources() {
        return resources;
    }

    /**
     * @return silver
     */
    public Silver getSilver() {
        return (Silver) resources.get(0);
    }

    /**
     * @return iron
     */
    public Iron getIron() {
        return (Iron) resources.get(1);
    }

    /**
     * @return wood
     */
    public Wood getWood() {
        return (Wood) resources.get(2);
    }

    /**
     * @return townId
     */
    public int getTownId() {
        return townId;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param buildings
     */
    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
        updateScore();
    }

    /**
     * @param resources
     */
    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
        updateScore();
    }

    /**
     * @param units
     */
    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
        updateScore();
    }

    /**
     * @param newOwner
     */
    public void setOwner(int newOwner) {
        this.owner = newOwner;
    }

    /**
     * Calculates the total number of troops in the town
     * 
     * @return
     */
    public int getTroopCount() {
        int totalTroops = 0;
        for (Unit unit : units) {
            totalTroops += unit.getTroopCount();
        }
        return totalTroops;
    }

    /**
     * @return name
     */
    public String toString() {
        return name;
    }

    /**
     * @return info
     */
    public String getInfo() {
        String result = "Town: " + name;

        for (Resource resource : resources) {
            result += " | " + resource.getName() + ": " + resource.getAmount();
        }
        for (Building building : buildings) {
            result += " | " + building.getName() + ": " + building.getLevel();
        }
        for (Unit unit : units) {
            result += " | " + unit.getName() + ": " + unit.getTroopCount();
        }
        return result;
    }
}