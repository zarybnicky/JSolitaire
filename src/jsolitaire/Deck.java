package jsolitaire;

/**
 * A deck identifier
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public enum Deck {
    WASTE(DeckType.WASTE, 0),
    TABLEAU0(DeckType.TABLEAU, 0),
    TABLEAU1(DeckType.TABLEAU, 1),
    TABLEAU2(DeckType.TABLEAU, 2),
    TABLEAU3(DeckType.TABLEAU, 3),
    TABLEAU4(DeckType.TABLEAU, 4),
    TABLEAU5(DeckType.TABLEAU, 5),
    TABLEAU6(DeckType.TABLEAU, 6),
    FOUNDATION0(DeckType.FOUNDATION, 0),
    FOUNDATION1(DeckType.FOUNDATION, 1),
    FOUNDATION2(DeckType.FOUNDATION, 2),
    FOUNDATION3(DeckType.FOUNDATION, 3),
    STOCK(DeckType.STOCK, 0);

    /**
     * A deck type
     */
    public enum DeckType {
        STOCK,
        WASTE,
        FOUNDATION,
        TABLEAU
    };

    private final DeckType type;
    private final int slot;

    private Deck(DeckType type, int slot) {
        this.type = type;
        this.slot = slot;
    }

    /**
     * Returns this deck id's type
     *
     * @return A DeckType...
     */
    public DeckType getType() {
        return type;
    }

    /**
     * Returns this deck id's slot number (for tableau and foundation only).
     *
     * @return slot number
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Returns a TABLEAUX deck id, where X is <code>slot</code>.
     *
     * @param slot The tableau number to return
     * @return TABLEAUX
     */
    public static Deck ofTableau(int slot) {
        switch (slot) {
            case 0:
                return Deck.TABLEAU0;
            case 1:
                return Deck.TABLEAU1;
            case 2:
                return Deck.TABLEAU2;
            case 3:
                return Deck.TABLEAU3;
            case 4:
                return Deck.TABLEAU4;
            case 5:
                return Deck.TABLEAU5;
            case 6:
                return Deck.TABLEAU6;
            default:
                return null;
        }
    }

    /**
     * Returns a FOUNDATIONX deck id, where X is <code>slot</code>.
     *
     * @param slot The foundation number to return
     * @return FOUNDATIONX
     */
    public static Deck ofFoundation(int slot) {
        switch (slot) {
            case 0:
                return Deck.FOUNDATION0;
            case 1:
                return Deck.FOUNDATION1;
            case 2:
                return Deck.FOUNDATION2;
            case 3:
                return Deck.FOUNDATION3;
            default:
                return null;
        }
    }
}
