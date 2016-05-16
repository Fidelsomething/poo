package blackjack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Implements blackjack rules and plays the game.
 * @author public
 *
 */
public class BlackjackGame {
	//this constants are used in the win method, to determine the value that the player wins
	final static int BLACKJACK_WIN = 1;
	final static int INSURANCE_WIN = 2;
	final static int DOUBLE_DOWN_WIN = 3;
	
	
	Dealer dealer;
	Player player;
	Table table;
	GameMode mode;
	static boolean usedHit;
	static boolean isFirstTurn;
	static boolean turnEnded;
	static boolean betMade;
	static boolean sideRulesUsed;
	static boolean usedInsurance;
	static boolean surrender;
	static boolean usedSplit;
	static int nrSplits;
	static int doneSplits;
	int[] hand_values;
	//Advisor advisor;
	
	/**
	 * Constructor
	 * @param mode
	 * @param player
	 * @param dealer
	 * @param table
	 */
	public BlackjackGame(GameMode mode, Player player, Dealer dealer, Table table) {
		isFirstTurn = true;
		turnEnded = false;
		betMade=false;
		usedInsurance = false;
		usedHit = false;
		surrender = false;
		nrSplits = 0;
		doneSplits=0;
		this.player = player;
		this.dealer = dealer;
		this.table = table;
		this.mode = mode;
		this.hand_values = new int[4];
		Advisor.resetHilo();
	//	System.out.println(Advisor.running_count);
		
	}

//-------------------------------------------------------------------------------------------------------------------------------
	/**
	 * playGame
	 * 
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
		    table.shoe.checkShuffle(table);
		   		   
		    if(command.equals("q")){
				System.out.println("bye");
				System.exit(0);
				continue;
			}
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
					advice();
					
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
				if(command.equals("b")){
					if(betMade){
						System.out.println(command + ": illegal command: you've already betted");
					}else{
						this.b(s);
						continue;
					}
					
				}
				if(command.equals("d")){
					if(betMade){
						this.d();
						isFirstTurn = false;
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
							if(nrSplits==0){
								this.insurance();
								sideRulesUsed = true;
							} else System.out.println("can't take insurance after split");
						}
							
						if(command.equals("u")){ // TODO no late surrender
							if(nrSplits==0){
								System.out.println("surrender");
								this.surrender();
								this.findWinner();
								this.endPlay();
								
							} else System.out.println("can't surrender after split");
					
						}
						if(command.equals("p")){
							this.split();
						}
						if(command.equals("2")){
							this.doubleDown();
						}
					}
				}
				
				if(command.equals("h")){
					this.h();
					if(player.getHandValue() > 21){	//player busts
						//System.out.println("dealers hand: " + this.dealer.hand + "(" + this.dealer.getHandValue()+")");
						if(nrSplits==0){ 
							this.findWinner();
							this.endPlay();
						} else{
							turnEnded=true;
						}
					}
				}
				if(command.equals("s")){
					this.s();
					turnEnded = true;
				}
			}
			
			if(turnEnded){
				System.out.println("player's turn is over");
				
				if(player.splitHandisEmpty()){ // there are no more split hands to handle
					dealer.play(table.shoe);
					if(nrSplits==0){		
						this.findWinner(); // game without splits
					}
					
					else{// game with splits - evaluate each hand
						int hand_value = player.getHandValue();
						hand_values[doneSplits] = hand_value;
						System.out.println("Split hand values: " + Arrays.toString(hand_values));
						this.findSplitWinner(hand_values);
					}
				}else{
					int hand_value = player.getHandValue();
					hand_values[doneSplits] = hand_value;
					player.changeHand();// update hand
					turnEnded=false;
					continue;
				}
				this.endPlay();
			}
		}//while loop
		//s.close();
	}//method end
//--------------------------------------------------------------------------------------------------------------------------
	

	/**
	 * b - BET
	 * 
	 * If there's a previous bet value, player bets that value. If there is no previous value,
	 * the minimum bet is the bet value. 
	 * 
	 * @param s Scanner that parses an integer that is the bet value
	 */
	void b(Scanner s){	
		System.out.println("\n # b");
		int b = 0;
		if(s.hasNextInt()){
			b = player.bet(s.nextInt(), table);
		}else if(player.prev_bet != 0){ // there is a previous bet
			b = player.bet(player.prev_bet, table);
		} else {
			b = player.bet(table.minbet, table);
		}
		if(b != 0){
			System.out.println("balance: " + player.getBalance() + "\n");
			betMade = true;
		}
	}

	
	/**
	 * d - DEAL
	 * 
	 * Deals two cards for the dealer and two cards for the player. Player gets a card first, then the dealer, then
	 * the player again and finally the dealer. Both player's and dealer's hands are printed, except for the dealer's
	 * hole card.
	 */
	void d(){	//TODO force hand
		System.out.println("\n# d");
		dealer.hit(table.shoe);
		Card.hiddencard=true;
		dealer.hit(table.shoe);
		Card.hiddencard=false;
		player.hit(table.shoe);	
		player.hit(table.shoe);
		
		//player.hand.add(new Card(Card.FIVE, Card.CLUBS));
		//System.out.print("Players hand1:" + +"\n");
		//player.hand.add(new Card(Card.FIVE, Card.CLUBS));
		//System.out.print("Players hand2:" + player.hand.get(player.hand.size()-1)+"\n");
		
		//dealer.hand.add(new Card(Card.ACE, Card.CLUBS));
		//dealer.hand.add(new Card(Card.THREE, Card.CLUBS));
		System.out.println("dealers hand: " +"["+ dealer.hand.get(0) + " X]" );
		System.out.println("players hand: " + player.hand + "(" + player.getHandValue()+")"+ "\n");
	}
	
	
	/**
	 * h - HIT
	 * 
	 * Player hits, taking a card from the shoe. The player's hand is printed.
	 */
	void h(){
		System.out.println("\n# h");
		System.out.println("player hits");
		player.hit(table.shoe);
		System.out.println("players hand: " + player.hand + "(" + player.getHandValue()+")"+ "\n");
	}
	 
	
	/**
	 * s - STAND
	 * 
	 * Player stands. Player's hand is printed.
	 */
	void s(){
		System.out.println("\n# s");
		System.out.println("player stands");
		System.out.println("dealers hand: " + dealer.hand + "(" + dealer.getHandValue()+")"+ "\n");
		
	}
	
	
	/**
	 * insurance
	 * 
	 * This method makes a bet the same value of betValue on whether
	 * the dealer has a Blackjack. Puts a flag {@code insurance} to true, for checking
	 * if the dealer in fact has a Blackjack, in the end of the play.
	 */
	void insurance(){
		System.out.println("\n# i");
		if(dealer.hand.get(0).isAce()){
			player.bet(player.getBetValue(), table);
			usedInsurance = true;
			sideRulesUsed = true;
			System.out.println("player is taking insurance");
		}else{
			System.out.println("i: illegal command: you can only get insurance if the dealer has an ACE!");
		}
	}
	
	
	/**
	 * surrender
	 * 
	 * This method takes half the bet the player made and returns it to his balance
	 * and ends the game immediately.
	 */
	void surrender(){
		System.out.println("\n# u");
		System.out.print("player is surrendering \n");
		player.setBalance((float)player.getBetValue()/2);
		surrender= true;
	}
	
	
	/**
	 * split
	 * 
	 * TODO
	 */
	void split(){	//TODO finish split method
		System.out.println("\n# p");
		if(player.hasTwoEqualCards()){
			if(nrSplits < 4){
				nrSplits += 1;
				System.out.println("splitting");				
				// create another hand
				ArrayList<Card> auxHand = new ArrayList<Card>();
				auxHand.add(player.hand.get(1));
				player.hand.remove(1); //keep using player.hand
				player.addSplitHand(auxHand); // add new hand to the array
				player.bet(player.getBetValue(), table);
			} else {
				System.out.println("p: illegal command: you can't split more than 3 times");
			}
		}else{
			System.out.println("p: illegal command: you must have two cards of the same rank to split a hand");
		}
	}
	
	
	/**
	 * doubleDown
	 * 
	 * Places another bet equal to the original (doubling the original one) and checks the hand value.
	 * If hand value according to the rules, hits one time and sets {@code turnEnded} to true, ending the turn.
	 * Otherwise, prints an error message.
	 * 
	 * @see BlackjackGame.h()
	 */
	void doubleDown(){
		System.out.println("\n# 2");
		if(player.getHandValue() == 9 || player.getHandValue() == 10 || player.getHandValue() == 11){
			player.betDoubleDown(player.getBetValue());
			System.out.println("balance "+player.getBalance() + ", bet " + player.getBetValue());
			this.h();
			turnEnded = true;
			sideRulesUsed = true;
		}else{
			System.out.println("2: illegal command: you must have a hand worth 9,10 or 11");
		}
		
	}
	
	
	/**
	 * currentBalance
	 * 
	 * Prints the player's balance.
	 */
	/* Print Player's current balance */
	void currentBalance(){
		System.out.println("player current balance is " + player.getBalance());
	}
	
	
	/**
	 * statistics
	 * 
	 * Prints the game statistics, showing the number of blackjacks of both player and dealer, the
	 * number of losses, wins and pushes, and finally the balance of the player and the gain he has made
	 * in percentage.
	 */
	
