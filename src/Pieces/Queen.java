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
public class Queen extends Piece {

    public Queen(boolean white, int letterPos, int numberPos) {
        super(white, letterPos, numberPos);
        super.myType = "Queen";
    }

    @Override
    public void move(int newLetterPos, int newNumberPos) throws IllegalMoveException {
       
        // is the target outside bounds?
        if (validLetter(newLetterPos) && validNumber(newNumberPos)){

            if (super.validTilted(newLetterPos, newNumberPos) || super.validLine(newLetterPos, newNumberPos)){
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
