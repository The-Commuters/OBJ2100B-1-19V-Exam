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
    static String myType = "Piece"; // this is changed in every construktor, but can be edited if locaisation somehow later is added
    
    protected static final int[] NUMBERS = {8, 7, 6, 5, 4, 3, 2, 1};
    protected static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public Piece(boolean white, int letterPos, int numberPos) {
        this.white = white;
        this.letterPos = letterPos;
        this.numberPos = numberPos;
    }
    
    @Override
    public String toString(){
        String out = "";
        out += getCollor() + " " + myType + " " + LETTERS[letterPos] + NUMBERS[numberPos];
        return out;
    }
    
    private String getCollor(){
        if (white){
            return "White";
        }
        else {
            return "Black";
        }
    }
    
    //this < is less-than      this > is greater than
    protected boolean validLetter(int cordinate){
        boolean valid = true;
        if (cordinate < 0 || cordinate > LETTERS.length-1){
            valid = false;
        }
        return valid;
    }
    
    protected boolean validNumber(int cordinate){
        boolean valid = true;
        if (cordinate < 0 || cordinate > NUMBERS.length-1){
            valid = false;
        }
        return valid;
    }
    
}
