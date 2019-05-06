/*
 * Player class is set up when the administrator registers
 * the player at the beginning of the tournment.
 */
package progeksamen;

import java.io.Serializable;

public class Player implements Serializable{
    
    int id;
    String name;
    int score;
    
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
        this.score = 0;

    }
    
    /**
     * Constructor used in saveplayer();
     * @param id
     * @param name 
     */
    public Player (int id, String name) {
        this.name = name;
        this.id = id;
    }
    
    /**
     * Generates id to place in the object at creation.
     * @return the newly generated id. 
     */
    public int generateId () {
        // Generates a unique ID to the player.
        return 0;
    }
    
    public String getPlayer(){
       return this.id + this.name + this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    @Override
    public String toString(){
    
        return id + " " + name + " " + score; 
    }
    
    public String getWinner(Player p) {
        
        if (this.score > p.score)
          return p.name;
        return this.name;
         
    }
}
