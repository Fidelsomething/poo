package blackjack;

import java.io.IOException;

interface GameMode {
	
	//Receives a command like HIT STAND... from keyborad, file...
	//Return char
	String GetCommand() throws IOException;
	

}
