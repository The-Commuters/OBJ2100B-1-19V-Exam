/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.chess;

import Pieces.Move;
import Pieces.Parser;
import java.awt.Point;
import java.util.ArrayList;
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
    Point[] oldPos = {new Point(0, 1), new Point(1, 6)}; 
    Point[] newPos = {new Point(0, 2), new Point(1, 5)};
    
    // Constructor
    public ChessSimulator(int size, ArrayList<String> san) {
        String[] sanArray = new String[san.size()];
        sanArray = san.toArray(sanArray);
        // Timeline timeline = makeTimeline(Parser.parseSANArray(sanArray));
        
        
        
        Field field = new Field(size);
        SequentialTransition chessSim = makeSequentialTransition(field, oldPos, newPos);
        chessSim.setCycleCount(Timeline.INDEFINITE);
        chessSim.setAutoReverse(true);
        chessSim.play();
        
        
        Chessboard chessboard = new Chessboard(size, field);
        ChessFeed chessFeed = new ChessFeed(size, sanArray, field);
        
        
        getChildren().addAll(chessboard, chessFeed);
    }
    
    // Methods
    private SequentialTransition makeSequentialTransition(Field field, Point[] oldPos, Point[] newPos) {
        
        ArrayList<Timeline> timelines = new ArrayList<>();
        
        for (int i = 0; i < oldPos.length; i++) {
            
            
            // WORKS
            ImageView[][] pieceGridCopy = field.getPieceGrid();
            
            Point[][] positionGridCopy = field.getPositionGrid();
            
            int oldPositionX = (int)positionGridCopy[(int)oldPos[i].getX()][(int)oldPos[i].getY()].getX();
            int oldPositionY = (int)positionGridCopy[(int)oldPos[i].getX()][(int)oldPos[i].getY()].getY();
            
            int newPositionX = (int)positionGridCopy[(int)newPos[i].getX()][(int)newPos[i].getY()].getX();
            int newPositionY = (int)positionGridCopy[(int)newPos[i].getX()][(int)newPos[i].getY()].getY();
            
            // ImageView piece = pieceGridCopy[(int)oldPos[i].getY()][(int)oldPos[i].getX()];
            ImageView piece = new ImageView();
            
            
            KeyValue moveX = new KeyValue(piece.xProperty(), newPositionX);
            KeyValue moveY = new KeyValue(piece.yProperty(), newPositionY);
            
            KeyFrame keyFrame  = new KeyFrame(Duration.millis(500), moveX, moveY);
            
            Timeline timeline = new Timeline(keyFrame);
            
            // timeline.setCycleCount(Timeline.INDEFINITE); 
            // timeline.setAutoReverse(true); 
            // timeline.setRate(1);
            
            timelines.add(timeline);
            // WORKS
            
            // ??
            // field.setPieceGrid(pieceGridCopy);
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
