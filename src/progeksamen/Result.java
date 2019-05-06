/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;


public class Result {
    
    Player winner;
    Player loser;
    Boolean draw;
    
    public Result(Player winner, Player loser, Boolean draw) {
        this.winner = winner;
        this.loser = loser;
        this.draw = draw;
    }
}
