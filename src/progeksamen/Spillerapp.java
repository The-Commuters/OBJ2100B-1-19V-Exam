/*
 * Main
 */
package progeksamen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Spillerapp extends Application implements Constants {
    
    // Current string for the Tournament, needed to find/create the .dat to store binary And the .txt.
    public static String tournament = "Tournament";
    
        // The list's used to store data.
    public static ArrayList<Player> playerList    = new ArrayList<Player>();
    public static ArrayList<Game> gameList        = new ArrayList<Game>();
    public static ArrayList<Result> resultList    = new ArrayList<Result>();
    
    @Override
    public void start(Stage primaryStage) {
        
        // The method that collects the data from the Binary file.
        getTournament();
        
        // Setter her opp scene, for å ha en måte å lukke applikasjonen enklere på.
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, SCREEN_HEIGHT, SCREEN_WIDTH);
        primaryStage.setTitle("Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
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
            
            // Collects the player's from the storage-file.
            try{
                
                gameList = (ArrayList<Game>) input.readObject();
                playerList = (ArrayList<Player>) input.readObject();
                resultList = (ArrayList<Result>) input.readObject();
                
            } catch (ClassCastException | ClassNotFoundException e){
            }
        } catch (IOException ex) {}
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
