/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
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
        
        Button play = new Button("Play");
        //play.setGraphic(GRAPHIC);
        play.getStyleClass().add("tool");
        play.setCursor(Cursor.HAND);
        play.setOnAction((e) -> {
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
            chessSim.play();
            
        });
        
        Button pause = new Button("Pause");
        //pause.setGraphic(GRAPHIC);
        pause.getStyleClass().add("tool");
        pause.setCursor(Cursor.HAND);
        pause.setOnAction((e) -> {
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
            
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
            chessSim.pause();
            
        });
        
        Button first = new Button("First Move");
        //first.setGraphic(GRAPHIC);
        first.getStyleClass().add("tool");
        first.setCursor(Cursor.HAND);
        first.setOnAction((e) -> {
            setCuePoint(0);
            
            chessSim.play();
            chessSim.jumpTo(getTimeStep().multiply(getCuePoint()));
            chessSim.pause();
            
            setCuePoint((int)Math.floor(chessSim.getCurrentTime().divide(500).toMillis()));
        });
        
        Button prev = new Button("prev");
        //prev.setGraphic(GRAPHIC);
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
        
        
        Button next = new Button("Next");
        //next.setGraphic(GRAPHIC);
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
        
        Button last = new Button("Last move");
        //last.setGraphic(GRAPHIC);
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

        HBox controllers = new HBox(play, pause , last, prev, next, first);
        return controllers;
    }
    
    
}
