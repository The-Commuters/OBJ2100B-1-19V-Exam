/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Game implements Serializable{
    
    private Player player1 = null;
    private Player player2 = null;
    private Result result = null;
    private ArrayList<String> score = null;
    private Date startDate = null;
    private Date startTime = null;
    
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
    

 public void handleGameResult(Game game) {
        
        // The alert is set up and styled.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Did someone win, or did the game end in a draw?");
        alert.setHeaderText(null);
        
        // The buttons that is used is created here.
        ButtonType playerBtnOne = new ButtonType("Draw");
        ButtonType playerBtnTwo = new ButtonType("Choose a winner"); 
        ButtonType cancelBtn = new ButtonType("Cancel"); 
        alert.getButtonTypes().setAll(playerBtnOne, playerBtnTwo, cancelBtn);
     
        // If the game ended in a draw or not.
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == playerBtnOne){
            alert("Title.", "A draw have successfully been registered between the two players, and each of them have been rewarded half a point! ");
            Result resultWin = new Result(game.getPlayer1(), game.getPlayer2(), true);
            game.setResult(resultWin);
            System.out.println(game.getResult());
            
            // Finds the players in the player-lists inside of the tournament.
            
            //Runs first trough the list of tournaments, and the games.
            for (Tournament tournament : Data.tournaments) {
                if(tournament.getGames().contains(game)){
                    // Gets the index of the tournament in the arraylist.
                    int indexTournament = Data.tournaments.indexOf(tournament);
                    // Gets the index of the player in the player-list of the tournament.
                    int indexPlayer1 = tournament.getPlayers().indexOf(game.getPlayer1());
                    int indexPlayer2 = tournament.getPlayers().indexOf(game.getPlayer2());
                    // Gets the old score of the player.
                    double prevScorePlayer1 = tournament.getPlayers().get(indexPlayer1).getScore();
                    double prevScorePlayer2 = tournament.getPlayers().get(indexPlayer2).getScore();
                    // Adds the new point to the players score.
                    tournament.getPlayers().get(indexPlayer1).setScore(prevScorePlayer1+1);
                    tournament.getPlayers().get(indexPlayer2).setScore(prevScorePlayer2+1);
                    // Sets the new tournament object back at the place where it was.
                    Data.tournaments.set(indexTournament, tournament);
                    
                    break;
                }
            }
            
        } else if (result.get() == playerBtnTwo) {
            handleChooseWinner(game);
        } else if (result.get() == cancelBtn) {
        }
    }
    
    /**
     * This is called in handleGameResult() if the player choose the
     * option where one of the players won. Here the administrator 
     * have to choose which one.
     * @param game 
     */
    public void handleChooseWinner(Game game) {
    
        // Creates the alert that makes you choose who won and styles it.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Which of the two players won the game?");
        alert.setGraphic(null);
        alert.setHeaderText(null);
        
        // Adds the buttons to the alert.
        ButtonType playerBtnOne = new ButtonType(game.getPlayer1().getName());
        ButtonType playerBtnTwo = new ButtonType(game.getPlayer2().getName());
        ButtonType cancelBtn = new ButtonType("Cancel"); 

        alert.getButtonTypes().setAll(playerBtnOne, playerBtnTwo, cancelBtn);
        
        // finds out which of the users that have been choosen.
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == playerBtnOne){
            
            Result resultWin = new Result(game.getPlayer1(), game.getPlayer2(), false);
            alert("Success", "The player " + game.getPlayer1().getName() + " have been choosen as the winner, and have recieved a point.");
            
            // Finds the players in the player-lists inside of the tournament.
            
            for (Tournament tournament : Data.tournaments) {
                if(tournament.getGames().contains(game)){
                    // Gets the index of the tournament in the arraylist.
                    int indexTournament = Data.tournaments.indexOf(tournament);
                    // Gets the index of the player in the player-list of the tournament.
                    int index = tournament.getPlayers().indexOf(game.getPlayer1());
                    // Gets the old score of the player.
                    double prevScore = tournament.getPlayers().get(index).getScore();
                    // Adds the new point to the players score.
                    tournament.getPlayers().get(index).setScore(prevScore+1);
                    // Sets the new tournament object back at the place where it was.
                    Data.tournaments.set(indexTournament, tournament);
                    
                    break;
                }
            }
            
        } else if (result.get() == playerBtnTwo) {  
            
            Result resultWin = new Result(game.getPlayer2(), game.getPlayer1(), false);
            game.setResult(resultWin);
            alert("Success","The player " + game.getPlayer2().getName() + " have been choosen as the winner, and have recieved a point.");
            
            // Finds the players in the player-lists inside of the tournament.
            
            //Runs first trough the list of tournaments, and the games.
            for (Tournament tournament : Data.tournaments) {
                if(tournament.getGames().contains(game)){
                     // Gets the index of the tournament in the arraylist.
                    int indexTournament = Data.tournaments.indexOf(tournament);
                    // Gets the index of the player in the player-list of the tournament.
                    int index = tournament.getPlayers().indexOf(game.getPlayer1());
                    // Gets the old score of the player.
                    double prevScore = tournament.getPlayers().get(index).getScore();
                    // Adds the new point to the players score.
                    tournament.getPlayers().get(index).setScore(prevScore+1);
                    // Sets the new tournament object back at the place where it was.
                    Data.tournaments.set(indexTournament, tournament);
                    
                    break;
                }
            }
        } else if (result.get() == cancelBtn) {
        }
    }
        

    
     /**
     * The alert method is used to show simple alerts of the type
     * information, it recieves teh title and message as parameters
     * and is used when handling the insertion of results.
     * @param title
     * @param message 
     */
    public void alert( String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.showAndWait();
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
        //for(String move : getScore()) {
        
        //}
        
        return "Player 1: " + getPlayer1().getName() + 
                ", Player 2: " + getPlayer2().getName();
        
              /* return "Player 1: " + getPlayer1().getName() + 
                ", Player 2: " + getPlayer2().getName() + 
                ", " + getResult().toString() + 
                ", " + scoreString + 
                ", Startdate: " + getStartDate() + 
                ", Starttime: " + getStartTime() + ".";*/
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
 


