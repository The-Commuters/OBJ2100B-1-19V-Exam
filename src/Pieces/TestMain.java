/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import static Pieces.Piece.LETTERS;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import progeksamen.*;

/**
 *
 * @author Mads Hagen
 */
public class TestMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       
        
        String[] testSetSAN = {"e4 e5", "Nf3 Nc6", "Bb5 a6"};
        String[] testSet = {"e2-e4 e7-e5", "Bf1-c4 Bf8-c5", "Qd1-f3 Nb8-c6"};
        Move[] m = Parser.parseLANArray(testSet);
        System.out.println("starting printout" + m[0].toString());
        System.out.println(m[1].toString());
        System.out.println(m[2].toString());
        
        
        /*
        String testData = "3 becomes " + numberToCordinate(3);
        for (int x = 1; x < 9; x++){
            testData += " " +numberToCordinate(x);
        }
        */
        
        
   
        //System.out.println(testData);
        
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
