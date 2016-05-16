package blackjack;

/**
 * Class that contains the variables needed for the game statistics such as number of wins, losses, blackjacks, etc.
 * @author public
 *
 */
class Statistics {
	static int nrBjPlayer = 0;
	static int nrBjDealer  = 0;
	static int nrWins = 0;
	static int nrLosses = 0;
	static int nrPushes = 0;
	static float gainPercentage = 0;
	static int initialBalance = 0;
	
	/**
	 * Calculates the gain of the player, relative to the initial balance.
	 * @param balance is the current balance of player
	 * @return gain, in percentage
	 */
	static float getGainPercentage(float balance){
		return ( (balance/initialBalance) * 100 ) - initialBalance;
	}
}
