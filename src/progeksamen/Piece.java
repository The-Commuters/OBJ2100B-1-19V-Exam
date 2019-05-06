/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progeksamen;

/**
 *
 * @author Mads Hagen
 */
public class Piece {
    boolean white;
    int letterPos; // letters acend along the X-axis, starting at 0/a ending at 7/h
    int numberPos; // numbers decend along the Y-axis, starting at 0/8 ending at 7/1
    
    private static final int[] NUMBERS = {8, 7, 6, 5, 4, 3, 2, 1};
    private static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public Piece(boolean white, int letterPos, int numberPos) {
        this.white = white;
        this.letterPos = letterPos;
        this.numberPos = numberPos;
    }
    
    
}
