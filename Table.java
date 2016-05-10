package blackjack;

public class Table {
	int maxbet, minbet, win;
	double winblackjack;
	Shoe shoe;
	
	/* We'll Create the Table, with its Maxbet, Minbet, Nr of Decks and its Rules (??)  */
	public Table (int maxbet, int minbet, int nrdecks){
		this.maxbet = maxbet;
		this.minbet = minbet;
		this.shoe = new Shoe(nrdecks);
		this.winblackjack = 2.5;
		this.win = 2;
	}

}
