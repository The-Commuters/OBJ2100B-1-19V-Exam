/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import javafx.scene.layout.StackPane;

/**
 *
 * @author DavidNaist
 */
public final class Page extends StackPane {
    
    // Constructor
    public Page(Body body) {
        getChildren().add(body);
    }
    
}
