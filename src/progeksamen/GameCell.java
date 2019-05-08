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
public class GameCell extends ListCell<Game> {
    
    @Override
    public void updateItem(Game item, boolean empty) {
        super.updateItem(item, empty);
 
        String name = item == null || empty ? null : item.getPlayer1().getName() + " VS " + item.getPlayer2().getName();
         
        setText(name);
        setGraphic(null);
        setCursor(Cursor.HAND);
        setPadding(new Insets(10, 20, 10, 20));
        getStyleClass().add("cell");
    }
}
