package blackjack;

public class BlackjackGame {

	public BlackjackGame() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		Shoe s = new Shoe(1);
		Player quim = new Player();
		Dealer marta = new Dealer();
		
		marta.firstHit(s);
		quim.firstHit(s);
		
		System.out.println("# b " + quim.hand);
		System.out.println("# d " + marta.hand.get(0) + " x");
		System.out.println("# d " + marta.hand.get(1));
		System.out.println("Value from Quim's hand:" + quim.getHandValue());
		System.out.println("Value from Dealer's hand:" + marta.getHandValue());
		
	}

}
