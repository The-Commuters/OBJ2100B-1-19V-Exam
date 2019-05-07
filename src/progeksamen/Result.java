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
     * The game object is sent here after it is clicked on in the 
     * Game list, in this method the administrator have to choose
     * if the game ended in a draw or a win, and if win, then who
     * won.
     * @param game 
     */
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
            Result resultWin = new Result(game.player1, game.player2, true);
            game.result = resultWin;
            System.out.println(game.result);
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
        ButtonType playerBtnOne = new ButtonType(game.player1.name);
        ButtonType playerBtnTwo = new ButtonType(game.player2.name);
        ButtonType cancelBtn = new ButtonType("Cancel"); 

        alert.getButtonTypes().setAll(playerBtnOne, playerBtnTwo, cancelBtn);
        
        // finds out which of the users that have been choosen.
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == playerBtnOne){
            
            Result resultWin = new Result(game.player1, game.player2, false);
            game.result = resultWin;
            alert("Success", "The player " + game.player1.name + " have been choosen as the winner, and have recieved a point.");
            System.out.println(game.result.winner);  
            
        } else if (result.get() == playerBtnTwo) {  
            
            Result resultWin = new Result(game.player2, game.player1, false);
            game.result = resultWin;
            alert("Success","The player " + game.player2.name + " have been choosen as the winner, and have recieved a point.");
            System.out.println(game.result.winner);
            
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

        // alert.setHeaderText("Results:");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.showAndWait();

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