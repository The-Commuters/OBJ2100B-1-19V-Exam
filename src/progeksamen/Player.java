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
        savePlayer();
    }
    
    /**
     * Generates id to place in the object at creation.
     * @return the newly generated id. 
     */
    public int generateId () {
        // Generates a unique ID to the player.
        return 0;
    }
    
    /**
     * The class that stores the player in at the end of the player-file
     * when the player is created by the administrator.
     */
    private void savePlayer() {
        // Saves the player in the file with printwriter.
    }
}
