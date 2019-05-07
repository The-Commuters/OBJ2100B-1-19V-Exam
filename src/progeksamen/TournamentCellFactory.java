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
public class TournamentCellFactory implements Callback<ListView<Tournament>, ListCell<Tournament>> {
    @Override
    public ListCell<Tournament> call(ListView<Tournament> listview) {
        return new TournamentCell();
    }
}
