/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import Pieces.*;
import progeksamen.IllegalMoveException;

/**
 *
 * @author Mads Hagen
 */
public class ChessBoardModel {
    Piece[][] board = new Piece[8][8]; // x/letter then y/number
    Rook tool = new Rook(true, 0, 0); // exists to acsess the Piece classes

    public ChessBoardModel() {
        reset();
        System.out.println("New Board Made");
    }
    
    // resets the board
    public void reset(){
        //rooks
        board[0][0] = new Rook(false, 0, 0); // a8 tower black
        board[7][0] = new Rook(false, 7, 0); // h8 tower black
        board[7][7] = new Rook(true, 7, 7); // a8 tower white
        board[0][7] = new Rook(true, 0, 7); // h8 tower white
        
        // Bishops
        board[2][7] = new Bishop(true, 2, 7); // c1 Bishop white
        board[5][7] = new Bishop(true, 5, 7); // f1 Bishop white
        board[2][0] = new Bishop(false, 2, 0); // c8 Bishop black
        board[5][0] = new Bishop(false, 5, 0); // f8 Bishop black
        
        // Knights
        board[1][7] = new Knight(true, 1, 7); // b1 Knight white
        board[6][7] = new Knight(true, 6, 7); // g1 Knight white
        board[1][0] = new Knight(false, 1, 0); // b8 Knight black
        board[6][0] = new Knight(false, 6, 0); // g8 Knight black
        
        // Queens
        board[4][7] = new Queen(true, 4, 7); // e1 Queen white
        board[4][0] = new Queen(false, 4, 0); // e8 Queen black
        
        // Kings
        board[3][7] = new King(true, 3, 7); // d1 King white
        board[3][0] = new King(false, 3, 0); // d8 King black
        
        // Pawns
        for (int i=0; i<tool.LETTERS.length; i++){
            board[i][6] = new King(true, i, 6); // Pawns white
        }
        for (int i=0; i<tool.LETTERS.length; i++){
            board[i][6] = new King(false, i, 1); // Pawns black
        }
        
    }
    
    // move that takes inn cordinates
    public void move(int pieceVertical, int pieceHorisontal, int targetVertical, int targetHorisontal){
        try{
            board[pieceVertical][pieceHorisontal].move(targetVertical, targetHorisontal);
            board[targetVertical][targetHorisontal] = board[pieceVertical][pieceHorisontal];
            board[pieceVertical][pieceHorisontal] = null;
        }
        catch(IllegalMoveException ex1){
            System.out.println(ex1.getMessage());
        }
    }
    
    // move that takes in charakters and letters
    public void move(char pieceLetter, int pieceNumber, char targetLetter, int targetNumber){
        move(letterToCordinate(pieceLetter), numberToCordinate(pieceNumber), letterToCordinate(targetLetter), numberToCordinate(targetNumber));
    }
    
    private int letterToCordinate(char letter){
        int cordinate = (int)letter;
        System.out.println(cordinate);
        
        if (cordinate > 0 || cordinate < tool.LETTERS.length-1){
            return -1;
        }
        
        // a is represented buy the number 97
        return (cordinate - 97);
    }
    
    private int numberToCordinate(int number){
        
        System.out.println(number);
        
        if (number > 0 || number < tool.LETTERS.length-1){
            return -1;
        }
        int cordinate = (number - tool.LETTERS.length) * -1;
        // a is represented buy the number 97
        return cordinate;
    }
    
}