/*
 * License
 */
package jsolitaire;

import java.util.Objects;
import javax.swing.ImageIcon;

public class Card {

    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;
    private final ImageIcon icon;

    public Card(Suit suit, Rank rank, boolean faceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = faceUp;
        icon = new ImageIcon(Card.class.getResource("/resources/" + getIconName() + ".gif"), getIconName());
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public ImageIcon getIcon() {
        return icon;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
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
}
