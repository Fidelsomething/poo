package blackjack;

import java.util.ArrayList;

class Player extends Person{
	/*Fields*/
	private int betValue;
	private float balance; // Private because no one else should be allowed to 
	  // access the player's money
	int prev_bet = 0;
	private ArrayList<Card>[] splitHands = null;	//for when player splits a hand
	
	
	/*Constructors*/
	public Player(int balance) {
		this.balance = balance;
	}
	
	
	/*Methods*/
	//TODO mostrar funcoes
	void win(Table table, int typeOfWin){
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
	void win(Table table){
		this.balance += table.win * this.betValue;
		System.out.println("player wins and his current balance is " + this.balance);
	}
	
	//TODO apagar metodo, win alterado para dar para todos
	void win(Table table, boolean insurance){
		this.balance += table.insurance * this.betValue;
		System.out.println("player wins insurance and his current balance is " + this.balance);
	}
	
	//TODO apagar metodo, win alterado para dar para todos
	void blackjackWin(Table table){
		this.balance += table.winblackjack * this.betValue;
		System.out.println("player wins and his current balance is " + this.balance);
	}
	
	void lose(){
		System.out.println("player loses and his current balance is " + this.getBalance());
	}
	void push(){
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
	
	/* Bet - Checks if bet is within limits */
    int bet(int value, Table table){
    	if(value < table.minbet){
    		System.out.println("Bet must be at least "+ table.minbet);
    		return 0;
    	} else if(value > table.maxbet){
    		System.out.println("Bet must be less then "+ table.maxbet);
    		return 0;
    	} else {
    		this.balance -= value;
    		this.betValue = value;
    		this.prev_bet = value;
    		return value; // bet ok!
    	}
    }
    
    /* Get Balance*/
    float getBalance(){
    	return this.balance;
    }
    
    int getBetValue(){
    	return this.betValue;
    }


	void setBalance(float balance) {
		this.balance += balance;
	}
	
	@SuppressWarnings("unchecked")
	ArrayList<Card>[] createSplitHands(){
		splitHands = (ArrayList<Card>[]) new ArrayList[4];
		return splitHands;
	}
	
	void addSplitHand(ArrayList<Card> hand){
		
	}
	
	ArrayList<Card>[] getSplitHands(int handIndex){
		return splitHands;
	}
	
    
}
