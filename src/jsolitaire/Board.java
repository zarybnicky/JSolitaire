/*
 * License
 */
package jsolitaire;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JComponent;

public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient JComponent temp; //leaving this here just for the "transient"

    private final Stack<Move> history = new Stack<>();
    private final Deque<Card> stock = new ArrayDeque<>();
    private final Deque<Card> waste = new ArrayDeque<>();
    private final List<Deque<Card>> foundation = Arrays.asList(new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>());
    private final List<Deque<Card>> tableau = Arrays.asList(new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>());

    public enum Deck {
        STOCK,
        WASTE,
        FOUNDATION,
        TABLEAU
    }

    public Board() {
        List<Card> deck = getShuffledDeck();
        for (int i = 0, j = 0, k = 1; i < 52; i++) {
            if (k < 8) {
                tableau.get(k - 1).add(deck.get(i));
                if (++j == k) {
                    j = 0;
                    k++;
                }
            } else {
                stock.add(deck.get(i));
            }
        }
    }

    private List<Card> getShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();
        for (int i = 0; i < 52; i++) {
            deck.add(new Card(suits[i / 13], ranks[i % 13], false));
        }
        Collections.shuffle(deck);
        return deck;
    }

    public boolean isGameWon() {
        return stock.isEmpty() && waste.isEmpty() && tableau.stream().allMatch(Queue::isEmpty);
    }

    public boolean tryToMove(Move move) {
        if (!isValidMove(move)) {
            return false;
        }
        performMove(move);
        history.push(move);
        return true;
    }

    public void goBack() {
        if (!history.isEmpty()) {
            performMove(history.pop().getInverse());
        }
    }

    public boolean isValidMove(Move x) {
        Card from = peekCard(x.getFromDeck(), x.getFromSlot(), x.getFromIndex());
        Card to = peekCard(x.getToDeck(), x.getToSlot(), 0);

        switch (x.getToDeck()) {
            case STOCK:
            case WASTE:
                return false;
            case FOUNDATION:
                if (x.getFromIndex() > 0) { //can't move a stack to foundation
                    return false;
                }
                if (to == null) {
                    return foundation.stream().allMatch(f -> f.peek() == null || f.peek().getSuit() != from.getSuit())
                            && from.getRank() == Card.Rank.ACE;
                }
                return from.getSuit() == to.getSuit() && from.precedes(to);
            default:
                if (to == null) {
                    return from.getRank() == Card.Rank.KING;
                }
                return from.isAlternateColor(to) && from.precedes(to);
        }
    }
    
    private void performMove(Move move) {
        Deque<Card> fromDeck = getDeck(move.getFromDeck(), move.getFromIndex());
        Deque<Card> toDeck = getDeck(move.getToDeck(), move.getToSlot());
        
        Deque<Card> xs = new ArrayDeque<>();
        for (int i = 0; i <= move.getFromIndex(); i++) {
            xs.addFirst(fromDeck.removeFirst());
        }
        for (int i = 0; i <= move.getFromIndex(); i++) {
            toDeck.addFirst(xs.removeLast());
        }
    }
    
    private Card peekCard(Deck deck, int slot, int num) {
        return getDeck(deck, slot).stream().skip(num).findFirst().orElse(null);
    }
    
    private Deque<Card> getDeck(Deck deck, int slot) {
        switch (deck) {
            case STOCK:
                return stock;
            case WASTE:
                return waste;
            case FOUNDATION:
                return foundation.get(slot);
            default:
                return tableau.get(slot);
        }
    }
}
