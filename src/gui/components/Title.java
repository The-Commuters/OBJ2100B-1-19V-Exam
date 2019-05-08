/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import gui.tools.Back;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author DavidNaist
 */
public final class Title extends HBox {
    
    // Fields
    private Text title;
    private Button back;
    
    // Constants
    public static final boolean DEFAULT_BACK_STATE = false;
    public static final int DEFAULT_PADDING_HORIZONTAL = 20;
    public static final int DEFAULT_PADDING_VERTICAL = 20;

    // Constructors
    public Title(Text title) {
        this(title, DEFAULT_PADDING_HORIZONTAL, DEFAULT_PADDING_VERTICAL);
    }
    
    public Title(Text title, int paddingHorizontal, int paddingVertical) {
        this.title = title;
        
        setPadding(new Insets(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal));
        getStyleClass().add("title-pane");
        title.getStyleClass().add("title");  
        
        update();
    }
    
    // Getter
    public Text getTitle() {
        return title;
    }
    
    public Button getButton() {
        return back;
    }
    
    // Setter
    public void setTitle(Text title) {
        if (title.getText().length() > 0) {
            this.title = title;
        }
    }
    
    // Methods
    public void addButton(Button back) {
        this.back = back;
        update();
    }
    
    public void deleteButton(Button back) {
        this.back = null;
        update();
    }
    
    private boolean titleHasButton() {
        return getButton() != null;
    }
    
    private void update() {
        getChildren().clear();
        if (titleHasButton()) {
            getChildren().add(getButton());
        }
        getChildren().add(getTitle());
              
    }
}
