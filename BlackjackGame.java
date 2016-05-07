package blackjack;

public class BlackjackGame {

	public BlackjackGame() {
		// TODO Auto-generated constructor stub
	}
	
	/* This is where the game logic will be, i.e. the steps */
	public static void main(String[] args){
		
		int maxbet=100, minbet=10, nrdecks=1;
		Player quim = new Player(50);
		Dealer marta = new Dealer(50);
		Table table = new Table(maxbet, minbet, nrdecks);
		
		marta.firstHit(table.shoe);
		quim.firstHit(table.shoe);
		
		System.out.println("# b " + quim.hand);
		System.out.println("# d " + marta.hand.get(0) + " x");
		System.out.println("# d " + marta.hand.get(1));
		System.out.println("Value from Quim's hand:" + quim.getHandValue());
		System.out.println("Value from Dealer's hand:" + marta.getHandValue());
		
		marta.bet(110, table);
		int new_bal= marta.getBalance();
		System.out.print("Marta's Balance after betting 10€ :" + new_bal);
		
	}

}