	void statistics() {
		System.out.println("BJ P/D    "+Statistics.nrBjPlayer+"/"+Statistics.nrBjDealer);
		System.out.println("Win       "+Statistics.nrWins);
		System.out.println("Lose      "+Statistics.nrLosses);
		System.out.println("Push      "+Statistics.nrPushes);
		System.out.println("Balance   "+player.getBalance()+"("+( Statistics.getGainPercentage(player.getBalance())+"%)") );
	}
	
	/**
	 * advice
	 * 
	 * Calls the method getAdvice from the class Advisor, which prints the recommend action for the player.
	 */
	void advice() {
		System.out.println("\n# ad");
		Advisor.getAdvice(player, dealer, table);
	}
	
	/* End of a play - there was a winner or a tie */
	/**
	 * endPlay
	 * 
	 * Called when game ends, it resets the game flags variables. If the player is out of money it exits the program.
	 */
	void endPlay(){
		if(player.getBalance() == 0){
			System.out.println("out of money. bye");
			System.exit(0);
		}
		player.clearHand();
		dealer.clearHand();
		betMade = false;
		isFirstTurn = true;
		turnEnded = false;
		usedHit = false;
		sideRulesUsed = false;
		surrender = false;
		nrSplits = 0;
		doneSplits=0;
		Arrays.fill(hand_values, 0);
	}
	
