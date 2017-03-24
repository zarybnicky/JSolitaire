 /*
 * License
 */

package jsolitaire;
        
import java.util.Stack;

/*
* Trida reprezentujici zasobnik karet
*/ 
public class CardStack extends CardDeck{
    
    /*
    * Atributy zasobniku
    */ 
    private boolean working = false; 
    
    /*
    * Konstruktor zasobniku
    */ 
    public CardStack(int size){
        deck = new Stack<Card>();
        count = 0;
        total = size;
    }
    
    public CardStack(int size, boolean work){
        deck = new Stack<Card>();
        count = 0;
        total = size;
        working = work;
    }
    
     /*
    * Metody pro pridani karet do zasobniku
    */ 
    public boolean put(Card card){
        if (total == count) return false;
        if (working == true){
            if (count == 0){
                if (card.getRank().equals(Card.Rank.KING)){
                    deck.add(card);
                    count++;
                    return true;
                }
            }else{
                Card c = get();
                if ((!c.similarColorTo(card)) && (c.getRank().ordinal() - 1 == card.getRank().ordinal())){
                    deck.add(card);
                    count++;
                    return true;
                }
            }
            return false;
        }

            deck.add(card);
            count++;
            return true;
    }
    
    public boolean put(CardStack stack){
        boolean ok = true;
        for (int i = 0 ; i < stack.count; i++){
            ok = put(stack.deck.get(i));
            if (ok == false) return ok;
        }
        return true;
    }
    
    /*
    * Metoda pro vlozeni karet z jineho zasobniku
    */ 
    public CardStack pop(Card card){
        int pom = count;
        int index = -1;
        for (int i = 0; i < count; i++){
            if (deck.get(i).equals(card)){ 
                index = i;
                break;
            }
        }
        
        if (index == -1){
            System.out.println("Card not found!");
            return null;
        }

        CardStack c = new CardStack(count - index);
        
        for (int i = index; i < pom; i++){
            c.put(deck.remove(index));
            count--;
        }
      
        return c;
    }
}
