/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.scene.layout.StackPane;

/**
 *
 * @author DavidNaist
 */
final class Chessboard extends StackPane {
    
    // Fields
    int size;
    
    // Constructor
    public Chessboard() {
        
        getChildren().add(new Board(getSize()));
        getChildren().add(new Field());
    }
    
    // Getters
    public int getSize() {
        return size;
    }
    
    // Setters
    public void setSize(int size) {
        this.size = size;
    }
}
