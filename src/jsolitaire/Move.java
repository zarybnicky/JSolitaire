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

/**
 * Represents a move of a stack of cards between two decks.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class Move extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Deck from;
    private final Deck to;
    private final int numCards;
    private boolean turnedCard;

    /**
     * Constructs a move moving <code>numCards</code> cards from
     * <code>from</code> to <code>to</code>.
     *
     * @param from 'From' deck identifier
     * @param to 'To' deck identifier
     * @param numCards Number of cards to move
     */
    public Move(Deck from, Deck to, int numCards) {
        this(from, to, numCards, false);
    }

    private Move(Deck from, Deck to, int numCards, boolean turnedCard) {
        this.from = from;
        this.to = to;
        this.numCards = numCards;
        this.turnedCard = turnedCard;
    }

    /**
     * Returns the number of cards to be moved.
     *
     * @return The number of cards to be moved
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * Returns the deck 'from' which this move is performed.
     *
     * @return The deck 'from' which this move is performed.
     */
    public Deck getFrom() {
        return from;
    }

    /**
     * Returns the deck 'to' which this move is performed.
     *
     * @return The deck 'to' which this move is performed.
     */
    public Deck getTo() {
        return to;
    }

    /**
     * Returns a move opposite to this one, moving the same amount of cards.
     *
     * @return The opposite move
     */
    public Move getInverse() {
        return new Move(to, from, numCards, turnedCard);
    }

    /**
     * Set whether this move resulted in a card on the tableau being turned face
     * up?
     *
     * @param turnedCard a boolean
     */
    void setTurnedCard(boolean turnedCard) {
        this.turnedCard = turnedCard;
    }

    /**
     * Did/will this move result in a card on the tableau being turned face up?
     *
     * @return a boolean
     */
    public boolean didTurnCard() {
        return turnedCard;
    }

    @Override
    public String toString() {
        return "Move(" + from.getType().name() + ", " + from.getSlot()
                + ", " + to.getType().name() + ", " + to.getSlot()
                + ", " + numCards + ", " + turnedCard + ")";
    }
}
