//package blackjack;
//
//class SimulationMode implements GameMode {
//	static final String BET = "b";
//	static final String DEAL = "d";
//	static final String HIT = "h";
//	static final String STAND = "s";
//	static final String DOUBLE = "2";
//	static final String  INSURANCE = "i";
//	static final String SPLIT = "p";
//	static final String SURRENDER = "u";
//	static final String STATISTICS = "st";
//	static final String QUIT = "q";
//	
//	Player player;
//	Dealer dealer;
//	Table table;
//	int shufflesPerformed;
//	int s_number;
//	int prevBet;
////	Advice strategy;
//	
//	/**
//	 * SimulationMode constructor
//	 * 
//	 * Receives player, dealer and table as parameters to be able to look at both the 
//	 * player's and dealer's hand
//	 * 
//	 * @param player
//	 * @param dealer
//	 * @param table
//	 */
////	SimulationMode(Player player, Dealer dealer, Table table, int s_number, Advice strategy){
////		this.player = player;
////		this.dealer = dealer;
////		this.table = table;
////		shufflesPerformed = 0;	
////		this.s_number = s_number; //number of shuffles to perform before game ends
////		prevBet = 0;
//////		this.strategy = strategy;
////	}
//	
//	
//	@Override
//	String GetCommand() {
//		boolean isFirstTurn = true;
//		boolean playerWon = false;
//		boolean playerPushed = false;
//		boolean betMade = false;
//		
//		int bet = table.minbet;
//		
//		String command = new String();
//		
//		while(shufflesPerformed < s_number){
//			if(player.getBalance() == 0){
//				command = QUIT;
//			}
//			
//			if(isFirstTurn){
//				if(!betMade){
//					betMade = true;
//					return BET;
//				}else{
//					isFirstTurn = false;
//					return DEAL;
//				}
//			}else{
//				getAdvice();
//			}
//		}
//		
//		return null;
//		
//	}
//
//}
//
//	int getBet(){
//		float balance;
//		int bet;
//		balance = player.getBalance();
//		
//		if(playerWon){
//			bet = prevBet += table.minbet;
//		}else{
//			if(playerPushed){
//				bet = prevBet;
//			}else{
//				//either the player lost or is the first bet
//				//if prevBet=0, it's the first bet made, so the bet is minbet
//				//if not, the player lost, and bet is prevBet-minbet
//				
//				bet = (prevBet == 0) ? table.minbet: (prevBet -= table.minbet);
//			}
//		}
//		
//		if( (balance - bet) >= 0){
//			//valid bet
//			return bet;
//		}else{
//			//player balance is not enough to make a bet. gameover
//			return 0;
//		}
//	}
//	
//}//class
