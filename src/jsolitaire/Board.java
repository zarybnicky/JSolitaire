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
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import javax.swing.ListModel;

public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    private final StackModel<Move> history = new StackModel<>();
    private final StackModel<Card> stock = new StackModel<>();
    private final StackModel<Card> waste = new StackModel<>();
    private final List<StackModel<Card>> foundation = Arrays.asList(
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>());
    private final List<StackModel<Card>> tableau = Arrays.asList(
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>(),
            new StackModel<>());
    private final List<Pair<Deck, Integer>> deckCombinations = Arrays.asList(
            Pair.of(Deck.WASTE, 0),
            Pair.of(Deck.TABLEAU, 0),
            Pair.of(Deck.TABLEAU, 1),
            Pair.of(Deck.TABLEAU, 2),
            Pair.of(Deck.TABLEAU, 3),
            Pair.of(Deck.TABLEAU, 4),
            Pair.of(Deck.TABLEAU, 5),
            Pair.of(Deck.TABLEAU, 6),
            Pair.of(Deck.FOUNDATION, 0),
            Pair.of(Deck.FOUNDATION, 1),
            Pair.of(Deck.FOUNDATION, 2),
            Pair.of(Deck.FOUNDATION, 3),
            Pair.of(Deck.STOCK, 0));

    private int time = -1;

    private transient int hintsIndex;
    private transient List<Move> hints = null;
    private transient Timer timer = null;
    private transient Runnable onWin;

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
                tableau.get(k - 1).push(deck.get(i));
                if (++j == k) {
                    tableau.get(k - 1).getElementAt(j - 1).setFaceUp(true);
                    j = 0;
                    k++;
                }
            } else {
                deck.get(i).setFaceUp(true);
                stock.push(deck.get(i));
            }
        }
    }

    public boolean tryToMove(Move move) {
        if (!isValidMove(move)) {
            return false;
        }
        hints = null;
        performMove(move);
        history.push(move);
        if (stock.isEmpty() && waste.isEmpty() && tableau.stream().allMatch(StackModel::isEmpty)) {
            onWin.run();
        }
        return true;
    }

    public void goBack() {
        hints = null;
        if (!history.isEmpty()) {
            performMove(history.pop().getInverse());
        }
    }

    public ListModel<Card> getListModel(Deck deck, int slot) {
        return getDeckInternal(deck, slot);
    }

    public Optional<Move> getHint() {
        if (hints != null) {
            if (hints.isEmpty()) {
                return Optional.empty();
            }
            Move x = hints.get(hintsIndex);
            hintsIndex = (hintsIndex + 1) % hints.size();
            return Optional.of(x);
        }
        hints = new ArrayList<>();
        deckCombinations.forEach(from -> deckCombinations.forEach(to -> {
            for (int index = getUsableSize(from); index >= 0; index--) {
                if (!from.equals(to)) {
                    Move x = new Move(from, to, index);
                    if (isValidMove(x)) {
                        hints.add(x);
                    }
                }
            }
        }));
        hintsIndex = 0;
        return getHint();
    }

    public void setOnWin(Runnable fn) {
        this.onWin = fn;
    }

    public void startTimer(Consumer<Integer> fn) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
                fn.accept(time);
            }
        };

        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
        }
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public int getTime() {
        return time;
    }

    private boolean isValidMove(Move x) {
        Card from = peekCard(x.getFromPair(), x.getFromIndex());
        Card to = peekCard(x.getToPair(), 0);
        if (from == null || !from.isFaceUp()) {
            return false;
        }
        if (x.getFromDeck() == Deck.STOCK && x.getToDeck() != Deck.WASTE) {
            return false;
        }
        switch (x.getToDeck()) {
            case STOCK:
                return x.getFromDeck() == Deck.WASTE && getDeckInternal(Deck.STOCK, 0).empty();
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
        StackModel<Card> fromDeck = getDeckPair(move.getFromPair());
        StackModel<Card> toDeck = getDeckPair(move.getToPair());

        if (move.getFromDeck() == Deck.WASTE && move.getToDeck() == Deck.STOCK) {
            for (int i = 0; i <= move.getFromIndex(); i++) {
                toDeck.push(fromDeck.pop());
            }
            return;
        }

        Stack<Card> stack = new Stack<>();
        for (int i = 0; i <= move.getFromIndex(); i++) {
            stack.push(fromDeck.pop());
        }
        for (int i = 0; i <= move.getFromIndex(); i++) {
            toDeck.push(stack.pop());
        }
    }

    private Card peekCard(Pair<Deck, Integer> deck, int num) {
        StackModel<Card> x = getDeckPair(deck);
        try {
            return x.get(x.size() - num - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private StackModel<Card> getDeckPair(Pair<Deck, Integer> x) {
        return getDeckInternal(x.getFirst(), x.getSecond());
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

    private int getUsableSize(Pair<Deck, Integer> x) {
        switch (x.getFirst()) {
            case FOUNDATION:
            case STOCK:
            case WASTE:
                return getDeckPair(x).getSize() > 0 ? 0 : -1;
            default:
                StackModel<Card> deck = getDeckPair(x);
                if (deck.getSize() == 0) {
                    return -1;
                }
                int index = 0;
                Card card = deck.lastElement();
                for (int i = deck.getSize() - 2; i >= 0; i--) {
                    Card nextCard = deck.get(i);
                    if (!card.isAlternateColor(nextCard) || !card.precedes(nextCard)) {
                        break;
                    }
                    index++;
                    card = nextCard;
                }
                return index;
        }
    }
}
