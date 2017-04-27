package jsolitaire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import jsolitaire.Deck.DeckType;

/**
 * Represents the internal state of a single game.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class Board implements Serializable {

    private static final long serialVersionUID = 2L;

    private final StackModel<Move> history = new StackModel<>();
    private final EnumMap<Deck, StackModel<Card>> decks = new EnumMap<>(Deck.class);

    private int time = -1;

    private transient Runnable onWin;
    private transient int state = 0;

    /**
     * Serializes this board to a file.
     *
     * @param f Where to save this board
     * @return Empty Optional on success, full on error, containing the error
     * message.
     */
    public Optional<String> serialize(File f) {
        try (FileOutputStream fileOut = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
        } catch (IOException i) {
            return Optional.of(i.toString());
        }
        return Optional.empty();
    }

    /**
     * Loads a board from a file.
     *
     * @param f Location of the saved board
     * @return Left on error, containing the error message, Right on success.
     */
    public static Either<String, Board> deserialize(File f) {
        try (FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return Either.right((Board) in.readObject());
        } catch (IOException | ClassNotFoundException i) {
            return Either.left(i.toString());
        }
    }

    /**
     * Constructs a Board, shuffles and places cards.
     */
    public Board() {
        Arrays.asList(Deck.values()).forEach(x -> decks.put(x, new StackModel<>()));

        List<Card> deck = Card.getShuffledDeck();
        for (int i = 0, j = 0, k = 1; i < 52; i++) {
            if (k < 8) {
                decks.get(Deck.ofTableau(k - 1)).push(deck.get(i));
                if (++j == k) {
                    decks.get(Deck.ofTableau(k - 1)).getElementAt(j - 1).setFaceUp(true);
                    j = 0;
                    k++;
                }
            } else {
                deck.get(i).setFaceUp(true);
                decks.get(Deck.STOCK).push(deck.get(i));
            }
        }
    }

    /**
     * Attempts to perform a move, returning false on failure.
     *
     * @param move The move to perform
     * @return Whether the move was performed
     */
    public boolean tryToMove(Move move) {
        if (!isValidMove(move)) {
            return false;
        }
        performMove(move);
        history.push(move);
        state++;

        if (decks.get(Deck.STOCK).isEmpty() && decks.get(Deck.WASTE).isEmpty()
                && decks.get(Deck.TABLEAU0).isEmpty() && decks.get(Deck.TABLEAU1).isEmpty()
                && decks.get(Deck.TABLEAU2).isEmpty() && decks.get(Deck.TABLEAU3).isEmpty()
                && decks.get(Deck.TABLEAU4).isEmpty() && decks.get(Deck.TABLEAU5).isEmpty()
                && decks.get(Deck.TABLEAU6).isEmpty()) {
            onWin.run();
        }
        return true;
    }

    /**
     * Undoes the last performed move (a no-op with no already performed moves).
     */
    public void goBack() {
        if (!history.isEmpty()) {
            performMove(history.pop().getInverse());
            state++;
        }
    }

    /**
     * Returns a deck, given a deck identifier.
     *
     * @param x Deck identifier
     * @return The corresponding deck.
     */
    public StackModel<Card> getDeck(Deck x) {
        return decks.get(x);
    }
    
    /**
     * Returns the number of this game state (serves as a game state hash).
     * @return A single unique integer per a board's state.
     */
    public int getState() {
        return state;
    }

    /**
     * Checks whether <code>x</code> is a valid move, given the current game
     * state.
     *
     * Most of game rules is implemented here.
     *
     * @param x The move to check
     * @return Whether the move was valid, given the current game state.
     */
    public boolean isValidMove(Move x) {
        Card from = peekCard(x.getFrom(), x.getNumCards());
        Card to = peekCard(x.getTo(), 0);
        if (from == null || !from.isFaceUp()) {
            return false;
        }
        if (x.getFrom().getType() == DeckType.STOCK && x.getTo().getType() != DeckType.WASTE) {
            return false;
        }
        switch (x.getTo().getType()) {
            case STOCK:
                return x.getFrom().getType() == DeckType.WASTE && getDeck(Deck.STOCK).empty();
            case WASTE:
                return x.getFrom().getType() == DeckType.STOCK;
            case FOUNDATION:
                if (x.getNumCards() > 0) { //can't move a stack to foundation
                    return false;
                }
                if (to == null) {
                    if (decks.get(Deck.FOUNDATION0).peek() != null && decks.get(Deck.FOUNDATION0).peek().getSuit() == from.getSuit()
                            && decks.get(Deck.FOUNDATION1).peek() != null && decks.get(Deck.FOUNDATION1).peek().getSuit() == from.getSuit()
                            && decks.get(Deck.FOUNDATION2).peek() != null && decks.get(Deck.FOUNDATION2).peek().getSuit() == from.getSuit()
                            && decks.get(Deck.FOUNDATION3).peek() != null && decks.get(Deck.FOUNDATION3).peek().getSuit() == from.getSuit()) {
                        return false;
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

    /**
     * Sets the function that will be run as soon as the game is won.
     *
     * @param fn Function that will be run on a victory.
     */
    public void setOnWin(Runnable fn) {
        this.onWin = fn;
    }

    /**
     * Return the current time, as tracked by the timer.
     *
     * @return Current time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the current time, as tracked by the timer.
     *
     * @param time Current time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Transfers cards between decks, according to the given Move.
     *
     * @param The Move to perform. It is not validated here!
     */
    private void performMove(Move move) {
        StackModel<Card> fromDeck = getDeck(move.getFrom());
        StackModel<Card> toDeck = getDeck(move.getTo());

        if (move.didTurnCard()) {
            toDeck.getElementAt(toDeck.getSize() - 1).setFaceUp(false);
        }

        if (move.getFrom().getType() == DeckType.WASTE && move.getTo().getType() == DeckType.STOCK) {
            for (int i = 0; i <= move.getNumCards(); i++) {
                toDeck.push(fromDeck.pop());
            }
            return;
        }

        Stack<Card> stack = new Stack<>();
        for (int i = 0; i <= move.getNumCards(); i++) {
            stack.push(fromDeck.pop());
        }
        for (int i = 0; i <= move.getNumCards(); i++) {
            toDeck.push(stack.pop());
        }

        if (fromDeck.getSize() > 0 && !fromDeck.getElementAt(fromDeck.getSize() - 1).isFaceUp()) {
            fromDeck.getElementAt(fromDeck.getSize() - 1).setFaceUp(true);
            move.setTurnedCard(true);
        }
    }

    /**
     * Returns the nth topmost card of a deck
     *
     * @param deck Deck identifier
     * @param num Which card from the top to return
     * @return The card, or null on failure.
     */
    private Card peekCard(Deck deck, int num) {
        StackModel<Card> x = getDeck(deck);
        try {
            return x.get(x.size() - num - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
