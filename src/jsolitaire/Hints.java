package jsolitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Manages hinting of possible moves.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class Hints {

    private int hintsIndex;
    private List<Move> hints = null;
    private Board target = null;

    public void setTarget(Board target) {
        this.target = target;
    }

    /**
     * Returns a valid move from the current state, iterating over all
     * possibilites.
     *
     * @return A move if there is one possible, empty if no move is possible.
     */
    public Optional<Move> getNextHint() {
        if (target == null) {
            return Optional.empty();
        }
        if (hints != null) {
            if (hints.isEmpty()) {
                return Optional.empty();
            }
            Move x = hints.get(hintsIndex);
            hintsIndex = (hintsIndex + 1) % hints.size();
            return Optional.of(x);
        }
        hints = new ArrayList<>();
        List<Deck> xs = Arrays.asList(Deck.values());
        xs.forEach(from -> xs.forEach(to -> {
            for (int index = getUsableSize(from); index >= 0; index--) {
                if (!from.equals(to)) {
                    Move x = new Move(from, to, index);
                    if (target.isValidMove(x)) {
                        hints.add(x);
                    }
                }
            }
        }));
        hintsIndex = 0;
        return getNextHint();
    }

    /**
     * Returns the index of the bottommost card which the player can move
     * (mainly for tableau decks).
     *
     * @param Deck identifier
     * @return Index of the bottommost moveable card (from the top).
     */
    private int getUsableSize(Deck x) {
        switch (x.getType()) {
            case FOUNDATION:
            case STOCK:
            case WASTE:
                return target.getDeck(x).getSize() > 0 ? 0 : -1;
            default:
                StackModel<Card> deck = target.getDeck(x);
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

    /**
     * Clear current hints and recalculate them on next request.
     */
    public void reset() {
        hints = null;
    }
}
