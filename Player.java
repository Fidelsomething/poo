package blackjack;

class Player extends Person{

	public Player(int balance) {
		super(balance);
	}
	
	void push(){
		
	}
	
	void hasBlackjack(Dealer dealer){
		if(this.getHandValue() == 21){ // player has blackjack
			if(dealer.hasBlackjack()==true){ // both have blackjack
				this.push();
				// tie
			}else {
				// player wins	
			}
		} else if(dealer.hasBlackjack()==true){ 
			//player loses because it does not have blackjack and dealer has
		}
		// it is not a blackjack for anyone... Keep Playing
	}
}
