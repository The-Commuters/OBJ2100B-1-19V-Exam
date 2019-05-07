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
public class Pawn extends Piece {
    private boolean firstmove;
        
        
    public Pawn(boolean white, int letterPos, int numberPos) {
        super(white, letterPos, numberPos);
        super.myType = "Pawn";
        firstmove = true;
        
    }
    
    @Override
    public void move(int newLetterPos, int newNumberPos) throws IllegalMoveException {
       
        // is the target outside bounds?
        if (validLetter(newLetterPos) && validNumber(newNumberPos)){
            
            //chose 5 difrent tests instead of validList() for the pawns unike 2-stepp at its first move and difrent "facings"
            if  ((super.validRelativeToSelf(newLetterPos, newNumberPos, -1, -1) && super.white)  ||             // the white cheks
                (super.validRelativeToSelf(newLetterPos, newNumberPos, -1, 0) && super.white) ||
                (super.validRelativeToSelf(newLetterPos, newNumberPos, -1, 1) && super.white) ||
                (super.validRelativeToSelf(newLetterPos, newNumberPos, 0, -2) && firstmove && super.white) ||
                (super.validRelativeToSelf(newLetterPos, newNumberPos, 0, 2) && firstmove && !super.white) ||   // the black cheks
                (super.validRelativeToSelf(newLetterPos, newNumberPos, 1, -1) && !super.white) ||
                (super.validRelativeToSelf(newLetterPos, newNumberPos, 1, 0) && !super.white) ||
                (super.validRelativeToSelf(newLetterPos, newNumberPos, 1, 1) && !super.white)
                    ){
                // replace with watever method call to send to .txt log
                System.out.println(super.toString() + " to " + super.LETTERS[newLetterPos] + super.NUMBERS[newNumberPos]);

                // insert method-call for removing piece curently on target cordinates if its not handled on ChessBoard
                super.letterPos = newLetterPos;
                super.numberPos = newNumberPos;
                firstmove = false;
            }
        }
        else{
            throw new IllegalMoveException("Ellegal move at " + super.toString());
        }
    }
    
}
