/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author DavidNaist
 */
public class GameCell extends ListCell<Game> {
    
    @Override
    public void updateItem(Game item, boolean empty) {
        super.updateItem(item, empty);
        
        if (item == null || empty) {
            setText(null);
        }
        else {
            String text;
            String players = item.getPlayer1().getName() + " VS " + item.getPlayer2().getName() + "\n";
            String time;
            if (item.getGameState()) {
                time = "Started: " + item.getStartDate() + ", " + item.getStartTime() + "\n";
                if (item.getResult().getDraw()) {
                    time += "Game is completed, It ended in a draw.";
                } else {
                    time += "Game is completed, " + item.getResult().getWinner() + " is the winner!";
                }
                
            }
            else {
                if (new Date().before(item.getStartDate())) {
                    time = "Started: " + item.getStartDate() + ", " + item.getStartTime() + "\n";
                    time +=  "Game starts in " + (long)TimeUnit.DAYS.convert(item.getStartDate().getTime() - new Date().getTime(), TimeUnit.MILLISECONDS) + " days"; 
                }
                else {
                    time = "Started: " + item.getStartDate() + ", " + item.getStartTime() + "\n";
                    time += "Game is being played right now!";
                }
                
                        
            }
            
            text = players + time;
            setText(text);
        }
        setGraphic(null);
        setCursor(Cursor.HAND);
        setPadding(new Insets(10, 20, 10, 20));
        getStyleClass().add("cell");
    }
}
