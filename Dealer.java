package blackjack;

class Dealer extends Person{

	public Dealer() {
		super();
	}

	@Override
	Card hit(Shoe s) {
		// TODO Auto-generated method stub
		return s.drawCard();
	}

	@Override
	Card stand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Card getHandValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Card hasBlackjack(Card card1, Card card2) {
		// TODO Auto-generated method stub
		return null;
	}

}
