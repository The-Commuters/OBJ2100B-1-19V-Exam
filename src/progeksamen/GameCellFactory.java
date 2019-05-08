/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author DavidNaist
 */
public class GameCellFactory implements Callback<ListView<Game>, ListCell<Game>>  {
    @Override
    public ListCell<Game> call(ListView<Game> listview){
        return new GameCell();
    }
}
