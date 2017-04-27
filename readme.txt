Solitaire
xzaryb00, xzales12

A very simple implementation (game logic has ~500 CLOC, GUI ~900) of the
classic table game Solitaire, using the Swing toolkit.

Controls should be mostly intuitive, following existing implementations
(double-click to move to foundation, drag-n-drop otherwise). The only thing
missing compared to standard implementations is a end-game animation (after
stock is empty).


Design patterns used (or discovered after implemetation :) ):
---------------------
Factory - Deck.getShuffledDeck is a factory for many cards.
Adapter - StackModel is an adapter from Stack to ListModel
Adapter - SimpleListListener is an adapter from ListDataListener to Observer
Iterator - Used many times, eg. when generating hints in Hints
Command - Move is a command that Board interprets
Interpreter - Board interprets Moves
Memento - Board is able to return to any of its previous states
Observer - JList observes a StackModel, GamePanel observes a Board (onWin)
Multiton - Enums are in fact a Multiton (Deck, DeckType, Rank, Suit, ... but mostly Deck)
Composite - Hints is a composite of Moves
Null object - Optional (in Java 8) is such an object, used in the code
