package blackjack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class that implements the GameMode interface for the debug mode of the game.
 * @author public
 *
 */
class DebugMode implements GameMode {
	FileReaderClass frc;
	String line;
	ArrayList<String> commands = new ArrayList<String>();
	Scanner sc;
	/**
	 * DebugMode constructor. Initializes the FileReaderClass with the name of the necessary files.
	 * @throws FileNotFoundException
	 * 
	 * @see FileReaderClass
	 */
	DebugMode() throws FileNotFoundException{
		frc = new FileReaderClass("cmd-file.txt", "shoe-file.txt");
		line = frc.ReadCommands();
		sc = new Scanner(line);
	}
	/**
	 * Override the GetCommand method. This method parses the line read from the command file, returning the 
	 * corresponding string. In the case of a b command (bet), it checks if there's an integer with the bet value.
	 * When it reads the end line character, it returns the quit command.
	 */
	@Override
	public String GetCommand() throws FileNotFoundException {
		String command;
		if(line != null){
			if(sc.hasNext()){
				command = sc.next();
				if(command.equals("b") && sc.hasNextInt()){
					return command+" "+sc.nextInt();
				}else return command;
			} else{
				return "q";
			}
		}
		return null;
	}

}
