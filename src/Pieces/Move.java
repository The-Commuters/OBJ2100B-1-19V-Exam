/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import java.awt.Point;
import java.util.Map;

/**
 *
 * @author Mads Hagen & David Øvernes
 */
public class Move {
    enum ChessPiece {KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN};
    enum CastleType {KING_SIDE_CASTLE, QUEEN_SIDE_CASTLE, NO_CASTLE};
    enum Result {WHITE_VICTORY, DRAW, BLACK_VICTORY, NO_RESULT};
    
    
    Point startPoint; // int startX, int startY 
    Point endPoint; // int endX, int endY
    boolean isThreateningChek;
    boolean isThreateningChekMate;
    boolean isCapturing;
    boolean drawIsOfferd;
    Map promotion; // boolean isPromoted, ChessPiece chessPiece
    Map castle; // boolean isCasteling, CastleType castleType
    Map result; // boolean gameOver, result result

    public Move(Point x, Point y, boolean isThreateningChek, boolean isThreateningChekMate, boolean isCapturing, boolean drawIsOfferd, Map promotion, Map castle, Map result) {
        this.startPoint = x;
        this.endPoint = y;
        this.isThreateningChek = isThreateningChek;
        this.isThreateningChekMate = isThreateningChekMate;
        this.isCapturing = isCapturing;
        this.drawIsOfferd = drawIsOfferd;
        this.promotion = promotion;
        this.castle = castle;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Move{" + "startPoint=" + startPoint + ", endPoint=" + endPoint + ", isThreateningChek=" + isThreateningChek + ", isThreateningChekMate=" + isThreateningChekMate + ", isCapturing=" + isCapturing + ", drawIsOfferd=" + drawIsOfferd + ", promotion=" + promotion + ", castle=" + castle + ", result=" + result + '}';
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public boolean isIsThreateningChek() {
        return isThreateningChek;
    }

    public boolean isIsThreateningChekMate() {
        return isThreateningChekMate;
    }

    public boolean isIsCapturing() {
        return isCapturing;
    }

    public boolean isDrawIsOfferd() {
        return drawIsOfferd;
    }

    public Map getPromotion() {
        return promotion;
    }

    public Map getCastle() {
        return castle;
    }

    public Map getResult() {
        return result;
    }
    
    
    
            
}
