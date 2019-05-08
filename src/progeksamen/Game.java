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
    
    private Player player1;
    private Player player2;
    private Result result;
    private ArrayList<String> score;
    private Date startDate;
    private Date startTime;
    
    public Game () {}
    public Game (Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game (Player player1, Player player2, Result result, Date startDate, Date startTime) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.startDate = startDate;
        this.startTime = startTime;
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
        this.getScore().add(move);
    }
    
    public Boolean nameContainString(String input) {
        
        if (this.getPlayer1().getName().toLowerCase().contains(input.toLowerCase()) || this.getPlayer2().getName().toLowerCase().contains(input.toLowerCase())) {
            return true;
        }
        
        return false;
    }
    
     @Override
    public String toString(){
        
        String scoreString = "No moves was taken";
        // Make a long string with \n every 10 or so moves. 
        for(String move : getScore()) {
        
        }
        
        return "Player 1: " + getPlayer1().getName() + 
                ", Player 2: " + getPlayer2().getName() + 
                ", " + getResult().toString() + 
                ", " + scoreString + 
                ", Startdate: " + getStartDate() + 
                ", Starttime: " + getStartTime() + ".";
    }

    /**
     * @return the player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return the score
     */
    public ArrayList<String> getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(ArrayList<String> score) {
        this.score = score;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
 


