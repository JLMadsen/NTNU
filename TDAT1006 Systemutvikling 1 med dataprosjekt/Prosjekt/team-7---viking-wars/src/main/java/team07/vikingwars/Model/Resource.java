package team07.vikingwars.Model;

/**
 *  Resource super class
 *
 *  subclasses: Silver - Iron - Wood
 */

public abstract class Resource {

    private String name;
    private int amount;

    /**
     * @param name
     * @param amount
     */
    public Resource(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    /**
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * @return amount
     */
    public int getAmount(){
        return amount;
    }

    /**
     * @param n
     */
    public void setAmount(int n){
        amount = n;
    }

    /**
     * Adds a certain quantity of a resource according to input
     * @param n
     */
    public void addAmount(int n){
        amount += n;
    }

    /**
     * @return toString
     */
    public String toString(){
        return name +": "+ amount;
    }
}
