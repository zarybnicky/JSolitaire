package jsolitaire;

import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    // Card attributes
    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;
    private transient ImageIcon icon;
    private transient boolean greyedOut = false;

    /**
     * The back side of a card
     */
    public static final ImageIcon BACK = new ImageIcon(Card.class.getResource("cards/BACK.gif"));
    
    /**
     * An icon to display if a card icon is missing (or is null).
     */
    public static final ImageIcon MISSING = new ImageIcon(Card.class.getResource("cards/missing.gif"));

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
            icon = new ImageIcon(Card.class.getResource("cards/missing.gif"), "missing");
        } else {
            icon = new ImageIcon(Card.class.getResource("cards/" + getIconName() + ".gif"), getIconName());
        }
    }

    /**
     * Creates a shuffled deck
     *
     * @return A shuffled deck
     */
    public static List<Card> getShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();

        // Cards generator
        for (int i = 0; i < 52; i++) {
            deck.add(new Card(suits[i / 13], ranks[i % 13], false));
        }

        Collections.shuffle(deck);
        return deck;
    }

    /**
     * Returns this card's suit.
     * 
     * @return This card's suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns this card's rank.
     * 
     * @return This card's rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns this card's icon (or back side, if !isFaceUp())
     * 
     * @return This card's icon
     */
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

    /**
     * Does this card face upwards?
     * @return Is this card face up?
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Is this card greyed out? (For hinting).
     * @return Is this card greyed out?
     */
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
        this.icon = new ImageIcon(Card.class.getResource("cards/" + getIconName() + ".gif"), getIconName());
    }
}
