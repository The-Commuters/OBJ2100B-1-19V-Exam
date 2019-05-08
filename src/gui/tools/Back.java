/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tools;

import gui.components.Container;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author DavidNaist
 */
public class Back extends Button {
    
    // Constants
    private final ImageView GRAPHIC = new ImageView(new Image("gui/images/back.png", 20, 0, true, true, true)); 

    // Constructors
    public Back(Container container) {
        super("");
        setGraphic(GRAPHIC);
        getStyleClass().add("tool");
        setCursor(Cursor.HAND);
        
        setOnAction((ActionEvent e) -> {
            container.pop();
        });
    }
}