	/**
	 * Finds if the dealer or the player won, according to their hands.
	 */
	void findWinner(){
		System.out.println("");
		if(surrender==true){
			player.lose();
			Statistics.nrLosses++;
			return;
		}
		if(player.hasBlackjack()){
			System.out.println("blackjack!!");
			Statistics.nrBjPlayer++;	//can't increment on hasBlackjack because it's a method of person
			if(dealer.hasBlackjack()){
				Statistics.nrBjDealer++;
				player.push();
				Statistics.nrPushes++;
			}else{
				player.win(table, BLACKJACK_WIN);
				Statistics.nrWins++;
			}
			return;
		}
		if(dealer.hasBlackjack()){
			System.out.println("dealer has blackjack...");
			Statistics.nrBjDealer++;
			
			player.lose();
			Statistics.nrLosses++;
			if(usedInsurance){
				player.win(table, INSURANCE_WIN);
			}
		}
		if(player.getHandValue()>21){
			System.out.println("player busts");
			player.lose();
			Statistics.nrLosses++;
			return;
		}
		if(dealer.getHandValue()>21){
			System.out.println("dealer busts");
			player.win(table);
			Statistics.nrWins++;
			return;
		}
		if(dealer.getHandValue() < player.getHandValue()){
			player.win(table);//player.betvalue
			Statistics.nrWins++;
			return;
		}
		if(dealer.getHandValue() > player.getHandValue()){
			player.lose();
			Statistics.nrLosses++;
			return;
		}
		if(dealer.getHandValue() == player.getHandValue()){
			player.push();
			Statistics.nrPushes++;
			return;
		}
	}
	
	/**
	 * Compares the dealers hand agaisnt each of the player's split hands.
	 * @param hands
	 */
	void findSplitWinner(int[] hands){
		int dealers_hand = dealer.getHandValue();
		for(int i = 0; i < nrSplits; i++){
			if(hands[i]>22){ // Bust
				System.out.println("Bust!");
				player.lose();
				continue;
			}
			if (hands[i] > dealers_hand ){
				player.win(table);
			} else {
				if(hands[i]== dealers_hand){
					player.push();
				} else{
					player.lose();
				}
			}
	
		}
	}
	
	
}
