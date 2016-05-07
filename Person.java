package blackjack;

import java.util.ArrayList;
import java.util.Iterator;

/* Person is an abstract class because we don't want 
 * any objects of type person, only dealer or player */

abstract class Person{
	// Attributes
	protected ArrayList<Card> hand;
	private int balance; // Private because no one else should be allowed to 
						  // access the player's money
	
	protected Person(int balance){
		hand = new ArrayList<Card>();
		this.balance = balance;
	}		
	
	
	/* Methods */ 
	
	/* In the first turn Players should draw two cards */
	void firstHit(Shoe s){
		this.hand.add(s.drawCard());
		this.hand.add(s.drawCard());
		// return "dealers' hand" + hand.get(0) + "X";
	}

	/* Hit - draw a card from shoe */
	void hit(Shoe s){
		this.hand.add(s.drawCard());
	}
	
	void stand(){
		/* do nothing */
	}
	
	/* Check if there's a blackjack - Caution! Call this method only in the first turn */
	boolean hasBlackjack(){
		if(this.getHandValue() == 21)
			return true;
		return false;
	}

	/* Get total value from a hand  */
    int getHandValue(){
        Iterator<Card> carditerator = this.hand.iterator();
        Card auxcard;
        int cardvalue, totalvalue=0, nr_aces=0;
        
        while (carditerator.hasNext()) {
            auxcard = carditerator.next();
            cardvalue = auxcard.getValue();
            
            if(cardvalue==1){ // its an ace
            	nr_aces++;
            }
           	totalvalue += cardvalue;
        }
        // put an aces as 11
        if(nr_aces != 0){
        	if(totalvalue + 10 <= 21){
        		totalvalue += 10;
        	}
        }
    	return totalvalue;
    }
    
    /* Bet - Checks if bet is within limits */
    int bet(int value, Table table){
    	if(value < table.minbet){
    		return -1; // bet bellow min
    	} else if(value > table.maxbet){
    		return 1; // bet above max
    	} else {
    		this.balance -= value;	
    		return 0; // bet ok!
    	}
    }
    
    /* Get Balance*/
    int getBalance(){
    	return this.balance;
    }
    

}
