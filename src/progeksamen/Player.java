/*
 * Player class is set up when the administrator registers
 * the player at the beginning of the tournment.
 */
package progeksamen;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player implements Serializable{
    
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
    
    /**
     * The class that stores the player in at the end of the player-file
     * when the player is created by the administrator.
     * @throws java.io.FileNotFoundException
     */
    public void savePlayer(String tournament) throws FileNotFoundException, IOException {
        
        Player player = new Player(this.id, this.name);
        
        // Stores the player at the end of the players.dat in BINARY.
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(tournament + ".dat"));) 
        {
            output.writeObject(new java.util.Date());
        }
        
        String playerText = this.id + ";" + this.name;
        // Stores the player at the end of the players.text in TEXT.
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter(tournament + ".txt", true));
            outStream.newLine();
            outStream.write(playerText);
            outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Collects the list of players from the file 
     */
    private void getPlayers() {
    
    }
}
