package blackjack;

import java.io.IOException;

/**
 * Interface that gets a command according to the game mode.
 * @author public
 *
 */
interface GameMode {
	static final int INTERACTIVE_MODE = 1;
	static final int DEBUG_MODE = 2;
	static final int SIMULATION_MODE = 3;
	
	
	/**
	 * Returns a string with the action to be taken. In Interactive Mode it gets the action from the keyboard,
	 * in Debug Mode from a file and in Simulation Mode from the program itself.
	 * @return
	 * @throws IOException
	 */
	String GetCommand() throws IOException;
	

}
