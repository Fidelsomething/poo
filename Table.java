package blackjack;

import java.io.IOException;

/**
 * In table are stored the bet values, the win percentages, the shoe and the value at which the later is 
 * shuffled, in percentage.
 * @author public
 *
 */
public class Table {
	final int maxbet;
	final int minbet;
	final int win;
	final int insurance, shuffle;
	final double winblackjack;
	Shoe shoe;
	
	/**
	 * Table constructor for interactive mode
	 * @param maxbet maximum bet value
	 * @param minbet minimum bet value
	 * @param nrdecks number of deck that compose the shoe
	 * @param shuffle percentage of the total shoe played at which it is shuffled
	 */
	/* We'll Create the Table, with its Maxbet, Minbet, Nr of Decks and its Rules (??)  */
	public Table (int maxbet, int minbet, int nrdecks, int shuffle){
		this.maxbet = maxbet;
		this.minbet = minbet;
		this.win = 2;
		this.shoe = new Shoe(nrdecks);
		//this.shoe = new Shoe();
		this.winblackjack = 2.5;
		this.insurance = 2;
		this.shuffle = shuffle;
	}
	
	/**
	 * Table constructor for debug mode. Shoe is created according to the shoe file read and it is shuffled
	 * if it is all played, although this shouldn't happen if the command file and shoe file are well made.
	 * @param maxbet maximum bet value
	 * @param minbet minimum bet value
	 * @throws IOException if the shoe file is non existing this exception is thrown
	 */
	public Table (int maxbet, int minbet) throws IOException{
		this.maxbet = maxbet;
		this.minbet = minbet;
		this.win = 2;
		this.shoe = new Shoe();
		this.winblackjack = 2.5;
		this.insurance = 2;
		this.shuffle = 100;
	}

}
