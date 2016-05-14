package blackjack;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int maxbet=50, minbet=5, nrdecks=1, balance = 100;
		int shuffle = 10;
		GameMode gameMode = null;
		int mode = 0;
		
		if(args[0] == null){
			System.out.println("ERROR: must specify mode!");
			System.exit(-1);
		}
		
		switch(args[0]){
		case "-i":
			mode = GameMode.INTERACTIVE_MODE;
			System.out.println("Interactive mode");
			gameMode = new InteractiveMode();
			if(args.length < 6){
				System.out.println("ERROR: wrong number of arguments for mode -i");
				System.exit(-2);
			}else{
				minbet = Integer.parseInt(args[1]);
				maxbet = Integer.parseInt(args[2]);
				balance = Integer.parseInt(args[3]);
				nrdecks = Integer.parseInt(args[4]);
				shuffle = Integer.parseInt(args[5]);
				
			}
			break;
		case "-d":
			mode = GameMode.DEBUG_MODE;
			System.out.println("Debug mode");
			gameMode = new DebugMode();
			if(args.length < 6){
				System.out.println("ERROR: wrong number of arguments for mode -d");
				System.exit(-2);
			}else{
				minbet = Integer.parseInt(args[1]);
				maxbet = Integer.parseInt(args[2]);
				balance = Integer.parseInt(args[3]);
//				shoeFile = args[4];		TODO shoefile e cmdfile
//				cmdFile = args[5];
			}
			break;
		case "-s":
			mode = GameMode.SIMULATION_MODE;
			System.out.println("Simulation mode");
			gameMode = new SimulationMode();
			if(args.length < 6){
				System.out.println("ERROR: wrong number of arguments for mode -s");
				System.exit(-2);
			}else{
				minbet = Integer.parseInt(args[1]);
				maxbet = Integer.parseInt(args[2]);
				balance = Integer.parseInt(args[3]);
				nrdecks = Integer.parseInt(args[4]);
				shuffle = Integer.parseInt(args[5]);
//				nrOfShuffles = Integer.parseInt(args[6]);	TODO nrofshuffles e strategy
//				strategy = args[7];
			}
			break;
		default:
			System.out.println("Invalid mode! Modes are -i,-d or -s");
			System.exit(-1);
		}
		
		
			
		Player player = new Player(balance);
		Dealer dealer = new Dealer();
		Table table = new Table(maxbet, minbet, nrdecks, shuffle);
		
		BlackjackGame game = new BlackjackGame(gameMode, player, dealer, table);
		game.playGame();
		
		//TODO the commands can't have spaces!	
		// GameMode mode = new InteractiveMode();
	
	     //if (hasNext())
		
			

	}

}
