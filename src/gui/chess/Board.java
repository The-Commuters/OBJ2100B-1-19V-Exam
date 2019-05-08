/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author DavidNaist
 */
public class Board extends ImageView {
    
    // Constructors
    protected Board(int size) {
        super(new Image("gui/images/chessboard.jpg", size, 0, true, true, true));
    }
}
