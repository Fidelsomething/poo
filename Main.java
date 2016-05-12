package blackjack;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int maxbet=50, minbet=5, nrdecks=1, balance = 100;
		GameMode mode = null;
		
		if(args.length != 1){
			System.out.println("ERROR: wrong number of arguments!");
			System.exit(-1);
		}
		
		switch(args[0]){
		case "-i":
			System.out.println("Interactive mode");
			mode = new InteractiveMode();
			break;
		case "-d":
			System.out.println("Debug mode");
			mode = new DebugMode();
			break;
		case "-s":
			System.out.println("Simulation mode");
			mode = new SimulationMode();
			break;
		default:
			System.out.println("Invalid mode! Modes are -i,-d or -s");
			System.exit(-1);
		}
			
		Player player = new Player(balance);
		Dealer dealer = new Dealer();
		Table table = new Table(maxbet, minbet, nrdecks);
		
		BlackjackGame game = new BlackjackGame(mode, player, dealer, table);
		game.playGame();
		
		//TODO the commands can't have spaces!	
		// GameMode mode = new InteractiveMode();
	
	     //if (hasNext())
		
			

	}

}
