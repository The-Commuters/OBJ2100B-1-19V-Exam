/*
 * Player class is set up when the administrator registers
 * the player at the beginning of the tournment.
 */
package progeksamen;

import java.io.Serializable;
import java.util.Comparator;

public class Player implements Serializable, Comparator<Player>, Comparable<Player>{
    
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
       return this.getId() + this.getName() + this.getScore()/2;
    }
    
    public void setScore(double score){
        this.score = score;
    }
    
    @Override
    public String toString(){
    
        return "Name: " + getName() + " Score: " + getScore()/2; 
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
    @Override
      public int compare(Player p1, Player p2) {
        if (p1.getScore() < p2.getScore()) return -1;
        if (p1.getScore() > p2.getScore()) return 1;
        return 0;
      }
    
      
      
    @Override
      public int compareTo(Player p1) {
        int comparePlayer =  (int) p1.score;

        // This sorts them in a decending order 
        return (int) (comparePlayer - this.getScore());
      }
}
