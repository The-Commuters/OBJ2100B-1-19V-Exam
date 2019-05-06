/*
 * Main
 */
package progeksamen;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.WriteAbortedException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Spillerapp extends Application implements Constants {
    
    // Current string for the Tournament, needed to find/create the .dat to store binary And the .txt.
    public static String tournament = "Tournament";
    
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
            
            // Objects to use when collecting the objects from the binary file.
            Player player = new Player();
            Game game = new Game();
            Result result = new Result();
            
            // Gathers the player-objects.
            while(true){
                try{
                    // places the retrieved object in player.
                    player = (Player) input.readObject();
                    // Places player in the player-list.
                    playerList.add(player);
                    // Shows it in the console, REMOVE LATER!
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
                    gameList.add(game);
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
                    resultList.add(result);
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
