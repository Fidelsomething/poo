package blackjack;

import java.util.ArrayList;

/**
 * This class implements the three strategies of the Blackjack game: basic strategy, High Low and Ace-five.
 * @author public
 *
 */
class Advisor {
	static int true_count;
	static int running_count;
	static int countAF;
	

	//Basic strategy hard, soft and pair tables (fig.1)
	
			final static int H = 1;
			final static int S = 2;
			final static int P = 3;
			final static int DH = 4;
			final static int DS = 5;
			final static int RH = 6;
			
			final static int[][] bsHardTable = {
				{H,H,H,H,H,H,H,H,H,H},
				{H,H,H,H,H,H,H,H,H,H},
				{H,H,H,H,H,H,H,H,H,H},
				{H,H,H,H,H,H,H,H,H,H},
				{H,H,DH,DH,DH,DH,H,H,H,H},
				{H, DH,DH,DH,DH,DH,DH,DH,DH,H},
				{H,DH,DH,DH,DH,DH,DH,DH,DH,DH},
				{H,H,H,S,S,S,H,H,H,H},
				{H,S,S,S,S,S,H,H,H,H},
				{H,S,S,S,S,S,H,H,H,H},
				{H,S,S,S,S,S,H,H,H,RH},
				{RH,S,S,S,S,S,H,H,RH,RH},
				{S,S,S,S,S,S,S,S,S,S},
				{S,S,S,S,S,S,S,S,S,S},
				{S,S,S,S,S,S,S,S,S,S},
				{S,S,S,S,S,S,S,S,S,S},
				{S,S,S,S,S,S,S,S,S,S}
			};
			
			final static int[][] bsSoftTable = {
				{H,H,H,H,DH,DH,H,H,H,H},
				{H,H,H,H,DH,DH,H,H,H,H},
				{H,H,H,DH,DH,DH,H,H,H,H},
				{H,H,H,DH,DH,DH,H,H,H,H},
				{H,H,DH,DH,DH,DH,H,H,H,H},
				{H,S,DH,DH,DH,DH,S,S,H,H},
				{S,S,S,S,S,S,S,S,S,S},
				{S,S,S,S,S,S,S,S,S,S},
				{S,S,S,S,S,S,S,S,S,S},
			};
			
			final static int[][] bsPairTable  = {
				{H,H,H,P,P,P,P,H,H,H},
				{H,H,H,P,P,P,P,H,H,H},
				{H,H,H,H,H,H,H,H,H,H},
				{H,DH,DH,DH,DH,DH,DH,DH,DH,H},
				{H,H,P,P,P,P,H,H,H,H},
				{H,P,P,P,P,P,P,H,H,H},
				{P,P,P,P,P,P,P,P,P,P},
				{S,P,P,P,P,P,S,P,P,S},
				{S,S,S,S,S,S,S,S,S,S},
				{P,P,P,P,P,P,P,P,P,P},
			};

