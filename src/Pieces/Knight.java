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
public class Knight extends Piece {
    private static final int[] VERTICAL = {-1, 1, 2, 1, 1, -1, -2, -2};
    private static final int[] HORISONTAL = {-2, -2, -1, 1, 2, 2, 1, -1};
    
    //private static final int[] VERTICAL = {-1, 0, 1, 1, 1, 0, -1, -1};
    //private static final int[] HORISONTAL = {-1, -1, -1, 0, 1, 1, 1, 0};
        
        
    public Knight(boolean white, int letterPos, int numberPos) {
        super(white, letterPos, numberPos);
        super.myType = "Knight";
        
    }
    
    @Override
    public void move(int newLetterPos, int newNumberPos) throws IllegalMoveException {
       
        // is the target outside bounds?
        if (validLetter(newLetterPos) && validNumber(newNumberPos)){

            if (super.validList(newLetterPos, newNumberPos, VERTICAL, HORISONTAL)){
                // replace with watever method call to send to .txt log
                System.out.println(super.toString() + " to " + super.LETTERS[newLetterPos] + super.NUMBERS[newNumberPos]);

                // insert method-call for removing piece curently on target cordinates if its not handled on ChessBoard
                super.letterPos = newLetterPos;
                super.numberPos = newNumberPos;
            }
        }
        else{
            throw new IllegalMoveException("Ellegal move at " + super.toString());
        }
    }
    
}