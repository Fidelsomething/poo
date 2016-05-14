package blackjack;

public class Table {
	final int maxbet, minbet, win;
	final int insurance, shuffle;
	final double winblackjack;
	Shoe shoe;
	
	/* We'll Create the Table, with its Maxbet, Minbet, Nr of Decks and its Rules (??)  */
	public Table (int maxbet, int minbet, int nrdecks, int shuffle){
		this.maxbet = maxbet;
		this.minbet = minbet;
		this.win = 2;
		this.shoe = new Shoe(nrdecks);
		this.winblackjack = 2.5;
		this.insurance = 2;
		this.shuffle = shuffle;
	}

}
