package blackjack;

class Dealer extends Person{

	public Dealer() {
		
	}
	
	void play(Shoe s){
		System.out.println("dealers hand: " + this.hand + "(" + this.getHandValue()+")");
		while(this.getHandValue() < 17){
			System.out.println("dealer hits");
			this.hit(s);
			System.out.println("dealers hand: " + this.hand + "(" + this.getHandValue()+")");
		}
		this.stand();
		System.out.println("dealer stands");
		System.out.println("dealers hand: " + this.hand + "(" + this.getHandValue()+")");
	}

}