	/**
	 * Gets a string with the action that should be performed by the player, based on the different strategies
	 * @param player
	 * @param dealer
	 */
	static String getAdvice(Player player, Dealer dealer, Table table){
		// print three types of advice
		if(getBasicStrategy(player, dealer)!=null){
			System.out.println("basic			" + getBasicStrategy(player, dealer));
			return getBasicStrategy(player, dealer);
		}
			
		
		if(getHiLoStrategy(player, dealer)!=null){
			System.out.println("hi 					" + getHiLoStrategy(player, dealer));
			return getHiLoStrategy(player, dealer);
		}
		if(getAceFiveStrategy(player, dealer, table)!=0){
			System.out.println("ace-five					bet: " + getAceFiveStrategy(player, dealer, table));
			return convertToString(getAceFiveStrategy(player, dealer, table));
		}
		return null;
	}

	
	/**
	 * Determines if the hand of the player is a hard, soft or a pairs hand. Depending on the type of hand,
	 * a different table is used, on which are the actions to be performed, according to the hand value of the
	 * player and the up card of the dealer
	 * @param player
	 * @param dealer
	 * @return string with the action (stand, hit, etc)
	 */
	static String getBasicStrategy(Player player, Dealer dealer) {
		
		if(isPair(player.hand)){
			if(handHasAce(player.hand)){
				return convertToString(bsPairTable[9][dealer.hand.get(0).getValue()]);
			}else{
			//	System.out.print("Player has no aces \n");
			//accesses the position of the table according to the player's hand value and the dealer's
			//up card, and returns the corresponding string instruction
			return convertToString(bsPairTable[player.hand.get(0).getRank()-2][dealer.hand.get(0).getValue()-1]);
			}
			
		}
		if(isHardHand(player)){
			
			//accesses the position of the table according to the player's hand value and the dealer's
			//up card, and returns the corresponding string instruction
			return convertToString(bsHardTable[player.getHandValue()-5][dealer.hand.get(0).getValue()-1]);
			
		}
		if(isSoftHand(player)){
			//accesses the position of the table according to the player's hand value and the dealer's
			//up card, and returns the corresponding string instruction
			return convertToString(bsSoftTable[player.getHandValue()-13][dealer.hand.get(0).getValue()-1]);
			
		}
		return null;
	}
	
	
			
			
			/**
			 * Check if a hand has an ace.
			 * @param hand
			 * @return true if hand has an ace, false otherwise
			 */
			static boolean handHasAce(ArrayList<Card> hand){
				Card ace = new Card(Card.ACE, Card.CLUBS);
				if(hand.contains(ace)){
					return true;
				}else{
					return false;
				}
			}
			/**
			 * Checks if hand is Hard Hand. A hand is hard if it doesn't have aces, or if it does, they all
			 * value 1.
			 * @param player
			 * @return true if hand is hard, false otherwise
			 */
			static boolean isHardHand(Person player){
				if(!handHasAce(player.hand) || player.checkAceEleven()== false){
						 return true;
					 }else{
						 return false;
					 }
			}
			/**
			 * Checks if hand is Soft Hand. A hand is soft if it has aces and at least one ace values 11.
			 * @param player
			 * @return true if hand is soft, false otherwise
			 */
			static boolean isSoftHand(Person player){
				if(handHasAce(player.hand) && player.checkAceEleven()==true){
					return true;
				}else{
					return false;
				}
			}
			
			/**
			 * Checks if hand has a Pair. A hand is a pair if has only 2 cards and both are of equal rank.
			 * @param hand
			 * @return true if hand is a pair, false otherwise
			 */
			static boolean isPair(ArrayList<Card> hand){
				if(hand.get(0).equals(hand.get(1)) && hand.size() == 2){
					System.out.print("Pair table \n");
					return true;
				}else{
					return false;
				}
			}
			
			/**
			 * Converts the constants used in the tables to the possible actions that can be performed
			 * by the player.
			 * @param instruction code
			 * @return string with instruction
			 */
			static String convertToString(int instruction){
				String s;
		        switch (instruction) {
		            case H:  s = "hit";
		                     break;
		            case S:  s = "Stand";
		                     break;
		            case P:  s = "Split";
		                     break;
		            case DH:  s = "Double if possible, otherwise Hit";
		                     break;
		            case DS:  s = "Double if possible, otherwise Stand";
		                     break;
		            case RH:  s = "Surrender if possible, otherwise Hit";
		                     break;
		            default: s = "?";
		                     break;
		        }
		        return  s;
			}

