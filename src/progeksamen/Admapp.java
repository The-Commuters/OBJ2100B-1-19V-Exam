/*
 * Main for the Admin application.
 */
package progeksamen;

import java.io.File;
import java.io.IOException;
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
    
    @Override
    public void start(Stage primaryStage) {
        
        // The list's used to store data.
        List<Player> playerList = new ArrayList<Player>();
        List<Game> gameList = new ArrayList<Game>();
        List<Result> resultList = new ArrayList<Result>();
        
        //----------------------------------Test of tournment---------------------------------
        // Test-input used when writing into the file.
        Player player = new Player("Harry");
        Player player1 = new Player("Ron");
        Player player2 = new Player("Hermoine"); 
        Game game1 = new Game(player1, player2);
        Game game2 = new Game(player2, player1);
        Game game3 = new Game(player, player1);
        Result result1 = new Result(player1, player2, true);
        Result result2 = new Result(player2, player1, true);
        Result result3 = new Result(player, player1, false);
        playerList.add(player); playerList.add(player1); playerList.add(player2);
        gameList.add(game1); gameList.add(game2); gameList.add(game3);
        resultList.add(result1); resultList.add(result2); resultList.add(result3);
        
        // Test's the method that saves the tournment-data.
        saveTournament(playerList, gameList, resultList);
        //----------------------------------/Test of tournment---------------------------------
        
        
        // Setter her opp scene, for å ha en måte å lukke applikasjonen enklere på.
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, SCREEN_HEIGHT, SCREEN_WIDTH);
        primaryStage.setTitle("Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
         
    }
    
    // ? Something like this to save the tournment at the end ?
    // Collects the object-arrays that have been added to after being
    // Collected at the start of the 'Session'.
    public Boolean saveTournament(List<Player> players, List<Game> games, List<Result> results) {
        
        try {
            
            // Add a string to the file here to seperate blocks?
            
            // CHANGE TO FULL ARRAYLIST WRITE LATER.
            
            for (Player player : players) {
                player.savePlayer(tournament);
            }
            
            // Add a string to the file here to seperate blocks?
            
            for (Game game : games) {
                game.saveGame(tournament);
            }
            
            // Add a string to the file here to seperate blocks?
            
            for (Result result : results) {
                result.saveGame(tournament);
            }
            
            return true;    // The process were successful.
        } catch (IOException ex) {
            Logger.getLogger(Admapp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;  // The process were unsuccessful.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
