/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author DavidNaist
 */
public final class Menu extends BorderPane {
    
    // Constants
    public static final int DEFAULT_PADDING_HORIZONTAL = 20;
    public static final int DEFAULT_PADDING_VERTICAL = 10;
    
    // Constructor
    public Menu (Crumb crumb, Tools tool) {
        this(crumb, tool, DEFAULT_PADDING_HORIZONTAL, DEFAULT_PADDING_VERTICAL);
    }
    
    public Menu(Crumb crumb, Tools tool, int paddingHorizontal, int paddingVertical) {
        super(null, null, tool, null, crumb);
        setPadding(new Insets(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal));
        init();
    }
    
    // Methods
    public void init() {
        getStyleClass().add("menu");
    }
}
