/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Game implements Serializable{
    
    Player player1;
    Player player2;
    Result result;
    ArrayList<String> score;
    Date startDate;
    Date startTime;
    
    public Game () {}
    public Game (Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public Game (Player player1, Player player2, Result result, ArrayList<String> moves, Date startDate, Date startTime) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.score = moves;
        this.startDate = startDate;
        this.startTime = startTime;
    } 
    
    public void addMove(String move) {
       this.score.add(move);
    }
    
    public Boolean nameContainString(String input) {
        
        
        if (this.player1.name.toLowerCase().contains(input.toLowerCase()) || this.player2.name.toLowerCase().contains(input.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
     @Override
    public String toString(){
        
        String scoreString = "No moves was taken";
        // Make a long string with \n every 10 or so moves. 
        for(String move : score) {
        
        }
        
        return "Player 1: " + player1.name + 
                ", Player 2: " + player2.name + 
                ", " + result.toString() + 
                ", " + scoreString + 
                ", Startdate: " + startDate + 
                ", Starttime: " + startTime + ".";
    }
}
 


