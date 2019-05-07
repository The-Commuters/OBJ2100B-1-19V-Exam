/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.Body;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author DavidNaist
 */
public class Title extends HBox {
    
    // Fields
    private Text title;
    
    // Constants
    public static final int DEFAULT_PADDING_HORIZONTAL = 20;
    public static final int DEFAULT_PADDING_VERTICAL = 20;

    // Constructors
    public Title(Text title) {
        this(title, DEFAULT_PADDING_HORIZONTAL, DEFAULT_PADDING_VERTICAL);
    }
    
    public Title(Text title, int paddingHorizontal, int paddingVertical) {
        this.title = title;
        setPadding(new Insets(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal));
        
        init();
    }
    
    // Getter
    public String getTitle() {
        return title.getText();
    }
    
    // Methods
    private void init() {
        // Adds Classes
        getStyleClass().add("title-pane");
        title.getStyleClass().add("title");
        
        // Adds the title to the Pane
        getChildren().add(title);
    }
}
