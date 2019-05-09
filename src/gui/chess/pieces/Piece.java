/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess.pieces;

import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author DavidNaist
 */
public class Piece extends ImageView {
    
    // Fields
    private Point tracker;
    
    // Constructor
    public Piece() {
        super();
    }
    
    public Piece(Image image) {
        super(image);
    }
    
    public void setTracker(Point tracker) {
        this.tracker = tracker;
    }
    
    public Point getTracker() {
        return tracker;
    }
    
}
