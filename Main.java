package blackjack;

import java.io.IOException;
import java.util.Arrays;

/**
 * Main class
 * @author public
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		int maxbet=50, minbet=5, nrdecks=1, balance = 100, shuffle=10, nrOfShuffles=1;
		String[] strategy = new String[2];
		GameMode gameMode = null;
		Table table = null;
		boolean simulation = false;
		int mode = 0;
		
		if(args.length < 1 || args[0] == null){
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
			table = new Table(maxbet, minbet, nrdecks, shuffle);
			break;
		case "-d":
			mode = GameMode.DEBUG_MODE;
			System.out.println("Debug mode");
			if(args.length < 6){
				System.out.println("ERROR: wrong number of arguments for mode -d");
				System.exit(-2);
			}else{
				minbet = Integer.parseInt(args[1]);
				maxbet = Integer.parseInt(args[2]);
				balance = Integer.parseInt(args[3]);
				gameMode = new DebugMode();
			}
			table = new Table(maxbet, minbet);
			break;
		case "-s":
			mode = GameMode.SIMULATION_MODE;
			System.out.println("Simulation mode");
			if(args.length < 8){
				System.out.println("Argument size: " + args.length);
				System.out.println("ERROR: wrong number of arguments for mode -s");
				System.exit(-2);
			}else{
				minbet = Integer.parseInt(args[1]);
				maxbet = Integer.parseInt(args[2]);
				balance = Integer.parseInt(args[3]);
				nrdecks = Integer.parseInt(args[4]);
				shuffle = Integer.parseInt(args[5]);
				nrOfShuffles = Integer.parseInt(args[6]);	//TODO nrofshuffles
				if(args.length == 8) {
					strategy[0] = args[7]; // BS or HL
				}else if(args.length == 9){	
					strategy[0] = args[7];
					strategy[1] = args[8];
				}
				System.out.println("Strategy: " + 	Arrays.toString(strategy));
				table = new Table (maxbet, minbet, nrdecks, shuffle);
				simulation = true;
				
			}
			break;
		default:
			System.out.println("Invalid mode! Modes are -i,-d or -s");
			System.exit(-1);
		}
		
		Statistics.initialBalance = balance;
		
		Player player = new Player(balance);
		Dealer dealer = new Dealer();
		
		if(simulation) gameMode = new SimulationMode(player, dealer, table, mode, strategy);
		
		BlackjackGame game = new BlackjackGame(gameMode, player, dealer, table, strategy);
		game.playGame();
	
	}

}
