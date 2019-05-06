/*
 * 
 */
package progeksamen;

/**
 *
 * @author voje
 */
public class Move {
    
    enum Comment { 
        VERY_GOOD, GOOD, INTERESTING, 
        DOUBTFUL, BAD, VERY_BAD; 
    } 

    String move;
    Comment comment;
    
    public Move(String move) {
        this.move = move;
    }
    
    public Move(String move, Comment comment) {
        this.move = move;
        this.comment = comment;
    }
    

}
