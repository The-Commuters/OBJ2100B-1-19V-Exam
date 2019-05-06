/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

public class Result implements Serializable {
    
    Player winner;
    Player loser;
    Boolean draw;
    
    public Result() {
    }
    
    public Result(Player winner, Player loser, Boolean draw) {
        this.winner = winner;
        this.loser = loser;
        this.draw = draw;
    }
    
    /**
     * The class that stores the player in at the end of the player-file
     * when the player is created by the administrator.
     * @throws java.io.FileNotFoundException
     */
    public void saveGame(String tournament) throws FileNotFoundException, IOException {
        
        // Creates a object to place.
        Result result = new Result(this.winner, this.loser, this.draw);
        
        // Stores the player at the end of the players.dat in BINARY.
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(tournament + ".dat"));) 
        {
            output.writeObject(new java.util.Date());
        }
        
        // Stores the player at the end of the players.text in TEXT.
        String resultText = this.winner.id + ";" + this.loser.id + ";" + this.draw;
 
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter(tournament + ".txt", true));
            outStream.newLine();
            outStream.write(resultText);
            outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
