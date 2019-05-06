/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

import java.util.Date;

public class Game {
    
    Player player1;
    Player player2;
    Result result;
    String[] moves;
    Date startDate;
    Date endDate;
    
    public Game () {}
    
    public Game (Player player1, Player player2, Result result, String[] moves, Date startDate, Date endDate) {
        
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.moves = moves;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
}
