/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author DavidNaist
 */
class ChessFeed extends BorderPane {
    
    private int cuePoint = 0;
    private final Duration timeStep = Duration.millis(500);
    
    public ChessFeed(int size, String[] score, SequentialTransition chessSim) {
        setMinWidth(size);
        setMinHeight(size);
        getStyleClass().add("chess-feed");
        
        // Text blie display center
        
        HBox controllers = createControllers(chessSim);
        controllers.setAlignment(Pos.CENTER_RIGHT);
        setBottom(controllers);
        
        
    }

    // Getters
    public void setCuePoint(int cuePoint) {
        this.cuePoint = cuePoint;
    }

    // Getters
    public int getCuePoint() {
        return cuePoint;
    }

    public Duration getTimeStep() {
        return timeStep;
    }
    
    // Methods
    private HBox createControllers(SequentialTransition chessSim) {
        
        Button play = new Button("");
        play.setGraphic(new ImageView(new Image("gui/images/play.png", 20, 0, true, true, true)));
        play.getStyleClass().add("tool");
        play.setCursor(Cursor.HAND);
        play.setOnAction((e) -> {
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            

            chessSim.play();   
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
        });
        
        Button pause = new Button("");
        pause.setGraphic(new ImageView(new Image("gui/images/pause.png", 20, 0, true, true, true)));
        pause.getStyleClass().add("tool");
        pause.setCursor(Cursor.HAND);
        pause.setOnAction((e) -> {
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
            chessSim.pause();
            
        });
        
        Button first = new Button("");
        first.setGraphic(new ImageView(new Image("gui/images/first.png", 20, 0, true, true, true)));
        first.getStyleClass().add("tool");
        first.setCursor(Cursor.HAND);
        first.setOnAction((e) -> {
            setCuePoint(0);
            
            chessSim.play();
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
            chessSim.pause();
            
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
        });
        
        Button prev = new Button("");
        prev.setGraphic(new ImageView(new Image("gui/images/prev.png", 20, 0, true, true, true)));
        prev.getStyleClass().add("tool");
        prev.setCursor(Cursor.HAND);
        prev.setOnAction((e) -> {
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            
            if (getCuePoint() > 0) {
                setCuePoint(getCuePoint() - 1);
                chessSim.play();
                chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
                chessSim.pause();
            }
            
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
        });
        
        
        Button next = new Button("");
        next.setGraphic(new ImageView(new Image("gui/images/next.png", 20, 0, true, true, true)));
        next.getStyleClass().add("tool");
        next.setCursor(Cursor.HAND);
        next.setOnAction((e) -> {
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            
            // if (getCuePoint() < score.length)
            if (getCuePoint() < 5) {
                setCuePoint(getCuePoint() + 1);
                chessSim.play();
                chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
                chessSim.pause();
            }
            
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
        });
        
        Button last = new Button("");
        last.setGraphic(new ImageView(new Image("gui/images/last.png", 20, 0, true, true, true)));
        last.getStyleClass().add("tool");
        last.setCursor(Cursor.HAND);
        last.setOnAction((e) -> {
            
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            // setCuePoint(score.length);
            setCuePoint(5);
            
            chessSim.play();
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
            chessSim.pause();
 
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
        });

        HBox controllers = new HBox(play, pause , first, prev, next, last);
        controllers.setPadding(new Insets(10, 10, 10, 10));
        return controllers;
    }
    
    
}
