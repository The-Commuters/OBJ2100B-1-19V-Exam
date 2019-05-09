/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import Pieces.Move;
import Pieces.Parser;
import gui.chess.pieces.Piece;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author DavidNaist
 */
public class ChessSimulator extends HBox {
    
    // Methods
    Point[] oldPos = {new Point(0, 1), new Point(0, 6), new Point(0, 2), new Point(0, 5), new Point(0, 3)}; 
    Point[] newPos = {new Point(0, 2), new Point(0, 5), new Point(0, 3), new Point(0, 4), new Point(0, 4)};
    
    // Constructor
    public ChessSimulator(int size, ArrayList<String> lan) {
        String[] lanArray = new String[lan.size()];
        lanArray = lan.toArray(lanArray);
        // Timeline timeline = makeTimeline(Parser.parseSANArray(sanArray));
        
        
        
        Field field = new Field(size);
        SequentialTransition chessSim = makeSequentialTransition(field, oldPos, newPos);
        // chessSim.setCycleCount(Timeline.INDEFINITE);
        // chessSim.setAutoReverse(true);     
        
        Chessboard chessboard = new Chessboard(size, field);
        ChessFeed chessFeed = new ChessFeed(size, lanArray, chessSim);
        
        
        getChildren().addAll(chessboard, chessFeed);
    }
    
    // Methods
    private SequentialTransition makeSequentialTransition(Field field, Point[] oldPos, Point[] newPos) {
        
        ArrayList<Timeline> timelines = new ArrayList<>();
        
        for (int i = 0; i < oldPos.length; i++) {
            
            ArrayList<KeyFrame> keyFrames = new ArrayList<>();
            
            ArrayList<Piece> pieceArray = field.getPieceArray();
            
            Point[][] positionGridCopy = field.getPositionGrid();
            
            int oldPositionX = (int)positionGridCopy[(int)oldPos[i].getX()][(int)oldPos[i].getY()].getX();
            int oldPositionY = (int)positionGridCopy[(int)oldPos[i].getX()][(int)oldPos[i].getY()].getY();
            
            int newPositionX = (int)positionGridCopy[(int)newPos[i].getX()][(int)newPos[i].getY()].getX();
            int newPositionY = (int)positionGridCopy[(int)newPos[i].getX()][(int)newPos[i].getY()].getY();
            
            // Find tracker
            Piece piece = new Piece();
            for (Piece p: pieceArray) {
                boolean hasSameX = p.getTracker().getX() == oldPositionX;
                boolean hasSameY = p.getTracker().getY() == oldPositionY;
                
                if (hasSameX && hasSameY) {
                    piece = p;
                }
            }
            
            // Set Keyvalues
            KeyValue moveX = new KeyValue(piece.xProperty(), newPositionX);
            KeyValue moveY = new KeyValue(piece.yProperty(), newPositionY);
 
            // Add Movement to timelines
            keyFrames.add(new KeyFrame(Duration.millis(500), moveX, moveY));
            
            
            // Check for Collision
            
            for (Piece captive: pieceArray) {
                boolean hasSameX = captive.getTracker().getX() == newPositionX;
                boolean hasSameY = captive.getTracker().getY() == newPositionY;
                
                if (hasSameX && hasSameY) {
                    // Add Capture to timelines
                    KeyValue capture = new KeyValue(captive.opacityProperty(), 0);
                    keyFrames.add(new KeyFrame(Duration.millis(500), capture));
                }
            }
            
            // Update Tracker
            piece.setTracker(new Point(newPositionX, newPositionY));
            
            // Add move
            KeyFrame[] keyFrameArray = new KeyFrame[keyFrames.size()];
            keyFrameArray = keyFrames.toArray(keyFrameArray);
            timelines.add(new Timeline(keyFrameArray));
 
        }
         
        Timeline[] timelinesArray = new Timeline[timelines.size()];
        timelinesArray = timelines.toArray(timelinesArray);
        SequentialTransition chessSim = new SequentialTransition(timelinesArray);
        
        return chessSim;
    }
    
    
    private Timeline makeTimeline(Move[] score) {
        
        
        
        return new Timeline();
    }
}
