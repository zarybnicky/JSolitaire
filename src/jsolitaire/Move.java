package jsolitaire;

import java.io.Serializable;
import jsolitaire.Board.Deck;

public class Move implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Deck fromDeck;
    private final int fromSlot;
    private final Deck toDeck;
    private final int toSlot;
    private final int fromIndex;

    public Move(Deck fromDeck, int fromSlot, int fromIndex, Deck toDeck, int toSlot) {
        this.fromDeck = fromDeck;
        this.fromSlot = fromSlot;
        this.fromIndex = fromIndex;
        this.toDeck = toDeck;
        this.toSlot = toSlot;
    }

    public Deck getFromDeck() {
        return fromDeck;
    }

    public int getFromSlot() {
        return fromSlot;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public Deck getToDeck() {
        return toDeck;
    }

    public int getToSlot() {
        return toSlot;
    }
    
    public Move getInverse() {
        return new Move(toDeck, toSlot, fromIndex, fromDeck, fromSlot);
    }
}