			/**
			 * Returns a string with the action that should be performed by the player, according to
			 * his current hand and the dealer's up card.
			 * @param player
			 * @param dealer
			 * @return string with action (hit, stand, etc)
			 */
			static String getHiLoStrategy(Player player, Dealer dealer){
				int players_hand = player.getHandValue();
				int dealers_hand = dealer.hand.get(0).getValue();
				
				if(players_hand == 9){
					if(dealers_hand == 2){
						if(Advisor.true_count >= 1) return "Double";
						else return "Hit";
					}
					if(dealers_hand == 7){
						if(Advisor.true_count >= 3) return "Double";
						else return "Hit";
					}
				}
				if(players_hand == 10){
					if(dealers_hand == 10){
						if(Advisor.true_count >= 4) return "Double";
						else return "Hit";
					}
					if(dealers_hand == 1){
						if(Advisor.true_count >= 4) return "Double";
						else return "Hit";
					}
				}
				if(players_hand == 11){
					if(dealers_hand == 1){
						if(Advisor.true_count >= 1) return "Double";
						else return "Hit";
					}
				}
				
				if(players_hand == 12){
					if(dealers_hand == 3){
						if(Advisor.true_count >= 2) return "Stand";
						else return "Hit";
					}
					if(dealers_hand == 2){
						if(Advisor.true_count >= 3) return "Stand";
						else return "Hit";
					}
					if(dealers_hand == 4){
						if(Advisor.true_count >= 0) return "Stand";
						else return "Hit";
					}
					if(dealers_hand == 5){
						if(Advisor.true_count >= -1) return "Stand";
						else return "Hit";
					}
				}
				
				if(players_hand == 13){
					if(dealers_hand == 2){
						if(Advisor.true_count >= -1) return "Stand";
						else return "Hit";
					}
					if(dealers_hand == 3){
						if(Advisor.true_count >= -2) return "Stand";
						else return "Hit";
					}
				}
				
				if(players_hand == 14){
					if(dealers_hand == 10){
						if(Advisor.true_count >= 3) return "Surrender";
						else return "use Basic Strategy";
					}
				}
				if(players_hand == 15){
					if(dealers_hand == 10){
						if(0 <= Advisor.true_count && Advisor.true_count <= 3) return "Surrender";
						if(Advisor.true_count >= 4) return "Stand";
						else return "Hit";
					}
					if(dealers_hand == 9){
						if(Advisor.true_count >= 2) return "Surrender";
						else return "use Basic Strategy";
					}
					if(dealers_hand == 1){
						if(Advisor.true_count >= 1) return "Surrender";
						else return "use Basic Strategy";
					}
				}
				
				if(players_hand == 16){
					if(dealers_hand == 9){
						if(Advisor.true_count >= 5) return "Stand";
						else return "Hit";
					}
					if(dealers_hand == 10){
						if(Advisor.true_count >= 0) return "Stand";
						else return "Hit";
					}
				}
				
				if(players_hand == 20){
					if(dealers_hand == 5){
						if(Advisor.true_count >= 5) return "Split";
						else return "Stand";
					}
					if(dealers_hand == 6){
						if(Advisor.true_count >= 4) return "Split";
						else return "Stand";
					}
				}
				if(dealers_hand == 1){
					return "Insurance";
				}
				return "use Basic Strategy";				
			}

			/**
			 * This method updates the running count and true count.
			 * @param card that is drawn from the shoe
			 * @param nr_decks is the total number of decks in the shoe
			 */
			
			static void updateHiLo(Card card, int nr_decks){
				int totalcards= 52 * nr_decks;
				running_count = running_count + HiLoValue(card);
				if(totalcards-Shoe.cardsplayed != 0){
					true_count = running_count/(totalcards-Shoe.cardsplayed);
				}

			}
			/**
			 * Gets HiLo value for a given card
			 * @param card
			 * @return HiLo value
			 */
			static int HiLoValue(Card card){
				switch (card.rank) {
			        case 2:
			            return 1;
			        case 3:
			            return 1;
			        case 4:
			            return 1;
			        case 5:
			            return 1;
			        case 6:
			            return 1;
			        case 7:
			            return 0;
			        case 8:
			            return 0;
			        case 9:
			            return 0;
			        case 10:
			            return -1;
			        case 11:
			            return -1;
			        case 12:
			            return -1;
			        case 13:
			            return -1;
			        case 1:
			            return -1;
			        default:
			        	return 0;
			        }
			}
			

			
			//when shuffle, reset strategies
			static void resetHilo(){
				Shoe.cardsplayed=0;
				true_count=0;
				running_count=0;
			}
			
			static int getAceFiveStrategy(Player player, Dealer dealer, Table table){
				System.out.println(countAF);
				if (countAF >=2){
					int new_bet = player.prev_bet *2;
					if (new_bet<table.maxbet) return new_bet;
				}
				if(countAF <= 1) return table.minbet;			
				
				return 0;
			}
			
			/***
			 * 
			 * updates the value for the Ace Five card counting 
			 * @param card
			 */
			static void checkCardAF(Card card){
				switch(card.getValue()){
					case 5:
						System.out.println(card.getValue());
						countAF +=1;
					case 1:
						countAF -=1;
						
				}
			}
			
			/**
			 * 
			 *  Resets card counting for Interactive and Simulation modes
			 * 
			 */
			static void resetCardCounting(){
				Shoe.cardsplayed = 0; 
			}
			
}
