package jsolitaire;

import java.io.Serializable;
import java.util.Observable;
import jsolitaire.Board.Deck;

public class Move extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Pair<Deck, Integer> from;
    private final Pair<Deck, Integer> to;
    private final int fromIndex;
    private boolean turnedCard = false;

    public Move(Deck fromDeck, int fromSlot, int fromIndex, Deck toDeck, int toSlot) {
        this.from = Pair.of(fromDeck, fromSlot);
        this.to = Pair.of(toDeck, toSlot);
        this.fromIndex = fromIndex;
    }

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
