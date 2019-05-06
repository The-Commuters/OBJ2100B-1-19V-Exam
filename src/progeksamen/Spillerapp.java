/*
 * Main
 */
package progeksamen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Spillerapp extends Application implements Constants {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        // Setter her opp scene, for å ha en måte å lukke applikasjonen enklere på.
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, SCREEN_HEIGHT, SCREEN_WIDTH);
        primaryStage.setTitle("Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
