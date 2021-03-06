package blackjack;

/**
 * 
 * Class that implements the Simulation GameMode interface, that simulates an automatic player 
 * that plays accordingly to the Basic, HiLo and Ace-Five Strategies.
 * 
 */
class SimulationMode implements GameMode {
	static final String BET = "b";
	static final String DEAL = "d";
	static final String HIT = "h";
	static final String STAND = "s";
	static final String DOUBLE = "2";
	static final String INSURANCE = "i";
	static final String SPLIT = "p";
	static final String SURRENDER = "u";
	static final String STATISTICS = "st";
	static final String QUIT = "q";
	static boolean BS, HL, AF;
	
	Player player;
	Dealer dealer;
	Table table;
	int shufflesPerformed;
	int s_number;
	static int simulation_bet;
	String[] strategy;

	int i;
	
	/**
	 * SimulationMode constructor
	 * 
	 * Receives table, number of shuffles and strategies to use as parameters
	 * 
	 * @param table
	 * @param s_number
	 * @param strategy
	 * 
	 */
	SimulationMode(Player player, Dealer dealer, Table table, int s_number, String[] strategy){	
		
		
		i= 0;
		this.player = player;
		this.dealer = dealer;
		this.table = table;
		shufflesPerformed = 0;	
		this.s_number = s_number; //number of shuffles to perform before game ends
		this.strategy = strategy;
		simulation_bet= table.minbet;
		BS=false;
		HL=false;
		AF=false;
		if(strategy[1] == null){
			if(strategy[0].equals("BS")) BS=true;
			if(strategy[0].equals("HL")) HL=true;
			if(strategy[0].equals("AF")) AF=true;
		} else {		
			if(strategy[0].equals("BS") || strategy[1].equals("BS")) BS=true;
			if(strategy[0].equals("HL") || strategy[1].equals("HL")) HL=true;
			if(strategy[0].equals("AF") || strategy[1].equals("AF")) AF=true;
		}
	}
	

	/**
	 * 
	 * Gets plays from the strategies and returns the string with the command
	 * 
	 */
	@Override
	public String GetCommand() {
		String BSvalue, HLvalue;

		if(Shoe.shufflesPerformed < s_number){
		
			if(player.getBalance() == 0){
				return "q";//quit
			}
			
			if(BlackjackGame.isFirstTurn){
				if(!BlackjackGame.betMade){
					if(i==0) simulation_bet=30;
					return "b " + simulation_bet;
				}else{
					return "d";
				}
			}else{
							
				BSvalue = Advisor.getBasicStrategy(player, dealer);
				if(BS){
					return BScommand(BSvalue);
				}
				
				if(HL){
					HLvalue = Advisor.getHiLoStrategy(player, dealer);
					return HLcommand(HLvalue, BSvalue);	
				}			
			}
		}
			return "q";
	}
	
	/**
	 * Gets the command using Basic Strategy
	 * 
	 * @param BSvalue
	 * @return command
	 */
	String BScommand(String BSvalue){
		System.out.println("BS Advice: " + BSvalue);
		
		if(BSvalue.equals("hit")) return "h";
		if(BSvalue.equals("Stand")) return "s";
		if(BSvalue.equals("Push")) return "p";
		if(BSvalue.equals("Double if possible, otherwise Hit")){
			if(BlackjackGame.doubleDownPossible(player))
				return "2";
			else return "h";
		}
		if(BSvalue.equals("Double if possible, otherwise Stand")){
			if(BlackjackGame.doubleDownPossible(player))
				return "2";
			else return "s";
		}
		if(BSvalue.equals("Surrender if possible, otherwise Hit")) {
			if(BlackjackGame.surrenderPossible(dealer))
				return "u";
			else return "h";
		}
		return "q";
	}


	/**
	 * Gets the command using HiLo Strategy
	 * 
	 * @param HLvalue
	 * @param BSvalue
	 * @return command
	 */
	String HLcommand(String HLvalue, String BSvalue){
		System.out.println("HL Advice:" + HLvalue);
		if(HLvalue.equals("Hit")) return "h";
		if(HLvalue.equals("Double")){
			if(BlackjackGame.doubleDownPossible(player))
				return "2";
			else return "h";
		}
		if(HLvalue.equals("Stand")) return "s";
		if(HLvalue.equals("Insurance")){
			if(BlackjackGame.insurancePossible(dealer, player))
				return "i";
			else return "h";
		}
		if(HLvalue.equals("Surrender")){
			if(BlackjackGame.surrenderPossible(dealer))
				return "u";
			else return "h";
		}
		if(HLvalue.equals("Split")){	
			if(BlackjackGame.splitPossible(player)) return "p";
			else return "h";
		}
		if(HLvalue.equals("use Basic Strategy")){
			return BScommand(BSvalue);
		}
		BlackjackGame.statistics(player);
		return "q";
	}
	/***
	 * 
	 * Standard bet strategy for Simulation mode, when there is no Ace-Five strategy
	 * 
	 */
	static int standardBetStrategy(Player player, Table table){
		float balance;
		int bet, newbet;
		
		balance = player.getBalance();
		bet = player.getBetValue();
		
		if(balance - bet <= 0) {
			System.out.println("Not enough balance to make a bet");
			return 0;
		}
		
		if(player.result.equals("win")){
			newbet = bet + table.minbet;
			System.out.println("Player Won. Standard Bet value: " + newbet);
			if(newbet>table.maxbet) return bet;
			else return newbet;
		}
		
		if(player.result.equals("lose")){
			newbet = bet - table.minbet;
			System.out.println("Player Lost. Standard Bet value: " + newbet);
			if(newbet<table.minbet) return bet;
			else return newbet;
		}
		
		if(player.result.equals("lose")){
			System.out.println("Player Pushed. Standard Bet value: " + bet);
			return bet;
		}
		return bet;
	}
	
	/***
	 * 
	 * Updates the bet value for simulation game, depending if the Ace-Five strategy is being used or not
	 * 
	 */
	static void updateBetValue(Player player, Dealer dealer, Table table){
		if(AF==false) simulation_bet=standardBetStrategy(player, table);
		else simulation_bet=Advisor.getAceFiveStrategy(player, dealer, table);
	}
}//class
