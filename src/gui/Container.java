/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author DavidNaist
 */
public final class Container extends StackPane {
    
    // Constants
    public static final int DEFAULT_MIN_WIDTH = 800;
    public static final int DEFAULT_MIN_HEIGHT = 600;

    // Constructor
    public Container() {
        this(DEFAULT_MIN_WIDTH, DEFAULT_MIN_HEIGHT);
    }
    
    public Container(int minWidth, int minHeight) {
        setMinSize(minWidth, minHeight);
    }
    
    // Getters
    public void pop() {
        getChildren().remove(getChildren().size() - 1);
    }
    
    // Setters
    public void put(Pane pane) {
        getChildren().add(pane);
    }
    
    
    
    
    
    
    
    
}
