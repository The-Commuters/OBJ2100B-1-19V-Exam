/*
 * Player class is set up when the administrator registers
 * the player at the beginning of the tournment.
 */
package progeksamen;

public class Player {
    
    int id;
    String name;
    
    /**
     * Constructor when the object is created.
     */
    public Player () {}
    
    /**
     * Constructor when registering.
     * @param name 
     */
    public Player (String name) {
        this.name = name;
        this.id = generateId();
    }
    
    /**
     * Generates id to place in the object at creation.
     * @return the newly generated id. 
     */
    public int generateId () {
    
        return 0;
    }
}
