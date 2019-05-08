/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import progeksamen.IllegalMoveException;

/**
 *
 * @author Mads Hagen
 */
public abstract class Piece {
    boolean white;
    int letterPos; // letters acend along the X-axis, starting at 0/a ending at 7/h
    int numberPos; // numbers decend along the Y-axis, starting at 0/8 ending at 7/1
    static String myType = "Piece"; // this is changed in every construktor, but can be edited if locaisation somehow later is added
    
    public static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    public static final int[] NUMBERS = {8, 7, 6, 5, 4, 3, 2, 1};
    

    public Piece(boolean white, int letterPos, int numberPos) {
        this.white = white;
        this.letterPos = letterPos;
        this.numberPos = numberPos;
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
    
    protected boolean validHorisontal(int newNumberPos){
        return newNumberPos == numberPos;
    }
    
    // takes inn adjustments from letterPos and numberPos, cheks if a set of cordinates are corekt relative to the piece that uses it
    protected boolean validRelativeToSelf(int verticalTarget, int horisontalTarget, int verticalAdjustment, int horisontalAdjustment){
        return ( (verticalTarget == letterPos + verticalAdjustment) && (horisontalTarget == numberPos + horisontalAdjustment) );
    }
    
    protected boolean validTilted(int verticalTarget, int horisontalTarget){
        for (int number: NUMBERS){
            System.out.println(number);
            if (    validRelativeToSelf(verticalTarget, horisontalTarget, number, number) ||
                    validRelativeToSelf(verticalTarget, horisontalTarget, number, number*-1) ||
                    validRelativeToSelf(verticalTarget, horisontalTarget, number*-1, number) ||
                    validRelativeToSelf(verticalTarget, horisontalTarget, number*-1, number*-1) ){
                return true;
            }
        }
        return false;
    }
    
    protected boolean validLine(int newLetterPos, int newNumberPos){
        boolean validTarget = false;
        
        // if you are vertical not horisontal
            if (!validHorisontal(newNumberPos) && validVertical(newLetterPos)){
                validTarget = true;
            }

            // if you are horisontal not vertical
            if (validHorisontal(newNumberPos) && !validVertical(newLetterPos)){
                validTarget = true;
            }

            // if both direktions are true the target is the curent position
            if (validHorisontal(newNumberPos) && validVertical(newLetterPos)){
                validTarget = false;
            }
            
            
        return validTarget;
    }
    
    // recives a target and a list of places to chek 
    protected boolean validList(int verticalTarget, int horisontalTarget, int[] verticalAdjustment, int[] horisontalAdjustment){
        for (int i = 0; i < verticalAdjustment.length-1; i++){
            if (validRelativeToSelf(verticalTarget, horisontalTarget, verticalAdjustment[i], horisontalAdjustment[i])){
                return true;
            }
            System.out.println(" curent target: " + LETTERS[verticalTarget] + " " + NUMBERS[horisontalTarget] + " seartching at: " + verticalAdjustment[i] + " " + horisontalAdjustment[i]);
        }
        return false;
    }
            
    //for (int x=0; x<5; x++)
    
    
    protected boolean validVertical(int newLetterPos){
        return newLetterPos == letterPos;
    }
    
    public abstract void move(int newLetterPos, int newNumberPos) throws IllegalMoveException;
    
    @Override
    public String toString(){
        String out = getCollor() + " " + myType + " " + LETTERS[letterPos] + NUMBERS[numberPos];
        return out;
    }
}