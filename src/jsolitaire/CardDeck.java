 /*
 * License
 */

package jsolitaire;

import java.util.Stack;

/*
*Trida reprezentujici balicek karet
*/
public class CardDeck extends java.lang.Object{
    
    /*
    * Atributy balicku
    */ 
    private Card.Suit target = null; 
    protected int count;
    protected int total;
    protected Stack<Card> deck;
    
    /*
    * Konstruktor balicku karet
    */
    public CardDeck(){
    }
    
    public CardDeck(int size){
        deck = new Stack<Card>();
        count = 0;
        total = size;
    }
    
    public CardDeck(int size, Card.Suit color){
        deck = new Stack<Card>();
        count = 0;
        total = size;
        target = color;
    }
    
    /*
    * Metoda pro ziskani velikosti balicku
    */ 
    public int size(){
        return count;    
    }
    
    public boolean isEmpty(){
        if (count == 0){ 
            return true;
        }else{ 
            return false;
        }
    }
    
    /*
    * Metoda pro pridavani karty na vrchol balicku
    */ 
    public boolean put(Card card){
        if (target != null){ // Jde o cílový balíček.
            if (count == 0){ 
                if ((card.getSuit().equals(target)) && (card.getRank().equals(Card.Rank.ACE))){
                    deck.add(card);
                    count++;
                    return true;
                }
            }else{
                Card c = get();
                if ((card.getSuit().equals(target)) && (card.getRank().ordinal() - 1 == c.getRank().ordinal())){
                    deck.add(card);
                    count++;
                    return true;
                }
            }
            return false;
        }
        boolean ok = true;
        if (total != count){
            ok = deck.add(card);
            if (ok == false)return ok;
            count++;
        } else {
            System.out.println("Deck is full!");
            return false;
        }
        return true;
    }
    
    public Card get(){
        if (count != 0){
            return deck.get(count-1);
        }else{
            return null;
        }
    }
    
    public Card get(int index){
        if ((count != 0) && (index < count-1)){
            return deck.get(1-index);
        }else{
            return null;
        }
    }
    
    /*
    * Metoda pro odebarni horni karty balicku
    */ 
    public Card pop(){
        if (0 != count){
            Card pom = deck.get(count-1);
            deck.remove(count-1);
            count--;
            return pom;
        }
        
        return null;
    }
    
}
