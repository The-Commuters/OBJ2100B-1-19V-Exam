/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable{
    
    Player player1;
    Player player2;
    Result result;
    String[] score;
    Date startDate;
    Date endDate;
    
    public Game () {}
    public Game (Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public Game (Player player1, Player player2, Result result, String[] moves, Date startDate, Date endDate) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.score = score;
        this.startDate = startDate;
        this.endDate = endDate;
    } 
    
    
     @Override
    public String toString(){
    
        return player1.name + " VS " + player2.name;
    }
}
 


