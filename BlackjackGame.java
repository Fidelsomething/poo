package blackjack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {
	//TODO: check visibility
	//this constants are used in the win method, to determine the value that the player wins
	final static int BLACKJACK_WIN = 1;
	final static int INSURANCE_WIN = 2;
	final static int DOUBLE_DOWN_WIN = 3;
	
	
	Dealer dealer;
	Player player;
	Table table;
	GameMode mode;
	private static boolean usedHit;
	static boolean isFirstTurn;
	static boolean turnEnded;
	static boolean betMade;
	static boolean sideRulesUsed;
	static boolean usedInsurance;

	public BlackjackGame(GameMode mode, Player player, Dealer dealer, Table table) {
		isFirstTurn = true;
		turnEnded = false;
		betMade=false;
		usedInsurance = false;
		usedHit = false;
		this.player = player;
		this.dealer = dealer;
		this.table = table;
		this.mode = mode;
	}

//-------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Method that implements blackjack rules and plays the game
	 * @throws IOException
	 */
	void playGame() throws IOException{
		Scanner s = null;
		isFirstTurn = true;
		turnEnded = false;
		betMade = false;
		sideRulesUsed = false;

		while(true){
			
		    s = new Scanner(mode.GetCommand());
		    String command = s.next();
		    
		    if(command.equals("$")){
				this.currentBalance();
				continue;
			}	
			if(command.equals("st")){
				this.statistics();
				continue;
			}
			if(command.equals("ad")){
				if(!isFirstTurn){
					//advice;
				} else {
					System.out.println(command + ": illegal command: the cards were not dealt yet");	
				}
				continue;
			}
			/* First Turn - player should bet. Check if there is a Blackjack after deal */
			if(isFirstTurn){
				if(!command.equals("d") && !command.equals("b")){
					System.out.println(command + ": illegal command for first turn");
					continue;
				}
				if(command.equals("b") && !betMade){
					this.b(s);
					betMade = true;
					continue;
				}
				if(command.equals("d")){
					if(betMade){
						this.d();
						isFirstTurn = false;
						if(player.hasBlackjack()){
							player.stand();	//if player has blackjack automatically stands
							turnEnded = true;
						}
					}else{
						System.out.println(command + ": illegal command: you must make a bet before the deal");
						continue;
					}
				}
			} // end first turn
			else{
				//if hit is used, player can no longer use the side rules
				if(!command.equals("i") && !command.equals("u") && !command.equals("p") && !command.equals("2") && !command.equals("h") && !command.equals("s")){
					System.out.println("illegal command after deal");
					continue;
				}else{
					if(command.equals("i") || command.equals("u") || command.equals("p") || command.equals("2")){
						if(sideRulesUsed){
							System.out.println("illegal command: you can only use side rules immediately after the deal");
							continue;
						}
						
						if(command.equals("i")){
							this.insurance();
							sideRulesUsed = true;
						}
							
						if(command.equals("u")){
							System.out.println("surrender");
							this.surrender();
							sideRulesUsed = true;
							turnEnded = true;
						}
						if(command.equals("p")){
							this.split();
						}
						if(command.equals("2")){
							this.doubleDown();
							turnEnded = true;
							sideRulesUsed = true;
						}
					}
				}
				
				if(command.equals("h")){
					this.h();
					if(player.getHandValue() > 21){
						turnEnded = true;
					}
					sideRulesUsed = true;
				}
				if(command.equals("s")){
					this.s();
					sideRulesUsed = true;
					turnEnded = true;
				}
			}
			
			if(turnEnded){
				System.out.println("player's turn is over");
				dealer.play(table.shoe);
				this.findWinner();
				this.endPlay();
			}
		}//while loop
		//s.close();
	}//method end
//--------------------------------------------------------------------------------------------------------------------------
	

	/* Bet in a turn */
	/* Player bets */
	void b(Scanner s){	
		System.out.println("\n# b");
		if(s.hasNextInt()){
			player.bet(s.nextInt(), table);
		}else if(player.prev_bet != 0){ // there is a previous bet
			player.bet(player.prev_bet, table);
		} else {
			player.bet(table.minbet, table);
		}
		System.out.println("balance: " + player.getBalance() + "\n");
	}

	
	/* First turn - Deal two cards for each */
	void d(){
		System.out.println("\n# d");
		player.hit(table.shoe);
		dealer.hit(table.shoe);
		player.hit(table.shoe);
		dealer.hit(table.shoe);
		System.out.println("dealers hand: " +"["+ dealer.hand.get(0) + " X]" );
		System.out.println("players hand: " + player.hand + "(" + player.getHandValue()+")"+ "\n");
		//check blackjack
	}
	
	/* Player Hits */
	void h(){
		System.out.println("hit");
		System.out.println("player hits");
		player.hit(table.shoe);
		System.out.println("players hand: " + player.hand + "(" + player.getHandValue()+")"+ "\n");
	}
	 
	/* Player Stands */
	void s(){
		System.out.println("player stands");
		System.out.println("dealers hand: " + dealer.hand + "(" + dealer.getHandValue()+")"+ "\n");
		
//		if(!blackjack){
//			if(dealer.play(table.shoe) == -1){
//				//adjust balance
//				System.out.println("player wins and his balance is " + player.getBalance());
//				player.win(table);
//			}else{
//				this.findWinner();
//			}
//		} else { // Player has blackjack
//			System.out.println("Blackjack!!");
//			dealer.play(table.shoe);
//			if(dealer.hasBlackjack()==true){ // both have blackjack
//				player.push();
//			}else {
//				player.win(table);
//			}
//		}
//		this.end_play();
	}
	
	/**
	 * insurance
	 * This method makes a bet the same value of betValue on whether
	 * the dealer has a Blackjack. Puts a flag {@code insurance} to true, for checking
	 * if the dealer in fact has a Blackjack, in the end of the play.
	 */
	void insurance(){
		System.out.println("insurance");
		if(dealer.hand.get(1).isAce()){
			player.bet(player.getBetValue(), table);
			usedInsurance = true;
		}else{
			System.out.println("i: illegal command: you can only get insurance if the dealer has an ACE!");
		}
	}
	
	
	/**
	 * surrender
	 * This method takes half the bet the player made and ends the game immediately.
	 */
	void surrender(){
		System.out.print("player is surrendering)");
		player.setBalance(player.getBetValue()/2);
	}
	
	void split(){	//TODO acabar metodo split
		if(player.hasTwoEqualCards()){
			System.out.println("splitting");
		}else{
			System.out.println("p: illegal command: you must have two cards of the same rank to split a hand");
		}
		
//		ArrayList<Card> splitHand = new ArrayList<Card>();
//		splitHand.add( player.hand.get(2) );
	}
	/**
	 * doubleDown
	 * Places another bet equal to the original (doubling the original one) and checks the hand value.
	 * If hand value according to the rules, hits one time and sets {@code turnEnded} to true, ending the turn.
	 * Otherwise, prints an error message.
	 * 
	 * @see BlackjackGame.h()
	 */
	void doubleDown(){
		System.out.println("doubling down");
		if(player.getHandValue() == 9 || player.getHandValue() == 10 || player.getHandValue() == 11){
			player.bet(player.getBetValue(), table);
			this.h();
			turnEnded = true;
		}else{
			System.out.println("2: illegal command: you must have a hand worth 9,10 or 11");
		}
		
	}
	
	/* Print Player's current balance */
	void currentBalance(){
		System.out.println("player current balance is " + player.getBalance());
	}
	
	void statistics() {
		// TODO
	}
	
	
	/* End of a play - there was a winner or a tie */
	void endPlay(){
		player.clearHand();
		dealer.clearHand();
		betMade = false;
		isFirstTurn = true;
		turnEnded = false;
		usedHit = false;

		//player.prev_bet=0; it only goes to 0 with new game?
	}
	
	void findWinner(){
		if(player.hasBlackjack()){
			System.out.println("blackjack!!");
			if(dealer.hasBlackjack()){
				player.push();
			}else{
				player.win(table, BLACKJACK_WIN);	//TODO testar win
			}
			return;
		}
		if(dealer.hasBlackjack()){
			System.out.println("dealer has blackjack...");
			player.lose();
			if(usedInsurance){
				player.win(table, INSURANCE_WIN);	//TODO testar win
			}
		}
		if(player.getHandValue()>21){
			System.out.println("player busts");
			player.lose();
			return;
		}
		if(dealer.getHandValue()>21){
			System.out.println("dealer busts");
			player.win(table);
			return;
		}
		if(dealer.getHandValue() < player.getHandValue()){
			player.win(table);//player.betvalue
			return;
		}
		if(dealer.getHandValue() > player.getHandValue()){
			player.lose();
			return;
		}
		if(dealer.getHandValue() == player.getHandValue()){
			player.push();
			return;
		}
	}
	
}
