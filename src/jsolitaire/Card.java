/*
 * License
 */
package jsolitaire;

public class Card {

    private final Suit suit;
    private final Rank rank;
    private boolean faceUp;

    public Card(Suit suit, Rank rank, boolean faceUp) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = faceUp;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
    
     public boolean similarColorTo(Card c){
        switch(suit){
            case HEARTS:
            case DIAMONDS:  if (c.suit.equals(Suit.HEARTS) || c.suit.equals(Suit.DIAMONDS)){
                                return true;
                            }else{
                                return false;
                            }
                
            case CLUBS:
            case SPADES:    if (c.suit.equals(Suit.CLUBS) || c.suit.equals(Suit.SPADES)){
                                return true;
                             }else{
                                return false;
                            }
        }
        return false;
    }


    public enum Suit {
        HEARTS,
        DIAMONDS,
        SPADES,
        CLUBS
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
}
