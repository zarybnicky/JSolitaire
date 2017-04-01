/*
 * License
 */
package jsolitaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import javax.swing.ListModel;

public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    private final StackModel<Move> history = new StackModel<>();
    private final StackModel<Card> stock = new StackModel<>();
    private final StackModel<Card> waste = new StackModel<>();
    private final List<StackModel<Card>> foundation = Arrays.asList(new StackModel<>(), new StackModel<>(), new StackModel<>(), new StackModel<>());
    private final List<StackModel<Card>> tableau = Arrays.asList(new StackModel<>(), new StackModel<>(), new StackModel<>(), new StackModel<>(), new StackModel<>(), new StackModel<>(), new StackModel<>());

    public enum Deck {
        STOCK,
        WASTE,
        FOUNDATION,
        TABLEAU
    }

    public Optional<String> serialize(File f) {
        try (FileOutputStream fileOut = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException i) {
            return Optional.of(i.toString());
        }
        return Optional.empty();
    }

    public static Either<String, Board> deserialize(File f) {
        try (FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return Either.right((Board) in.readObject());
        } catch (IOException | ClassNotFoundException i) {
            return Either.left(i.toString());
        }
    }

    public void resetGame() {
        history.clear();
        stock.clear();
        waste.clear();
        foundation.forEach(Collection::clear);
        tableau.forEach(Collection::clear);

        List<Card> deck = getShuffledDeck();
        for (int i = 0, j = 0, k = 1; i < 52; i++) {
            if (k < 8) {
                tableau.get(k - 1).add(deck.get(i));
                if (++j == k) {
                    tableau.get(k - 1).getElementAt(j - 1).setFaceUp(true);
                    j = 0;
                    k++;
                }
            } else {
                deck.get(i).setFaceUp(true);
                stock.add(deck.get(i));
            }
        }
    }

    public boolean isGameWon() {
        return stock.isEmpty() && waste.isEmpty() && tableau.stream().allMatch(StackModel::isEmpty);
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

    public ListModel<Card> getListModel(Deck deck, int slot) {
        return getDeckInternal(deck, slot);
    }

    private boolean isValidMove(Move x) {
        Card from = peekCard(x.getFromDeck(), x.getFromSlot(), x.getFromIndex());
        Card to = peekCard(x.getToDeck(), x.getToSlot(), 0);
        if (from == null) {
            return false;
        }
        switch (x.getToDeck()) {
            case STOCK:
                return x.getFromDeck() == Deck.WASTE;
            case WASTE:
                return x.getFromDeck() == Deck.STOCK;
            case FOUNDATION:
                if (x.getFromIndex() > 0) { //can't move a stack to foundation
                    return false;
                }
                if (to == null) {
                    for (int i = 0; i < 4; i++) {
                        if (foundation.get(i).peek() != null && foundation.get(i).peek().getSuit() == from.getSuit()) {
                            return false;
                        }
                    }
                    return from.getRank() == Card.Rank.ACE;
                }
                return from.getSuit() == to.getSuit() && to.precedes(from);
            default:
                if (to == null) {
                    return from.getRank() == Card.Rank.KING;
                }
                return from.isAlternateColor(to) && from.precedes(to);
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

    private void performMove(Move move) {
        StackModel<Card> fromDeck = getDeckInternal(move.getFromDeck(), move.getFromSlot());
        StackModel<Card> toDeck = getDeckInternal(move.getToDeck(), move.getToSlot());

        Stack<Card> stack = new Stack<>();
        for (int i = 0; i <= move.getFromIndex(); i++) {
            stack.push(fromDeck.pop());
        }
        for (int i = 0; i <= move.getFromIndex(); i++) {
            toDeck.push(stack.pop());
        }
        
    }

    private Card peekCard(Deck deck, int slot, int num) {
        StackModel<Card> x = getDeckInternal(deck, slot);
        try {
            return x.get(x.size() - num - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private StackModel<Card> getDeckInternal(Deck deck, int slot) {
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
