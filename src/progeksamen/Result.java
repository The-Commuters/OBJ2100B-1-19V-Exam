/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

public class Result implements Serializable {
    
    private Player winner;
    private Player loser;
    private Boolean draw;
    
    public Result(Player winner, Player loser, Boolean draw) {
        this.winner = winner;
        this.loser = loser;
        this.draw = draw;
    }

    /**
     * This is called upon every time a result-object is created to be
     * inserted into the result-list. It will add the earned points for
     * victories and draws to the players that have earned them.
     */
    public void addPointsToPlayers (Game game) {
        
        // Collects the public static list of players.
        //ArrayList<Player> resultPlayerList = ;
        
        // Goes trough all of the players in the playerlist and gi
        /*resultPlayerList.forEach((player) -> {
            if (this.draw) {
                if (player.getName() == this.winner.getName() || player.getName() == this.loser.getName()) {
                    
                    double newScore = player.getScore()+0.5;
                    player.setScore(newScore);
                }
            } else {
                if(player.getName() == this.winner.getName()) {
                    double newScore = player.getScore()+1;
                    player.setScore(newScore);
                }
            }
        });*/
    }
    
    @Override
    public String toString() {
        if (draw){
            return "Draw Between: " + winner.getName() + " & " + loser.getName();
        } else {
            return "Winner: " + winner.getName() + " Loser: " + loser.getName() + " Draw: " + draw;
        }
    }
    
    public String getResult(){
      return winner.getPlayer() + " & " + loser.getPlayer();
    }
    
    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getLoser() {
        return loser;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public Boolean getDraw() {
        return draw;
    }

    public void setDraw(Boolean draw) {
        this.draw = draw;
    }
}