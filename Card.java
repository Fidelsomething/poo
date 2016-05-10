package blackjack;

public class Card {
	
	/*Fields*/
    private final int rank;
    private final int suit;

    // Kinds of suits
    public final static int DIAMONDS = 1;
    public final static int CLUBS    = 2;
    public final static int HEARTS   = 3;
    public final static int SPADES   = 4;

    // Kinds of ranks
    public final static int ACE   = 1;
    public final static int DEUCE = 2;
    public final static int THREE = 3;
    public final static int FOUR  = 4;
    public final static int FIVE  = 5;
    public final static int SIX   = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE  = 9;
    public final static int TEN   = 10;
    public final static int JACK  = 11;
    public final static int QUEEN = 12;
    public final static int KING  = 13;

    /*Methods*/
    public Card(int rank, int suit) {//constructor
        assert isValidRank(rank);
        assert isValidSuit(suit);
        this.rank = rank;
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
    /* Get cards' value  */
    int getValue(){
    	if(this.rank == ACE){
    		return 1; // default = 1, but can be 11 depending on the hand
    	}
    	if(this.rank >= DEUCE && this.rank <= TEN){
    		return this.rank;
    	}else{
    		return 10;
    	}
    }
    
    
    //Duas cartas sao iguais se tiverem o mesmo suit e rank
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            return (((Card)obj).getRank() == this.rank && ((Card)obj).getSuit() == this.suit);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return ((suit-1)*13)+rank;
    }

    @Override
    public String toString() {
        return rankToString(this.rank)+suitToString(this.suit);
    }

    public static boolean isValidRank(int rank) {
        return ACE <= rank && rank <= KING;
    }

    public static boolean isValidSuit(int suit) {
        return DIAMONDS <= suit && suit <= SPADES;
    }

    public static String rankToString(int rank) {
        switch (rank) {
        case ACE:
            return "A";
        case DEUCE:
            return "2";
        case THREE:
            return "3";
        case FOUR:
            return "4";
        case FIVE:
            return "5";
        case SIX:
            return "6";
        case SEVEN:
            return "7";
        case EIGHT:
            return "8";
        case NINE:
            return "9";
        case TEN:
            return "10";
        case JACK:
            return "J";
        case QUEEN:
            return "Q";
        case KING:
            return "K";
        default:
            return null;
        }    
    }
    
    public static String suitToString(int suit) {
        switch (suit) {
        case DIAMONDS:
            return "D";
        case CLUBS:
            return "C";
        case HEARTS:
            return "H";
        case SPADES:
            return "S";
        default:
            return null;
        }    
    }

    public static void main(String[] args) {
        Card aceofspades= new Card(ACE,SPADES);
        System.out.println(aceofspades);
    }
}
