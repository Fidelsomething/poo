package blackjack;
/**
 * Class with all info and methods regarding a card, such as rank, suit, value, etc.
 * @author public
 *
 */
public class Card {
	
	/*Fields*/
    final int rank;
    final int suit;
    static boolean hiddencard;

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

    /**
     * Card constructor
     * @param rank
     * @param suit
     */
    public Card(int rank, int suit) {//constructor
        assert isValidRank(rank);
        assert isValidSuit(suit);
        this.rank = rank;
        this.suit = suit;
    }
    /**
     * Getter for card suit.
     * @return suit
     */
    public int getSuit() {
        return suit;
    }
    /**
     * Getter for the card rank.
     * @return rank
     */
    public int getRank() {
        return rank;
    }
    /**
     * Get cards' value. An Ace is worth 1 by default, cards from 2 to 10 are worth their face value, and 
     * the King, Jack and Queen are worth 10.  
     * @return value of the card, according to rank
     */
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
    /**
     * Method that checks if a card is a an ace.
     * @return true if card is an ace, false otherwise
     */
    boolean isAce(){
    	if(this.rank == ACE){
    		return true;
    	}else{
    		return false;
    	}
    }
    /**
     * Redefinition of equals. Two cards are equal if they have the same rank.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            //return (((Card)obj).getRank() == this.rank && ((Card)obj).getSuit() == this.suit);
        	return (((Card)obj).getRank() == this.rank);
        } else {
            return false;
        }
    }
    /**
     * Redefinition of hashCode. 
     */
    @Override
    public int hashCode() {
        return ((suit-1)*13)+rank;
    }
    /**
     * Redefinition of toString. Prints the rank and suit of a card. 
     */
    @Override
    public String toString() {
        return rankToString(this.rank)+suitToString(this.suit);
    }
    /**
     * Determines if rank is valid.
     * @param rank
     * @return true if rank is valid, false otherwise
     */
    public static boolean isValidRank(int rank) {
        return ACE <= rank && rank <= KING;
    }
    /**
     * Determines if suit is valid.
     * @param suit
     * @return true if suit is valid, false otherwise
     */
    public static boolean isValidSuit(int suit) {
        return DIAMONDS <= suit && suit <= SPADES;
    }
    /**
     * Converts the integer code used for rank to a human readable string.
     * @param rank
     * @return string with rank
     */
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
    /**
     * Converts the integer code used for suit to a human readable string.
     * @param suit
     * @return string with suit
     */
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

   
}
