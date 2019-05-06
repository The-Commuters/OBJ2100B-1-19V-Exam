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
        
        
        Scene scene = new Scene(root);
        
        
        primaryStage.setTitle("ChessX");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
