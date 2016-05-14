package blackjack;

import java.io.IOException;

interface GameMode {
	static final int INTERACTIVE_MODE = 1;
	static final int DEBUG_MODE = 2;
	static final int SIMULATION_MODE = 3;
	
	
	//Receives a command like HIT STAND... from keyborad, file...
	//Return char
	String GetCommand() throws IOException;
	

}
