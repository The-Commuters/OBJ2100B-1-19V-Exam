/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Result implements Serializable {
    
    Player winner;
    Player loser;
    Boolean draw;
    
    public Result() {
    }
    
    public Result(Player winner, Player loser, Boolean draw) {
        this.winner = winner;
        this.loser = loser;
        this.draw = draw;
    }
    
    @Override
    public String toString() {
        if (draw == true){
            return "Draw : " + winner.name + " & " + loser.name;
        }
        return " Winner: " + winner.name + " Loser: " + loser.name + " Draw: " + draw;
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
    //
    public void setDraw(Boolean draw) {
        this.draw = draw;
    }
}