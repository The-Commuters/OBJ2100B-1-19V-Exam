/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {
    
    Player winner;
    Player loser;
    Boolean draw;
    
    public Result(Player winner, Player loser, Boolean draw) {
        this.winner = winner;
        this.loser = loser;
        this.draw = draw;
        
        // The method that 
        addPointsToPlayers();
    }
    
    /**
     * This is called upon every time a result-object is created to be
     * inserted into the result-list. It will add the earned points for
     * victories and draws to the players that have earned them.
     */
    public void addPointsToPlayers () {
        
        // Collects the public static list of players.
        ArrayList<Player> resultPlayerList = Admapp.playerList;
        
        // Goes trough all of the players in the playerlist and gi
        resultPlayerList.forEach((player) -> {
            if (this.draw) {
                if (player.name == this.winner.name || player.name == this.loser.name) {
                    player.score += 0.5;
                }
            } else {
                if(player.name == this.winner.name) {
                    player.score += 1;
                }
            }
        });
        
        // The new and updated list with scores is placed in the playerList.
        Admapp.playerList = resultPlayerList;
    }
    
    @Override
    public String toString() {
        if (draw){
            return "Draw Between: " + winner.name + " & " + loser.name;
        } else {
            return "Winner: " + winner.name + " Loser: " + loser.name + " Draw: " + draw;
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