/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import javafx.animation.Timeline;
import javafx.scene.layout.HBox;

/**
 *
 * @author DavidNaist
 */
public class ChessSimulator extends HBox {
    
    // Fields
    int size;
    Timeline score;
    
    // Constructor
    public ChessSimulator(int size, String... san) {
        this.size = size;
        makeTimeline();
    }
    
    // Methods
    private Timeline makeTimeline() {
        
        
        
        return new Timeline();
    }
}
