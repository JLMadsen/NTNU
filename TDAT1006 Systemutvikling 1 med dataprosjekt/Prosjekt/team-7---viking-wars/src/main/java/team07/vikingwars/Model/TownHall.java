package team07.vikingwars.Model;

/**
 * Town hall building is the central building for a town. This building wil have
 * more functions in the javaFX Controller
 * Subclass of Building
 */
public class TownHall extends Building {

    /**
     * @param level
     */
    public TownHall(int level) {
        super("Townhall", new int[]{40,40,40}, level, 0.5, 20, "Building for upgrading your other buildings");
    }
}