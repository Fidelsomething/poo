package blackjack;

public class Table {
	int maxbet, minbet;
	Shoe shoe;
	
	/* We'll Create the Table, with its Maxbet, Minbet, Nr of Decks and its Rules (??)  */
	public Table (int maxbet, int minbet, int nrdecks){
		this.maxbet = maxbet;
		this.minbet = minbet;
		this.shoe = new Shoe(nrdecks);
	}

}
