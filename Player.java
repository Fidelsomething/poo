package blackjack;

class Player extends Person{
	/*Fields*/
	private int betValue;
	private int balance; // Private because no one else should be allowed to 
	  // access the player's money
	int prev_bet = 0;
	
	
	/*Constructors*/
	public Player(int balance) {
		this.balance = balance;
	}
	
	
	/*Methods*/
	void win(Table table){
		this.balance += table.win * this.betValue;
		System.out.println("player wins and his current balance is " + this.balance);
	}
	
	void blackjackWin(Table table){
		this.balance += table.winblackjack * this.betValue;
	}
	
	void lose(){
		System.out.println("player loses and his current balance is " + this.getBalance());
	}
	void push(){
		this.balance += this.betValue;
		System.out.println("player pushes and his current balance is " + this.getBalance());
		System.out.println("PUSH");
	}
	
	//TESTAR
	void hasBlackjack(Dealer dealer){
		if(this.getHandValue() == 21){ // player has blackjack
			if(dealer.hasBlackjack()==true){ // both have blackjack
//				this.push();
				// tie
			}else {
				// player wins	
			}
		} else if(dealer.hasBlackjack()==true){ 
			//player loses because it does not have blackjack and dealer has
		}
		// it is not a blackjack for anyone... Keep Playing
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
    int getBalance(){
    	return this.balance;
    }
    
}
