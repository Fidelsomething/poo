package blackjack;

import java.util.ArrayList;

//TODO decidir como fazer tabelas ou se valem a pena sequer (agrupar entradas repetidas em if's)

class CardCounting {
	//Basic strategy hard, soft and pair tables (fig.1)
	final static int[][] bsHardTable = new int[16][10];
	final static int[][] bsSoftTable = new int[8][10];
	final static int[][] bsPairTable = new int[10][10];
	
	
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
			return true;
		}else{
			return false;
		}
	}
	
	boolean isSoftHand(ArrayList<Card> hand){
		if(!handHasAce(hand)){
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
	
}//class
