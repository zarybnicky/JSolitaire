/*
 * License
 */
package jsolitaire;

import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Observable;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;
    private transient ImageIcon icon;
    private transient boolean greyedOut = false;
    
    public static final ImageIcon BACK = new ImageIcon(Card.class.getResource("/resources/BACK.gif"));
    public static final ImageIcon MISSING = new ImageIcon(Card.class.getResource("/resources/missing.gif"));

    public Card(Suit suit, Rank rank, boolean faceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = faceUp;
        if (suit == null && rank == null){
            icon = new ImageIcon(Card.class.getResource("/resources/" + "missing" + ".gif"), "missing");
        }else{
            icon = new ImageIcon(Card.class.getResource("/resources/" + getIconName() + ".gif"), getIconName());
        }
    }

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

    public boolean isAlternateColor(Card c) {
        boolean red = c.getSuit() == Suit.HEARTS || c.getSuit() == Suit.DIAMONDS;
        return (suit == Suit.HEARTS || suit == Suit.DIAMONDS) ? !red : red;
    }

    public boolean precedes(Card c) {
        return rank.ordinal() + 1 == c.getRank().ordinal();
    }

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

    public enum Suit {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS;
    }

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
