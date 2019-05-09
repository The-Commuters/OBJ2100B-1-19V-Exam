/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import gui.chess.pieces.Bishop;
import gui.chess.pieces.King;
import gui.chess.pieces.Knight;
import gui.chess.pieces.Pawn;
import gui.chess.pieces.Piece;
import gui.chess.pieces.Queen;
import gui.chess.pieces.Rook;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author DavidNaist
 */
final class Field extends Pane {
    
    // Fields
    private Point[][] positionGrid;
    private ArrayList<Piece> pieceArray;
            
    // Constants
    private final int GRID_SIZE = 8;
    private final int PIECE_SIZE = 40;
    
    // Constructors
    public Field(int size) {
        super();
        
        this.positionGrid = newPointGrid(GRID_SIZE, size, size);
        this.pieceArray = new ArrayList<>();
        initGrids(positionGrid, pieceArray);
        initTrackers(pieceArray);
        

    }

    // Getters
    public Point[][] getPositionGrid() {
        return positionGrid;
    }
    
    public ArrayList<Piece> getPieceArray() {
        return pieceArray;
    }
    
    // Methods
    private void initGrids(Point[][] positionGrid, ArrayList<Piece> pieceArray) {
        Piece piece;
        
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0: piece = new Rook(PIECE_SIZE, i == 0); break;
                        case 1: piece = new Knight(PIECE_SIZE, i == 0); break;
                        case 2: piece = new Bishop(PIECE_SIZE, i == 0); break;
                        case 3: piece = new Queen(PIECE_SIZE, i == 0); break;
                        case 4: piece = new King(PIECE_SIZE, i == 0); break;
                        case 5: piece = new Bishop(PIECE_SIZE, i == 0); break;
                        case 6: piece = new Knight(PIECE_SIZE, i == 0); break;
                        case 7: piece = new Rook(PIECE_SIZE, i == 0); break;
                        default: piece = new Piece();
                    }
                    
                    // Add to position
                    piece.setX(positionGrid[i][j].getY());
                    piece.setY(positionGrid[i][j].getX());
                    getChildren().add(piece);
                    
                    pieceArray.add(piece);
                }
                
                if (i == 1 || i == 6) {
                    piece = new Pawn(PIECE_SIZE, i == 1);
                    piece.setX(positionGrid[i][j].getY());
                    piece.setY(positionGrid[i][j].getX());
                    getChildren().add(piece);
                    
                    pieceArray.add(piece);
                }
                
                if (i == 7) {
                    switch (j) {
                        case 0: piece = new Rook(PIECE_SIZE, i == 0); break;
                        case 1: piece = new Knight(PIECE_SIZE, i == 0); break;
                        case 2: piece = new Bishop(PIECE_SIZE, i == 0); break;
                        case 3: piece = new King(PIECE_SIZE, i == 0); break;
                        case 4: piece = new Queen(PIECE_SIZE, i == 0); break;
                        case 5: piece = new Bishop(PIECE_SIZE, i == 0); break;
                        case 6: piece = new Knight(PIECE_SIZE, i == 0); break;
                        case 7: piece = new Rook(PIECE_SIZE, i == 0); break;
                        default: piece = new Piece();
                    }
                    
                    piece.setX(positionGrid[i][j].getY());
                    piece.setY(positionGrid[i][j].getX());
                    getChildren().add(piece);
                    
                    pieceArray.add(piece);
                }
            }
        }
    }
    
    private Point[][] newPointGrid(int size, int parentWidth, int parentHeight) {
        int[][] row = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                row[i][j] = ((parentWidth / size) * (i+1)) - ((parentWidth / size));
            }
        }
        
        int[][] column = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                column[i][j] = ((parentHeight / size) * (j+1)) - ((parentHeight / size));
            }
        }
        
        Point[][] grid = new Point[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Point(row[i][j], column[i][j]);
            }
        }
        
        return grid;
    }
    
    public void initTrackers(ArrayList<Piece> pieceArray) {
        for (Piece piece: pieceArray) {
            piece.setTracker(new Point((int)piece.getX(), (int)piece.getY()));
            
        }
    }
    
}
