/*
 * Player class is set up when the administrator registers
 * the player at the beginning of the tournment.
 */
package progeksamen;

import java.io.Serializable;

public class Player implements Serializable{
    
    private int id;
    private String name;
    private double score;
    
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
   
    public void deletePlayer() {
        
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
       return this.getId() + this.getName() + this.getScore();
    }
    
    public void setScore(double score){
        this.score = score;
    }
    
    @Override
    public String toString(){
    
        return "ID: " + getId() + " Name: " + getName() + " Score: " + getScore(); 
    }
    
    public String getWinner(Player p) {
        
        if (this.getScore() > p.getScore())
          return p.getName();
        return this.getName();
         
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }
}
