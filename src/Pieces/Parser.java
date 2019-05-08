/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import Pieces.Move.CastleType;
import Pieces.Move.ChessPiece;
import Pieces.Move.Result;
import static Pieces.Piece.NUMBERS;
import static Pieces.Piece.LETTERS;
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
    
    //Rook tool = new Rook(true, 0, 0); // exists to acsess the Piece classes
    //public static final char[] LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    //public static final int[] NUMBERS = {8, 7, 6, 5, 4, 3, 2, 1};
    
    
    //Nf3
    private static Move parseSAN(String oneMove){
        
        //code here
        int piece = determineType(oneMove.charAt(0));
        Map position = determinePromotion(oneMove);
        boolean drawIsOfferd = determineDrawOffer(oneMove);
        boolean isCaptureing = oneMove.contains("x");
        boolean isThreateningChekMate = oneMove.contains("#");
        boolean isThreateningChek = oneMove.contains("+");
        
        
        //FIKTIVE DATA
        Map castle = new HashMap();
        castle.put(false, CastleType.NO_CASTLE);
        
        Map result = new HashMap();
        result.put(false, Result.NO_RESULT);
        
        return new Move(new Point(1, 0), new Point(2, 3), isThreateningChek, isThreateningChekMate, isCaptureing, drawIsOfferd, position, castle, result); 
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
    
    private static Map determinePromotion(String oneMove){
        
        // fin ut om der er en promotion
        char equalsFinder = oneMove.charAt(oneMove.length()-2);
        char potentialForm = 'P';
        boolean promotion = ((int)equalsFinder == '=');
        
        Map position = new HashMap();
        
        // hvis der er en forfremmelse, finn ut hvilken og lagre den som en String
        if (promotion){
            potentialForm = oneMove.charAt(oneMove.length()-1); // gets the last charakter in the string
            }
        
            switch(potentialForm) {
                case 'K': position.put(promotion, ChessPiece.KING); break;
                case 'Q': position.put(promotion, ChessPiece.QUEEN); break;
                case 'B': position.put(promotion, ChessPiece.BISHOP); break;
                case 'N': position.put(promotion, ChessPiece.KNIGHT); break;
                case 'R': position.put(promotion, ChessPiece.ROOK); break;
                default: position.put(promotion, ChessPiece.PAWN);
            }
        return position;
    }
    
    private static boolean determineDrawOffer(String oneMove){
        boolean draw = false; // you dotn normaly offer a draw
        
        // is this a draw offer? it cud be a pawn promotion
        char equalsFinder = oneMove.charAt(oneMove.length()-2);
        boolean potentialDraw = ((int)equalsFinder == '=');
        
        // okey so it has potential, lets make dam shure its ( and ) ont eh sides of =
        if (potentialDraw){
            boolean left = ((oneMove.charAt(oneMove.length()-3)) == '(');
            boolean right = ((oneMove.charAt(oneMove.length()-3)) == ')');
            draw = (left && right); // if both are preset, then yea, its a draw  offer
        }
        return draw;
    }
    
    private static int determineType(char firstLetter){
        int type = -1; // -1 being the error signal
        
        // ser etter edle brikker
        switch(firstLetter) {
            case 'K': type = 1; break;
            case 'Q': type = 2; break;
            case 'B': type = 3; break;
            case 'N': type = 4; break;
            case 'R': type = 5; break;
            default: type = -1;
        }
        if (type != -1){
            return type;
        }
        
        // < mindre en, > støre en
        
       
        type = letterToCordinate(firstLetter);
        if (validNumber(type)){
            return 6; // bonne
        }
        
        return -1;
    }
    
    private static int letterToCordinate(char letter){
        int cordinate = (int)letter;
        System.out.println(cordinate);
        
        if (cordinate > 0 || cordinate < LETTERS.length-1){
            return -1;
        }
        
        // a is represented buy the number 97
        return (cordinate - 97);
    }
    
    private static int numberToCordinate(int number){
        
        System.out.println(number);
        
        if (number > 0 || number < LETTERS.length-1){
            return -1;
        }
        int cordinate = (number - LETTERS.length) * -1;
        // a is represented buy the number 97
        return cordinate;
    }
    
    private static boolean validNumber(int cordinate){
        boolean valid = true;
        if (cordinate < 0 || cordinate > NUMBERS.length-1){
            valid = false;
        }
        return valid;
    }
}
