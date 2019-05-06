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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    
    Player player1;
    Player player2;
    Result result;
    String[] moves;
    Date startDate;
    Date endDate;
    
    public Game () {}
    
    public Game (Player player1, Player player2, Result result, String[] moves, Date startDate, Date endDate) {
        
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.moves = moves;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
        /**
     * The class that stores the player in at the end of the player-file
     * when the player is created by the administrator.
     * @throws java.io.FileNotFoundException
     */
    public void saveGame() throws FileNotFoundException, IOException {
        
        Game game = new Game(this.player1, this.player2, this.result, this.moves, this.startDate, this.endDate);
        
        // Stores the player at the end of the players.dat in BINARY.
        try ( 
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("players.dat"));
        ) {
            output.writeObject(new java.util.Date());
        }
        
        // Stores the player at the end of the players.text in TEXT.
        String gameText = game.toString();
 
        try (BufferedWriter outStream = new BufferedWriter(new FileWriter("players.txt", true))) {
            outStream.newLine();
            outStream.write(gameText);
        }
    }
}
