/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.scene.layout.Pane;

/**
 *
 * @author DavidNaist
 */
class ChessFeed extends Pane {
    
    public ChessFeed(int size, String[] score, Field field) {
        setMinWidth(size);
        setMinHeight(size);
        getStyleClass().add("chess-feed");
    }
}
