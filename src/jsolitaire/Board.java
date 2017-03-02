/*
 * License
 */
package jsolitaire;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JComponent;

public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient JComponent temp; //leaving this here just for the "transient"

    private Stack<Move> history;
    private Card waste;
    private Queue<Card> stock;
    private Stack<Card>[] foundations; //an array or four separate stacks??
    private Stack<Card>[] tableaus;    //an array or eight separate stacks??

    public boolean tryToMove(Move move) {
        return false;
    }

    public Optional<Move> goBack() {
        return Optional.empty();
    }

    public List<Move> getNextMove() {
        return Collections.emptyList();
    }

    public JComponent getRendered() {
        return null;
    }
    
    private boolean isValidMove(Move move) {
        return false;
    }
    
    private boolean isGameEnd() {
        return false;
    }
    
    private List<Move> rankMoves(List<Move> moves) { //should we implement the entire "AI"?
        return moves;
    }

    public enum Deck {
        STOCK,
        WASTE,
        FOUNDATION_H,
        FOUNDATION_D,
        FOUNDATION_C,
        FOUNDATION_S,
        TABLEAU_1,
        TABLEAU_2,
        TABLEAU_3,
        TABLEAU_4,
        TABLEAU_5,
        TABLEAU_6,
        TABLEAU_7,
        TABLEAU_8
    }
}
