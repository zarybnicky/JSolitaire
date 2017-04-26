/*
 * @authors: Jakub Zarybnický (xzaryb00)
 *           Jiří Záleský (xzales12)
 * VUTBR BIT 2, 2016/17
 *
 * Description: Class represents move between decks.
 */

package jsolitaire;

import java.io.Serializable;
import java.util.Observable;
import jsolitaire.Board.Deck;

/**
 * Represents a move of a stack of cards between two decks.
 * 
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class Move extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Pair<Deck, Integer> from;
    private final Pair<Deck, Integer> to;
    private final int fromIndex;
    private boolean turnedCard = false;

    /**
     * Constructs a move moving <code>fromIndex</code> cards from <code>from</code> to <code>to</code>
     * 
     * @param from 'From' deck identifier
     * @param to 'To' deck identifier
     * @param fromIndex Number of cards to move
     */
    public Move(Pair<Deck, Integer> from, Pair<Deck, Integer> to, int fromIndex) {
        this.from = from;
        this.to = to;
        this.fromIndex = fromIndex;
    }

    private Move(Pair<Deck, Integer> from, Pair<Deck, Integer> to, int fromIndex, boolean turnedCard) {
        this.from = from;
        this.to = to;
        this.fromIndex = fromIndex;
        this.turnedCard = turnedCard;
    }
    
    /* Getters and setters */

    public Deck getFromDeck() {
        return from.getFirst();
    }

    public int getFromSlot() {
        return from.getSecond();
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public Deck getToDeck() {
        return to.getFirst();
    }

    public int getToSlot() {
        return to.getSecond();
    }

    public Pair<Deck, Integer> getFromPair() {
        return from;
    }

    public Pair<Deck, Integer> getToPair() {
        return to;
    }

    public Move getInverse() {
        return new Move(to, from, fromIndex, turnedCard);
    }

    void setTurnedCard(boolean turnedCard) {
        this.turnedCard = turnedCard;
    }

    public boolean didTurnCard() {
        return turnedCard;
    }

    @Override
    public String toString() {
        return "Move(" + from.getFirst().name() + ", " + from.getSecond()
                + ", " + to.getFirst().name() + ", " + to.getSecond()
                + ", " + fromIndex + ", " + turnedCard + ")";
    }
}
