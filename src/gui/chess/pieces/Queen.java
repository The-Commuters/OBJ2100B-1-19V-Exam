/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess.pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author DavidNaist
 */
public class Queen extends ImageView {
    
    // Constructors
    public Queen(int size, boolean isBlack) {
        super(new Image(isBlack ? "gui/images/queen-black.png" : "gui/images/queen-white.png", size, 0, true, false, true));
    }
}
