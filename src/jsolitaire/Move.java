package jsolitaire;

public class Move {

    private final Card card;
    private final Board.Deck from;
    private final Board.Deck to;
    private int rank;

    public Move(Card card, Board.Deck from, Board.Deck to) {
        this.card = card;
        this.from = from;
        this.to = to;
        this.rank = -1;
    }

    public Card getCard() {
        return card;
    }

    public Board.Deck getFrom() {
        return from;
    }

    public Board.Deck getTo() {
        return to;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}
