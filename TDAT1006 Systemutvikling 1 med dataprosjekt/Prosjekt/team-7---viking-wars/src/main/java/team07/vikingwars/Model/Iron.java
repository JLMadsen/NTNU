package team07.vikingwars.Model;

/**
 *  Iron is a resource used to upgrade buildings and train troops.
 *  Iron is a subclass of Resource.
 */

public class Iron extends Resource {

    /**
     * @param amount
     */

    public Iron(int amount){
        super("Iron", amount);
    }
}
