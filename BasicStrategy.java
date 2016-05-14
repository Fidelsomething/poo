package blackjack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class BasicStrategy extends Advice {
	
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
		
		
		//methods
		boolean handHasAce(ArrayList<Card> hand){
			Card ace = new Card(Card.ACE, Card.CLUBS);
			if(hand.contains(ace)){
				return true;
			}else{
				return false;
			}
		}
		
		boolean isHardHand(ArrayList<Card> hand){
			if(handHasAce(hand)){
				return false;
			}else{
				return true;
			}
		}
		
		boolean isSoftHand(ArrayList<Card> hand){
			if(handHasAce(hand)){
				return true;
			}else{
				return false;
			}
		}
		
		boolean isPair(ArrayList<Card> hand){
			if(hand.get(0).equals(hand.get(1)) && hand.size() == 2){
				return true;
			}else{
				return false;
			}
		}
		
	
		String convertToString(int instruction){
			String s;
	        switch (instruction) {
	            case H:  s = "HIT";
	                     break;
	            case S:  s = "Stand";
	                     break;
	            case P:  s = "Split";
	                     break;
	            case DH:  s = "Double if possible, otherwise Hit";
	                     break;
	            case DS:  s = "Double if possible, otherwise Stand";
	                     break;
	            case RH:  s = "Surrender is possible, otherwise Hit";
	                     break;
	           
	            default: s = "?";
	                     break;
	        }
	        return  "basic  "+s;
			
		}
		
		@Override
		String getAdvice(Person player, Person dealer) {
			if(isHardHand(player.hand)){
				
				//accesses the position of the table according to the player's hand value and the dealer's
				//up card, and returns the corresponding string instruction
				return convertToString(bsHardTable[player.getHandValue()-5][dealer.hand.get(0).getValue()-1]);
				
			}
			if(isSoftHand(player.hand)){
				//accesses the position of the table according to the player's hand value and the dealer's
				//up card, and returns the corresponding string instruction
				return convertToString(bsSoftTable[player.getHandValue()-13][dealer.hand.get(0).getValue()-1]);
				
			}
			if(isPair(player.hand)){
				if(handHasAce(player.hand)){
					return convertToString(bsPairTable[9][dealer.hand.get(0).getValue()]);
				}else{
				
				//accesses the position of the table according to the player's hand value and the dealer's
				//up card, and returns the corresponding string instruction
				return convertToString(bsPairTable[player.hand.get(0).getRank()-2][dealer.hand.get(0).getValue()-2]);
				}
				
			}
			return null;
		}
		
		
		public static void main(String[] args)  {
			Advice advice = new BasicStrategy();
			Player player = new Player(100);
			Dealer dealer = new Dealer();
			player.hand.add(new Card(Card.KING, Card.CLUBS));
			player.hand.add(new Card(Card.FOUR, Card.CLUBS));
			player.hand.add(new Card(Card.DEUCE, Card.CLUBS));
			dealer.hand.add(new Card(Card.ACE, Card.CLUBS));
			dealer.hand.add(new Card(Card.SIX, Card.CLUBS));
			
			System.out.println("l " +(player.getHandValue()) +" c "+(dealer.hand.get(0).getValue()-1));
			System.out.println(advice.getAdvice(player, dealer));
		}

		
		
	

}
