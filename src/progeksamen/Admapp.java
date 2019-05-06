/*
 * Main for the Admin application.
 */
package progeksamen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Admapp extends Application implements Constants {
    
    @Override
    public void start(Stage primaryStage) {
        
        Player player = new Player("Harry");
        
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
    public Boolean saveTournament(Player[] players, Game[] games) {
        
        try {
            
            // Add a string to the file here to seperate blocks?
            
            // Go trough the players in player[] and saves them to the file.
            for (Player player : players) {
                player.savePlayer();
            }
            
            // Add a string to the file here to seperate blocks?
            
            // Does so for every type of object, like game, moves etc.
            for (Game game : games) {
                game.saveGame();
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
