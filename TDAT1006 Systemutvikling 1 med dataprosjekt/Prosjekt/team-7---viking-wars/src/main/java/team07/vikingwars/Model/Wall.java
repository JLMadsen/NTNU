package team07.vikingwars.Model;

/**
 * Wall building, used to increase defence in battles.
 * Subclass of Building.
 */
public class Wall extends Building {

    private double defenseFactor;

    /**
     * @param level
     */
    public Wall(int level) {
        super("Wall", new int[] { 100, 100, 100 }, level, 1.15, 10, "The wall adds a defense bonus to your town");
        this.defenseFactor = 1 - (level * 0.01);
    }

    /**
     * @return defenseFactor
     */
    public double getDefenseFactor() {
        return defenseFactor;
    }

    /**
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return true or false based on upgrade status.
     */
    @Override
    public boolean upgrade() {
        if (super.upgrade()) {
            this.defenseFactor = 1 - (level * 0.01);
            return true;
        }
        return false;
    }
}
