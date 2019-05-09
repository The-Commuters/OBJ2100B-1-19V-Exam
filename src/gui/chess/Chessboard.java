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
    
    // Constructor
    public Chessboard(int size, Field field) {
        setMaxWidth(size);
        setMaxHeight(size);
        getChildren().add(new Board(size));
        getChildren().add(field);
    }
}
