package blackjack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
/**
 * A shoe is a set of cards composed by several decks, from which the game cards are drawn.
 * @author public
 *
 */
public class Shoe {
	/*Field*/
	int array_card[];
	int array_total[];
	int rank, suit;
	static int cardsplayed;
	static int nrdecks;
	
	ArrayList<Card> cards;
	

	/**
	 * Constructor of shoe for debug mode, where shoe is read from file. 
	 * @throws IOException exception thrown if file does not exist
	 */
	Shoe() throws IOException{
		FileReaderClass f = new FileReaderClass("cmd-file.txt", "shoe-file.txt");
		this.cards = new ArrayList<Card>(f.dim_buffer);
		f.ReadDeck();
		build_deck(f.buffer, f.dim_buffer);
		
	}
	
	/**
	 * Constructor of shoe for interactive mode. Creates all cards that compose a deck, times the number of
	 *  decks, and shuffles them
	 * @param ndecks number of decks that compose the shoe
	 */
	Shoe(int ndecks){
		Shoe.nrdecks = ndecks;
		this.cards = new ArrayList<Card>(nrdecks*52);
		
		// for the given number of decks we'll get all the cards
		for(int d=0; d<nrdecks; d++){
			for(int suit = 1; suit <= 4; suit++){ // naipes
				for(int rank = 1; rank <= 13; rank++){ // cartas
					cards.add(new Card(rank, suit));
				}
			}
		}
		shuffleShoe();
	}
	
	/**
	 * Override of the toString method
	 */
	@Override
	public String toString() {
		return "Shoe [" + cards + "]";
	}



	/*Methods*/
	
	/**
	 * drawCard
	 * 
	 * Takes a card from the cards array list, and puts it back at the end of the shoe.
	 * @return the drawn card is returned
	 */
	Card drawCard() {
		Card cardDrawn = this.cards.remove(0);
		this.cards.add(this.cards.size(), cardDrawn);	//drawn card is put at the end of the shoe
		//this.cards_out++;
		if(cardsplayed ==  this.cards.size()){
			System.out.println("---------------------SHOE ENDED!---------------------");
		}
		Advisor.updateHiLo(cardDrawn, Shoe.nrdecks);
		Shoe.cardsplayed++;
		if(Card.hiddencard==false) Advisor.checkCardAF(cardDrawn);
		return cardDrawn;
	}
	
	/**
	 * Shuffles the shoe and resets the cards_out variable.
	 */
	void shuffleShoe(){
		System.out.println("shuffling the shoe...");
		Collections.shuffle(cards);
		Advisor.resetCardCounting();
	}
	
	/**
	 * checkShuffle
	 * 
	 * Checks if there is a situation to do shuffle.
	 * @return void
	 */
	void checkShuffle(Table table) {
		if(cardsplayed > table.shuffle*0.01*this.cards.size()){
			shuffleShoe();
		}
	}
	
	/**
	 * Converts the integers read from the file to the format integer of the class Card.
	 * @param character
	 * @return integer with Card format
	 */
	int convertToCard(int character){
		int inteiro;
        switch (character) {
            case 'A':  inteiro = 1;
                     break;
            case '2':  inteiro = 2;
                     break;
            case '3':  inteiro = 3;
                     break;
            case '4':  inteiro = 4;
                     break;
            case '5':  inteiro = 5;
                     break;
            case '6':  inteiro = 6;
                     break;
            case '7':  inteiro = 7;
                     break;
            case '8':  inteiro = 8;
                     break;
            case '9':  inteiro = 9;
                     break;
            case '*': inteiro = 10;
                     break;
            case 'J': inteiro = 11;
                     break;
            case 'Q': inteiro = 12;
                     break;
            case 'K': inteiro = 13;
            		 break;
            case 'D': inteiro = 1;
   		 			 break;
            case 'C': inteiro = 2;
   		 			 break;
            case 'H': inteiro = 3;
            		 break;
            case 'S': inteiro = 4;
   		 			 break;
            default: inteiro = '?';
                     break;
        }
        return inteiro;
		
	}
	
	/**
	 * Generates the shoe according to file read
	 * @param array_total array with values read
	 * @param dim total cards read
	 */
	void build_deck(int[] array_total, int dim){
		this.array_card = new int[5];// 4 deve chegar!!
		int j=0;
		int flag = 0;
		//this.array_total = new int[dim];
		this.array_total = new int[50];
		this.array_total = array_total;
		for(int i=0;i<dim;i++){
			
			array_card[j] = array_total[i];
			if(array_total[i] == ' '){
				
				if(j==2){
					rank = convertToCard(array_card[0]);
					suit = convertToCard(array_card[1]);
					//cards[k] = new Carta(rank,suit);
					cards.add(new Card(rank, suit));
				}
				if(j==3){
					rank = convertToCard('*');//DEVIA SE FAZER ISTO DE OUTRA FORMA (*=10)
					suit = convertToCard(array_card[2]);
					//cards[k] = new Carta(rank,suit);
					cards.add(new Card(rank, suit));
				}
				flag=1;
				j=0;
			}
			if(flag==0) j++;
			flag = 0;
			
		}
		//FALTA A ULTIMA CARTA!(repetir codigo dos ifs?)
		if(j==2){
			rank = convertToCard(array_card[0]);
			suit = convertToCard(array_card[1]);
			//cards[k] = new Carta(rank,suit);
			cards.add(new Card(rank, suit));
		}
		if(j==3){
			rank = convertToCard('*');//DEVIA SE FAZER ISTO DE OUTRA FORMA (*=10)
			suit = convertToCard(array_card[2]);
			//cards[k] = new Carta(rank,suit);
			cards.add(new Card(rank, suit));
		}
	}


}
