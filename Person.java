package blackjack;

import java.util.ArrayList;

abstract class Person{
	//attributes
	private ArrayList<Card> hand;
	
	public Person(){	//TODO:perguntar se vale a pena construtor da classe abstrata
		hand = new ArrayList<Card>();
	}		
	
	//methods
	abstract Card hit(Shoe s);
	
	abstract Card stand();
	
	abstract Card getHandValue();
	
	abstract Card hasBlackjack(Card card1, Card card2);

}
