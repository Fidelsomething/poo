package blackjack;

class Dealer extends Person{

	public Dealer() {
		
	}
	
	int play(Shoe s){
		while(this.getHandValue() < 17){
			System.out.println("dealer hits");
			this.hit(s);
			System.out.println("dealers hand: " + this.hand + "(" + this.getHandValue()+")");
			if(this.getHandValue()>21){
				System.out.println("dealer busts");
				return -1;
			}
		} 
			this.stand();
			System.out.println("dealer stands");
			return 1;
		
	}

}
