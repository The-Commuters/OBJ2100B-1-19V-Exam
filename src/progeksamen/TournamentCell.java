/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;

/**
 *
 * @author DavidNaist
 */
public class TournamentCell extends ListCell<Tournament> {
    
    @Override
    public void updateItem(Tournament item, boolean empty) {
        super.updateItem(item, empty);
 
        String name = "bob";
         
        setText(item == null || empty ? null : name);
        setGraphic(null);
        setCursor(Cursor.HAND);
        setPadding(new Insets(10, 20, 10, 20));
    }
}
