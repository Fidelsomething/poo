package blackjack;

//import java.util.Scanner;
import java.io.*;  

/**
 * Class that implements the GameMode interface, that gets the player's actions from the keyboard.
 * @author public
 *
 */
class InteractiveMode  implements GameMode{
	
	/**
	 * Reads from the keyboard and returns the string read.
	 */
	@Override
	public String GetCommand() throws IOException {
		InputStreamReader r = new InputStreamReader(System.in);  
		BufferedReader br = new BufferedReader(r);  
		  
		System.out.println("Enter command:");
		String command = br.readLine();
		return command;  
	 }  
		 
	

}
