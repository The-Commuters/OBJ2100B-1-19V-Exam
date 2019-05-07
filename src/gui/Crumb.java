/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.text.Text;

/**
 *
 * @author DavidNaist
 */
public class Crumb extends Text {
    
    // Constructors
    public Crumb(String... crumbs) {
        
        for (String crumb: crumbs) {
            setText(crumbs.length > 1 ? getText() + " > " + crumb : crumb);
        }
        
        init();
    }
    
    // Methods
    private void init() {
        getStyleClass().add("crumb");
    }
        
        
        
        
        
        
        
}
