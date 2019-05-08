/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import Pieces.Move.CastleType;
import Pieces.Move.ChessPiece;
import Pieces.Move.Result;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mads Hagen & David Øvernes
 */
public class Parser {
    
    
    
    
    // move  = startx, starty, målx, måly, booleanChek, booleanChekMate, booleanCapture, booleanDraw?, enumPromotion, enumCastle, enumresult
    private static Move parseSAN(String oneMove){
        
        //code here
        
        
        
        //FIKTIVE DATA
        Map position = new HashMap();
        position.put(false, ChessPiece.PAWN);
        
        Map castle = new HashMap();
        castle.put(false, CastleType.NO_CASTLE);
        
        Map result = new HashMap();
        result.put(false, Result.NO_RESULT);
        
        return new Move(new Point(1, 0), new Point(2, 3), false, false, true, true, position, castle, result); 
    }
    

    // Nf3 Nc6
    public static Move[] parseSANTurn(String move){
        ArrayList<Move> parsedMoves = new ArrayList<>();
        
        for (String oneMove: move.split(" ")){
            parsedMoves.add(parseSAN(oneMove));
        }
        
        Move[] moves = new Move[parsedMoves.size()];
        moves = parsedMoves.toArray(moves);
        return moves; // move  = 
    }
    
    
    /*
         e4 e5
         Nf3 Nc6
         Bb5 a6
    */
    public static Move[] parseSANArray(String[] score){
         ArrayList<Move> parsedMoves = new ArrayList<>();
        
        for (String move: score){
            parsedMoves.addAll(new ArrayList<>(Arrays.asList(parseSANTurn(move))));
        }
        
        Move[] moves = new Move[parsedMoves.size()];
        moves = parsedMoves.toArray(moves);
        return moves;
    }
    
}
