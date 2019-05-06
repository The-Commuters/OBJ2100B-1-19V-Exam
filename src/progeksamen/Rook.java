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
    
    // untested and not done
    public void move(int newLetterPos, int newNumberPos){
        
        
        System.out.println(super.toString() + " " + super.LETTERS[super.letterPos] + super.NUMBERS[super.numberPos]);
    }
    
}
