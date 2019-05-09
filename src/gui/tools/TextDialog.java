/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tools;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

    
public class TextDialog extends Dialog {
    
    // Constants
   private Optional<ButtonType> result;
   private String out;

    // Constructors
   
   
   /** This constuctor will take in the 3 Strings from 
     * the method caller and use them in the dialog 
     * that will be shown to the use
     * @param HeaderTxt
     * @param tittle
     * @param warning
     */ 
    public TextDialog(String HeaderTxt, String tittle, String warning){
        super();
         
        
        this.initStyle(StageStyle.UTILITY);
        this.setHeaderText(HeaderTxt);
        this.setTitle(tittle);
       
        TextField text = new TextField();
        text.setMinWidth(50);
        this.getDialogPane().setContent(text);
        this.setHeight(100);
        this.setContentText(HeaderTxt);
        this.getDialogPane().getButtonTypes().add(ButtonType.OK);
        this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        out = "";
        Boolean ok = false;
        Alert alert = new Alert(Alert.AlertType.WARNING, warning);
        while (!ok) {
            result = this.showAndWait();
            if (!text.getText().isEmpty() && result.isPresent() && result.get() == ButtonType.OK) {
                out = text.getText();
                ok = true;
            } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                this.close();
                ok = true;
            } else {
                alert.showAndWait();
            }
        }
        
    }
       
         @Override
    public String toString(){
    
        return out; 
    } 
        
    }
        
        
    

