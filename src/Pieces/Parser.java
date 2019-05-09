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
    private static Move parseLAN(String oneMove){
        
        //code here
        
        Map position = determinePromotion(oneMove);
        boolean drawIsOfferd = determineDrawOffer(oneMove);
        boolean isCaptureing = oneMove.contains("x");
        boolean isThreateningChekMate = oneMove.contains("#");
        boolean isThreateningChek = oneMove.contains("+");
        Map castle = determineCastle(oneMove);
        Map result = determineResult(oneMove);
        
        Point start = determineStartLAN(oneMove);
        Point stop = determineStopLAN(oneMove);
        
        //System.out.println("parseLAN is being called!");
        
        return new Move(start, stop, isThreateningChek, isThreateningChekMate, isCaptureing, drawIsOfferd, position, castle, result); 
    }
    

    // Nf3 Nc6
    public static Move[] parseLANTurn(String move){
        ArrayList<Move> parsedMoves = new ArrayList<>();
        
        //System.out.println("parseLANTurn is being called!");
        
        for (String oneMove: move.split(" ")){
            parsedMoves.add(parseLAN(oneMove));
        }
        
        Move[] moves = new Move[parsedMoves.size()];
        moves = parsedMoves.toArray(moves);
        return moves; // move  = 
    }
    
     /*    e4 e5
         Nf3 Nc6
         Bb5 a6*/    
    public static Move[] parseLANArray(String[] score){
         ArrayList<Move> parsedMoves = new ArrayList<>();
         
        //System.out.println("parseLANArray is being called!");
        
        for (String move: score){
            parsedMoves.addAll(new ArrayList<>(Arrays.asList(parseLANTurn(move))));
        }
        
        Move[] moves = new Move[parsedMoves.size()];
        moves = parsedMoves.toArray(moves);
        return moves;
    }
    
    private static Point determineStartLAN(String oneMove){
        int x = -1, y = -1;
        int piece = determineType(oneMove.charAt(0));
        
        if (piece == 6){
            x = charakterToCordinate(oneMove.charAt(0));
            y = charakterToCordinate(oneMove.charAt(1));
        }// < mindre en, > støre en
        else if (piece >= 1 && piece <= 5){
            x = charakterToCordinate(oneMove.charAt(1));
            y = charakterToCordinate(oneMove.charAt(2));
        }
                
        return new Point(x, y);
    }
    
    private static Point determineStopLAN(String oneMove){
        int x = -1, y = -1;
        
        //adjust for the potential lenght of the previus message
        int adjustingPiece = determineType(oneMove.charAt(0));
        int stringLenghtModifier = 3; // 2 is the with of a pawns command and 1 more for the -
        if (adjustingPiece >= 1 && adjustingPiece <= 5){
            stringLenghtModifier++; // all other peieces are 3 wide
        }
        
        
        int piece = determineType(oneMove.charAt(0+stringLenghtModifier));
        if (piece == 6){
            x = charakterToCordinate(oneMove.charAt(0+stringLenghtModifier));
            y = charakterToCordinate(oneMove.charAt(1+stringLenghtModifier));
        }// < mindre en, > støre en
        else if (piece >= 1 && piece <= 5){
            x = charakterToCordinate(oneMove.charAt(1+stringLenghtModifier));
            y = charakterToCordinate(oneMove.charAt(2+stringLenghtModifier));
        }
        
        
        return new Point(x, y);
    }
    
    private static Point determineStopSAN(String oneMove){
        int x = -1, y = -1;
        int piece = determineType(oneMove.charAt(0));
        if (piece == 6){
            x = letterToCordinate(oneMove.charAt(0));
            y = numberToCordinate(oneMove.charAt(1));
        }// < mindre en, > støre en
        else if (piece >= 1 && piece <= 5){
            x = letterToCordinate(oneMove.charAt(1));
            y = numberToCordinate(oneMove.charAt(2));
        }
        
        
        return new Point(x, y);
    }
    
    private static Map determineResult(String oneMove){
        Map result = new HashMap();
        if (oneMove.contains("1–0")){
            result.put(true, Result.WHITE_VICTORY);
        }
        else if(oneMove.contains("0–1")){
            result.put(true, Result.BLACK_VICTORY);
        }
        else if(oneMove.contains("½–½")){
            result.put(true, Result.DRAW);
        }
        else if(oneMove.contains("0.5–0.5")){
            result.put(true, Result.DRAW);
        }
        else{
            result.put(false, Result.NO_RESULT);
        }
        
        
        return result;
    }
    // KING_SIDE_CASTLE
    private static Map determineCastle(String oneMove){
        Map castle = new HashMap();
        if (oneMove.contains("0-0")){
            castle.put(true, CastleType.QUEEN_SIDE_CASTLE);
        }
        else if (oneMove.contains("0-0-0")){
            castle.put(true, CastleType.KING_SIDE_CASTLE);
        }
        else{
            castle.put(false, CastleType.NO_CASTLE);
        }
        return castle;
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
        
        //String testData = "firstLetter: " + firstLetter;
        
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
        // turn the letter into an acsepted cordinate
        type = letterToCordinate(firstLetter);
        
        //testData += " in-bokstav som kommer ut av letterToCordinate() " + type;
        //System.out.println(testData);
        if (validNumber(type)){
            return 6; // bonne
        }
        
        return -1;
    }
    
    private static int letterToCordinate(char letter){
        int cordinate = (int)letter; // turn teh char into an int, a is 97
        
        // String testData = "letterToCordinate turns " + letter + " into " + cordinate;
        cordinate = cordinate - 97; // not a, the lowest number shud be 0 and h the highest number 7
        
        // < mindre en, > støre en
        //if the letter 
        if (cordinate < 0 || cordinate > LETTERS.length-1){ // make shure they are within those boundries
            // testData += " subtrakt 97 to get " + cordinate + " it does not get acsepted as from 0 to 7";
            // System.out.println(testData);
            return -1;
        }
        // testData += " subtrakt 97 to get " + cordinate + " it gets acsepted";
        // System.out.println(testData);
        // a is represented buy the number 97
        return cordinate;
    }
    
    private static int charakterToCordinate(char charakter){
        int cordinate = -1;
        switch (charakter){
            case 'A': cordinate = 0; break;
            case 'B': cordinate = 1; break;
            case 'C': cordinate = 2; break;
            case 'D': cordinate = 3; break;
            case 'E': cordinate = 4; break;
            case 'F': cordinate = 5; break;
            case 'G': cordinate = 6; break;
            case 'H': cordinate = 7; break;
            case 'a': cordinate = 0; break;
            case 'b': cordinate = 1; break;
            case 'c': cordinate = 2; break;
            case 'd': cordinate = 3; break;
            case 'e': cordinate = 4; break;
            case 'f': cordinate = 5; break;
            case 'g': cordinate = 6; break;
            case 'h': cordinate = 7; break;
            case '1': cordinate = 0; break;
            case '2': cordinate = 1; break;
            case '3': cordinate = 2; break;
            case '4': cordinate = 3; break;
            case '5': cordinate = 4; break;
            case '6': cordinate = 5; break;
            case '7': cordinate = 6; break;
            case '8': cordinate = 7; break;
            default: cordinate = -1;
        }
        return cordinate;
    }
    
    
    private static int numberToCordinate(int number){
        
        
        String testData = "numberToCordinate kordinat: " + number;

        // < mindre en, > støre en
        
        if (number < 0 || number > LETTERS.length-1){
            return -1;
        }
        
        int cordinate = (number - LETTERS.length) * -1;
        testData += " blir til kordinaten: " + cordinate;
        System.out.println(testData);
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
