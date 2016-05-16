package blackjack;

/**
 * Class that implements the dealer
 * @author public
 *
 */
class Dealer extends Person{

	/**
	 * Method that controls the dealer's decisions. Dealer plays according to the rules: must hit if
	 * his hand value is lower than 17, and stand otherwise.
	 * @param s
	 */
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
