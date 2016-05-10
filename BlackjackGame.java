package blackjack;

import java.io.IOException;
import java.util.Scanner;

public class BlackjackGame {

	public BlackjackGame() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	/* This is where the game logic will be, i.e. the steps */
	public static void main(String[] args) throws IOException{
		
		int maxbet=50, minbet=5, nrdecks=1;
		
		Table table = new Table(maxbet, minbet, nrdecks);
		
		switch(args[0]){
		case "-i":
			System.out.println("Interactive mode");
			break;
		case "-d":
			System.out.println("Debug mode");
			break;
		case "-s":
			System.out.println("Simulation mode");
			break;
		default:
			System.out.println("Invalid mode! Modes are -i,-d or -s");
		}
			
				
		Player player = new Player(100);
		Dealer dealer = new Dealer();
		Shoe shoe = new Shoe(2);
		boolean first_turn = true;
		
		//TODO the commands can't have spaces!
		while(true){
			
			GameMode mode = new InteractiveMode();
			String input = mode.GetCommand();
		    Scanner s = new Scanner(input);
		     //if (hasNext())
		    
			switch(s.next()){
				//player.clearHand();
				//dealer.clearHand();
			case "b":
				b(player, table, s);
				break;
			case "$":
				System.out.println("\n# $");
				System.out.println("player current balance is " + player.getBalance() + "\n");
				break;
			case "d":
				d(player, dealer, shoe);
				break;
			case "h":
				h(player, shoe);
				break;
			case "s":
				s(player, dealer, shoe, table);
				//Who wins?
				break;
			case "i":
				System.out.println("insurance");
				break;
			case "u":
				System.out.println("surrender");
				break;
			case "p":
				System.out.println("splitting");
				break;
			case "2":
				System.out.println("double");
				break;
			case "ad":
				System.out.println("advice");
				break;
			case "st":
				System.out.println("statistics");
				break;
			default:
				System.out.println("Invalid command!\n");
			
				 s.close();	
				 
				 
		}
		
		}
		
		
	}

//	void play(){
//		if firstturn(){
//			
//		} else{
//			
//		}
//	}
	/* Bet in a turn */
	static void b(Player player, Table table, Scanner s){	
		System.out.println("\n# b");
		if(s.hasNextInt()){
			player.bet(s.nextInt(), table);
		}else{
			player.bet(table.minbet, table);
		}
		System.out.println("balance: " + player.getBalance() + "\n");
	}
	
	/* First turn - Deal two cards for each */
	static void d(Player player, Dealer dealer, Shoe shoe){
		System.out.println("\n# d");
		player.hit(shoe);
		dealer.hit(shoe);
		player.hit(shoe);
		dealer.hit(shoe);
		System.out.println("dealers hand: " +"["+ dealer.hand.get(0) + " X]" );
		System.out.println("players hand: " + player.hand + "(" + player.getHandValue()+")"+ "\n");
		//check blackjack
	}
	
	/* Player Hits */
	static void h(Player player, Shoe shoe){
		System.out.println("hit");
		System.out.println("player hits");
		player.hit(shoe);
		System.out.println("players hand: " + player.hand + "(" + player.getHandValue()+")"+ "\n");
		if(player.getHandValue() > 21) System.out.println("player loses and his balance is " + player.getBalance());
	}
	
	/* Player Stands */
	static void s(Player player, Dealer dealer, Shoe shoe, Table table){
		System.out.println("player stands");
		System.out.println("dealers hand: " + dealer.hand + "(" + dealer.getHandValue()+")"+ "\n");
		
		
		if(dealer.play(shoe) == -1){
			//adjust balance
			System.out.println("player wins and his balance is " + player.getBalance());
			player.win(table);
		}else{
			if(dealer.getHandValue() < player.getHandValue()){
				player.win(table);//player.betvalue
			}
			if(dealer.getHandValue() > player.getHandValue()){
				player.lose();
			}
			if(dealer.getHandValue() == player.getHandValue()){
				player.push();
			}
		}
	}
}
