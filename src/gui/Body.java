/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author DavidNaist
 */
public class Body extends BorderPane {
    
    // Fields
    private String name;
    
    // Constructors
    public Body(String name) {
        this(name, null, null);
    }
    
    public Body(String name, Pane header, Pane main) {
        super(main, header, null, null, null);
        this.name = name;
    }
    
    // Getters
    public String getName() {
        return name;
    } 
    
    // Methods
    public void init() {
        getStyleClass().add("body");
    }
    
}
