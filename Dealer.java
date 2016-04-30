package blackjack;

class Dealer extends Person{

	public Dealer() {
		super();
	}
	
	void play(Shoe s){
		if(this.getHandValue() < 17){
			this.hit(s);
		} else {
			this.stand();
		}
	}

}
