package blackjack;

class Dealer extends Person{

	public Dealer(int balance) {
		super(balance);
	}
	
	void play(Shoe s){
		if(this.getHandValue() < 17){
			this.hit(s);
		} else {
			this.stand();
		}
	}

}
