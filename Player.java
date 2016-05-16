package blackjack;

import java.util.ArrayList;
/**
 * Class that represents the player. The player can do actions such as hit, stand, bet, as well as win, lose, etc.
 * @author public
 *
 */
class Player extends Person{
	/*Fields*/
	private int betValue;
	private float balance; // Private because no one else should be allowed to 
	  // access the player's money
	int prev_bet = 0;
	private ArrayList<ArrayList<Card>> splitHands;
	boolean lastHand;
	//private ArrayList<Card>[] splitHands;
	
	/*Constructors*/
	/**
	 * Constructor of Player
	 * @param balance
	 */
	public Player(int balance) {
		this.balance = balance;
		splitHands = new ArrayList<ArrayList<Card>>(4);
	}
	
	
	/*Methods*/
	/**
	 * Method that updates the player's balance according to the type of win.
	 * @param table contains the win percentages
	 * @param typeOfWin code for the type of win (blackjack, double down, insurance)
	 */
	void win(Table table, int typeOfWin){
		result = "win";
		switch(typeOfWin){
		case BlackjackGame.BLACKJACK_WIN:
			this.balance += table.winblackjack * this.betValue;
			System.out.println("player wins and his current balance is " + this.balance);
			break;
		case BlackjackGame.INSURANCE_WIN:
			this.balance += table.insurance * this.betValue;
			System.out.println("player wins insurance and his current balance is " + this.balance);
			break;
		case BlackjackGame.DOUBLE_DOWN_WIN:
			this.balance += 2 * table.win * this.betValue;
			System.out.println("player wins and his current balance is " + this.balance);
			break;
		default:	//this should never happen!
			System.out.println("ERROR: method win. Exiting");
			System.exit(-2);
		}
	}
	
	/**
	 * Method that updates the player's balance for a normal win (player's hand value is greater
	 * than the dealer's)
	 * @param table contains the normal win percentage
	 */
	void win(Table table){
		result = "win";
		this.balance += table.win * this.betValue;
		System.out.println("player wins and his current balance is " + this.balance);
	}
	
	/**
	 * Method that updates the player balance when loses.
	 */
	void lose(){
		result = "lose";
		System.out.println("player loses and his current balance is " + this.getBalance());
	}
	
	/**
	 * Method that updates the player balance when there's a push.
	 */
	void push(){
		result = "lose";
		this.balance += this.betValue;
		System.out.println("player pushes and his current balance is " + this.getBalance());
		System.out.println("PUSH");
	}
	
	/**
	 * Checks if player has only two cards on his hand and if those cards are of equal
	 * rank
	 * @return true if cards are of same rank, false otherwise
	 */
	boolean hasTwoEqualCards(){
		if(this.hand.get(0).equals(this.hand.get(1)) && this.hand.size() == 2){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Checks if bet is within limits.
	 * @param value bet value
	 * @param table contains the max bet and min bet values
	 * @return integer with the value of the bet. If bet invalid, returns 0
	 */
    int bet(int value, Table table){
    	System.out.println("bet value -> "+value);
    	
    	if(value < table.minbet){
    		System.out.println("Bet must be at least "+ table.minbet);
    		return 0;
    	} else if(value > table.maxbet){
    		System.out.println("Bet must be less then "+ table.maxbet);
    		return 0;
    	}else if(value > this.balance){
    		System.out.println("Bet must be less then balance "+ this.balance);
    		return 0;
    	}else {
    		this.balance -= value;
    		this.betValue = value;
    		this.prev_bet = value;
    		return value; // bet ok!
    	}
    }
    
    /**
     * Updates balance and bet value for doubling down.
     * @param value original bet value
     */
    void betDoubleDown(int value){	//for the doubledown bet
    	this.balance -= value;
    	this.betValue += value;	//+=
    }
    
    /**
     * Getter for player's balance
     * @return balance
     */
    float getBalance(){
    	return this.balance;
    }
    
    /**
     * Getter for player's current bet value
     * @return bet value
     */
    int getBetValue(){
    	return this.betValue;
    }

    /**
     * Setter for player's balance.
     * @param balance
     */
	void setBalance(float balance) {
		this.balance += balance;
	}
	
	/**
	 * Empties player main and split hands.
	 */
    void clearHand(){
    	this.hand.clear();
    	this.splitHands.clear();
    }
    
    /**
     * Add a split hand, for when there's splitting
     * @param hand hand to add
     */
	void addSplitHand(ArrayList<Card> hand){
		this.splitHands.add(hand);
	}
	
	/**
	 * Changes the players current hand with the next split hand.
	 */
	void changeHand(){
		this.hand = splitHands.remove(0);
		BlackjackGame.doneSplits++;
		if(splitHands.isEmpty()) lastHand=true;
	}
	
	/**
	 * Checks if split hand list is empty
	 * @return true if empty, false otherwise
	 */
	boolean splitHandisEmpty(){
		if (this.splitHands.isEmpty())
			return true;
		return false;
	}
}
