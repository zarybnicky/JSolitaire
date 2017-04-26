package jsolitaire;

import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

/**
 * Represents a single card
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ClassLoader LOADER = Card.class.getClassLoader();

    // Card attributes
    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;
    private transient ImageIcon icon;
    private transient boolean greyedOut = false;

    // Image of card
    public static final ImageIcon BACK = new ImageIcon(LOADER.getResource("cards/BACK.gif"));
    public static final ImageIcon MISSING = new ImageIcon(LOADER.getResource("cards/missing.gif"));

    /**
     * Constructs a Card
     *
     * @param suit The card's suit
     * @param rank The card's rank
     * @param faceUp Whether the card is face up
     */
    public Card(Suit suit, Rank rank, boolean faceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = faceUp;

        if (suit == null && rank == null) {
            icon = new ImageIcon(LOADER.getResource("cards/missing.gif"), "missing");
        } else {
            icon = new ImageIcon(LOADER.getResource("cards/" + getIconName() + ".gif"), getIconName());
        }
    }

    /* Getters and setters */

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public ImageIcon getIcon() {
        ImageIcon i = isFaceUp() ? icon : BACK;
        if (isGreyedOut()) {
            i = new ImageIcon(GrayFilter.createDisabledImage(i.getImage()));
        }
        if (i.getImageLoadStatus() == MediaTracker.ERRORED) {
            i = MISSING;
        }
        return i;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isGreyedOut() {
        return greyedOut;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public void setGreyedOut(boolean greyedOut) {
        this.greyedOut = greyedOut;
    }

    /**
     * Is <code>c</code> the opposite color from this card?
     *
     * @param c The card to match against
     * @return Do the cards have opposite colors
     */
    public boolean isAlternateColor(Card c) {
        boolean red = c.getSuit() == Suit.HEARTS || c.getSuit() == Suit.DIAMONDS;
        return (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? !red : red;
    }

    /**
     * Does this card precede <code>c</code>?
     *
     * @param c The card to match against
     * @return Does this card precede <code>c</code>?
     */
    public boolean precedes(Card c) {
        return rank.ordinal() + 1 == c.getRank().ordinal();
    }

    /**
     * Returns this card's icon's name.
     *
     * @return String identifier of the card's icon.
     */
    public String getIconName() {
        String str = String.valueOf(rank);
        switch (suit) {
            case HEARTS:
                return str + "H";
            case DIAMONDS:
                return str + "D";
            case SPADES:
                return str + "S";
            case CLUBS:
                return str + "C";
        }
        return null;
    }

    /**
     * A card's suit
     */
    public enum Suit {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS;
    }

    /**
     * A card's rank
     */
    public enum Rank {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Card && suit == ((Card) obj).getSuit() && rank == ((Card) obj).getRank();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 7 + 41 * hash + Objects.hashCode(suit);
        hash = 41 * hash + Objects.hashCode(rank);
        return hash;
    }

    private void readObject(final ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        this.icon = new ImageIcon(Card.class.getResource("/resources/" + getIconName() + ".gif"), getIconName());
    }
}
