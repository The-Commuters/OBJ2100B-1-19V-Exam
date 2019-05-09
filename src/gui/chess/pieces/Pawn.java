/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess.pieces;

import javafx.scene.image.Image;

/**
 *
 * @author DavidNaist
 */
public class Pawn extends Piece {
    
    // Constructors
    public Pawn(int size, boolean isBlack) {
        super(new Image(isBlack ? "gui/images/pawn-black.png" : "gui/images/pawn-white.png", size, 0, true, false, true));
    }
}
