package blackjack;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Class that represents persons in the game. A person will be either a dealer or a player, meaning that
 * there won't be any Person objects, hence it's abstract.
 * @author public
 */
abstract class Person{
	// Attributes
	protected ArrayList<Card> hand;
	boolean ace_values_eleven;
	
	protected Person(){
		hand = new ArrayList<Card>();
	}
	
	
	/* Methods */ 
	
	

	/**
	 * Hit draws a card from shoe to the default hand (either the player's or the dealer's).
	 * @param s is the shoe
	 */
	void hit(Shoe s){
		this.hand.add(s.drawCard());
		
	}
	
	/**
	 * Hit draws a card from shoe to a specific hand.
	 * @param s is the shoe
	 * @param hand - hand to which card is drawn
	 */
	void hit(Shoe s, ArrayList<Card> hand){
		hand.add(s.drawCard());
	}
	/**
	 * Stand does nothing, it is used only as an abstraction of the player's actions.
	 */
	void stand(){
		/* do nothing */
	}
	
	/**
	 * Checks if a hand is a Blackjack hand. A Blackjack hand only has two cards whose values sum to 21.
	 * @return 
	 */
	boolean hasBlackjack(){
		if(this.getHandValue() == 21 && hand.size() == 2)
			return true;
		return false;
	}
	
	/**
	 * Gets the value of a hand. Aces are worth 1 by default. If there is at least one ace, it's decided if it
	 * is worth 1 or 11.
	 * @return integer with hand value
	 */
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
        		totalvalue += 10; // put ace as 11
        		ace_values_eleven = true;
        	}
        ace_values_eleven = false;
        }
    	return totalvalue;
    }
    
    /**
     * Checks if in the hand there is an Ace that values 11.
     * @return True if there's and Ace, false otherwise
     */
    boolean checkAceEleven(){
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
        		return true;
        	}
        }
        return false;
    }
    
    /**
     * Clears a hand, making it empty for a new game.
     */
    void clearHand(){
    	this.hand.clear();
    }
    
    

}
