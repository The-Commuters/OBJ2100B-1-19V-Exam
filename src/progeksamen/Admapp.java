/*
 * Main for the Admin application.
 */
package progeksamen;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Admapp extends Application implements Constants {
    
    // Current string for the Tournament, needed to find/create the .dat to store binary.
    // And the .txt
    String tournament = "Tournament";
    
    // The list's used to store data.
    public static ArrayList<Player> playerList    = new ArrayList<Player>();
    ArrayList<Game> gameList        = new ArrayList<Game>();
    ArrayList<Result> resultList    = new ArrayList<Result>();
    
    /** Metodeforklaringer -- MÅ SLETTES SENERE --
     * getTournament() collects the data from the "database"-file.
     * saceTournament() Saves the data to the "database"-file. 
     */
    
    @Override
    public void start(Stage primaryStage) {
        
        // The method that collects the data from the Binary file.
        getTournament();
        
        //----------------------------------Test of tournment---------------------------------
        // Test-input used when writing into the file.
        Player player   = new Player("Harry");
        Player player1  = new Player("Ron");
        Player player2  = new Player("Hermoine"); 
        Game game1      = new Game(player1, player2);
        Game game2      = new Game(player2, player1);
        Game game3      = new Game(player, player1);
        Result result1  = new Result(player1, player2, true);
        Result result2  = new Result(player2, player1, true);
        Result result3  = new Result(player, player1, false);
        
        playerList.add(player); playerList.add(player1); playerList.add(player2);
        gameList.add(game1); gameList.add(game2); gameList.add(game3);
        resultList.add(result1); resultList.add(result2); resultList.add(result3);
        
        // Test's the method that saves the tournment-data.
        // SaveTournament(playerList, gameList, resultList);
        
        //----------------------------------/Test of tournment---------------------------------
        
        // Setter her opp scene, for å ha en måte å lukke applikasjonen enklere på.
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, SCREEN_HEIGHT, SCREEN_WIDTH);
        primaryStage.setTitle("Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * This method is the one that saves the Arraylist's into the file
     * to store them.
     * @param players is the ArrayList of player-objects.
     * @param games is the ArrayList of game-objects.
     * @param results is the ArrayList of result-objects. 
     */
    public void saveTournament(List<Player> players, List<Game> games, List<Result> results) {
        
        try {
            
            // First the objects is written to the file that holds the binary data...
            FileOutputStream fileOut = new FileOutputStream(tournament + ".dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            // Stores the player-objects in the file.
            for (Player player : players) {
                objectOut.writeObject(player);
            }
            
            // The seperator's that makes it possible to use the while(true) below.
            objectOut.writeObject("/n");
            
            for (Game game : games) {
                objectOut.writeObject(game);
            }
            
            objectOut.writeObject("/n");
            
            for (Result result : results) {
                objectOut.writeObject(result);
            }
            
            objectOut.close();
            System.out.println("The Objects was succesfully written to a file in binary");
            
            // ...Then it creates the backup in clear text.
            BufferedWriter outStream = new BufferedWriter(new FileWriter(tournament + ".txt", true));
            
            for (Player player : players) {
                outStream.newLine();
                outStream.write(player.toString());
            }

            for (Game game : games) {
                outStream.newLine();
                outStream.write(game.toString());
            }
            
            for (Result result : results) {
                outStream.newLine();
                outStream.write(result.toString());
            }
            
            outStream.close();
            System.out.println("The Objects was succesfully written to a file in text");
            
        } catch (IOException ex) {
            Logger.getLogger(Admapp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The getTorunament is the method that collects the data from the 
     * binary file where it was stored, and places them into the list
     * that they should be in before the application really starts. 
     */
    public void getTournament() {
        
        try {
            // create an ObjectInputStream for the file we created before
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(tournament + ".dat"));
            
            // Objects to use when collecting the objects from the binary file.
            Player player = new Player();
            Game game = new Game();
            Result result = new Result();
            
            // Gathers the player-objects.
            while(true){
                try{
                    player = (Player) input.readObject();
                    System.out.println(player.name + " " + player.id);
                } catch (EOFException e){
                    break;
                } catch (WriteAbortedException ex) {
                    break;
                } catch (ClassCastException ex) {
                    break;
                }
            }
            
            // Gathers the game-objects.
            while(true){
                try{
                    game = (Game) input.readObject();
                    System.out.println(game.player1.name + " " + game.result);
                } catch (EOFException e){
                    break;
                } catch (WriteAbortedException ex) {
                    break;
                } catch (ClassCastException ex) {
                    break;
                }
            }
            
            // Gathers the result-objects.
            while(true){
                try{
                    result = (Result) input.readObject();
                    System.out.println(result.draw + " " + result.winner.name);
                } catch (EOFException e){
                    break;
                } catch (WriteAbortedException ex) {
                    break;
                } catch (ClassCastException ex) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
