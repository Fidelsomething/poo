package blackjack;

import java.io.IOException;
import java.util.Scanner;

public class BlackjackGame {
	//TODO: check visibility
	Dealer dealer;
	Player player;
	Table table;
	GameMode mode;
	boolean first_turn, bet_made, blackjack;

	public BlackjackGame(GameMode mode, Player player, Dealer dealer, Table table) {
		first_turn = true;
		bet_made=false;
		this.player = player;
		this.dealer = dealer;
		this.table = table;
		this.mode = mode;
	}
	
	void playGame() throws IOException{
		Scanner s = null;
		while(true){
			
		    s = new Scanner(mode.GetCommand());
		    String command = s.next();
		    
		    if(command == "$"){
				this.currentBalance();
				continue;
			}	
			if(command == "st"){
				this.statistics();
				continue;
			}
			if(command == "ad"){
				if(!first_turn){
					//advice;
				} else {
					System.out.println("invalid command: the cards were not dealt");	
				}
				continue;
			}
			/* First Turn - player should bet. Check if there is a Blackjack after deal */
			if(first_turn){			
				if(command == "b"){
					this.b(s);
					bet_made = true;
				}		
				if(command == "d"){
					if(bet_made){
						this.d();
						
						if (player.hasBlackjack()){ // Check BlackJack
							blackjack = true;
							this.s();
							// player.hasBlackjack(dealer);
							continue;
						}
					}else{
						System.out.println("you have to make a bet");
						continue;
					}
				}else{
					System.out.println("invalid command!");
					continue;
				}
				first_turn = false;
			} // end first turn
			else{
				if(command == "i" || command == "2" || command == "u" || command == "p"){
					switch(command){
						case "i":
						case "2":
						case "u":
						case "p":
					}
				}else{
					if(command == "h"){
						player.hit(table.shoe);
						continue;
					}
					if(command == "s"){
						player.stand();
					}
				}
			
		}
		//s.close();
	}

	

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
	void s(){
		System.out.println("player stands");
		System.out.println("dealers hand: " + dealer.hand + "(" + dealer.getHandValue()+")"+ "\n");
		
		if(!blackjack){
			if(dealer.play(table.shoe) == -1){
				//adjust balance
				System.out.println("player wins and his balance is " + player.getBalance());
				player.win(table);
			}else{
				this.findWinner();
			}
		} else { // Player has blackjack
			System.out.println("Blackjack!!");
			dealer.play(table.shoe);
			if(dealer.hasBlackjack()==true){ // both have blackjack
				player.push();
			}else {
				player.win(table);
			}
		}
		this.end_play();
	}
	
	/* Print Player's current balance */
	void currentBalance(){
		System.out.println("player current balance is " + player.getBalance());
	}
	
	void statistics() {
		// TODO
	}
	
	
	/* End of a play - there was a winner or a tie */
	void end_play(){
		player.clearHand();
		dealer.clearHand();
		bet_made = false;
		blackjack = false;
		first_turn = true;
		//player.prev_bet=0; it only goes to 0 with new game?
	}
	
	void findWinner(){
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
