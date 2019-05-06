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
public class ChessBoard {
    Piece[][] board = new Piece[8][8]; // x/letter then y/number

    public ChessBoard() {
        reset();
    }
    
    
    public void reset(){
        //long ass list of cunstruction veare ya add eatch peice
        board[0][0] = new Rook(false, 0, 0); // a8 tower black
        board[7][0] = new Rook(false, 7, 0); // h8 tower black
        board[7][7] = new Rook(true, 7, 7); // a8 tower white
        board[0][7] = new Rook(true, 0, 7); // h8 tower white
    }
    
}
