/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author DavidNaist
 */
class ChessFeed extends Pane {
    
    public ChessFeed(int size, String[] score, SequentialTransition chessSim) {
        setMinWidth(size);
        setMinHeight(size);
        getStyleClass().add("chess-feed");
        
        Button play = new Button("Play");
        play.setOnAction((e) -> {
            chessSim.play();
        });
        getChildren().add(play);
        
    }
}
