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
public class Rook extends Piece {
    
    public Rook(boolean white, int letterPos, int numberPos) {
        super(white, letterPos, numberPos);
        super.myType = "Rook";
    }
    
    public void move(int newLetterPos, int newNumberPos) throws IllegalMoveException{
        boolean onBoard = false, validTarget = false;
        
        // heare comes a series of ifs, they cud all be in one if-statement but are split mostly for redabilety
        // is the target outside bounds?
        if (validLetter(newLetterPos) && validNumber(newNumberPos)){
            onBoard = true;
        }
        //System.out.println("piece on board? " + onBoard);
        
        // neither being true is achounted for buy false being the default state
        // if you are vertical not horisontal
        if (!super.validHorisontal(newNumberPos) && super.validVertical(newLetterPos)){
            validTarget = true;
        }
        //System.out.println("vertical? " + validTarget);
        
        // if you are horisontal not vertical
        if (super.validHorisontal(newNumberPos) && !super.validVertical(newLetterPos)){
            validTarget = true;
        }
        //System.out.println("horisontal? " + validTarget);
        
        // if both direktions are true the target is the curent position
        if (super.validHorisontal(newNumberPos) && super.validVertical(newLetterPos)){
            validTarget = false;
        }
        
        //System.out.println("on the same dam spot? " + validTarget);
        
        if (validTarget && onBoard){
            // replace with watever method call to send to .txt log
            System.out.println(super.toString() + " to " + super.LETTERS[newLetterPos] + super.NUMBERS[newNumberPos]);

            // insert method-call for removing piece curently on target cordinates if its not handled on ChessBoard
            super.letterPos = newLetterPos;
            super.numberPos = newNumberPos;
        }
        else{
            throw new IllegalMoveException("Ellegal move at " + super.toString());
        }
        
        
        
    }
    
}