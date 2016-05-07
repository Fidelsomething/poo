package blackjack;
import java.util.ArrayList;
import java.util.Collections;

//TODO: Shuffle accordingly to percentage

public class Shoe {
	/*Field*/
	int array_card[];
	int array_total[];
	int rank, suit;
	
	//Carta[] cards = new Carta[52];
	ArrayList<Card> cards;
	
	Shoe(int ndecks){
		this.cards = new ArrayList<Card>(ndecks*52);
		
		// for the given number of decks we'll get all the cards
		for(int d=0; d<ndecks; d++){
			for(int suit = 1; suit <= 4; suit++){ // naipes
				for(int rank = 1; rank <= 13; rank++){ // cartas
					cards.add(new Card(rank, suit));
				}
			}
		}
		//shuffle cards 
		Collections.shuffle(cards);
	}
		
	
	@Override
	public String toString() {
		return "Shoe [" + cards + "]";
	}



	/*Methods*/
	int convertToCard(int caracter){
		int inteiro;
        switch (caracter) {
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
	
	void build_deck(int[] array_total, int dim){
		this.array_card = new int[5];// 4 deve chegar!!
		int j=0;
		int flag = 0;
		//this.array_total = new int[dim];
		this.array_total = new int[50];
		this.array_total = array_total;
		for(int i=0;i<dim;i++){
			System.out.println("j="+j+" i="+i);
			array_card[j] = array_total[i];
			if(array_total[i] == ' '){
				System.out.println("if");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int array_joao[];
//		array_joao = new int[50];
//		array_joao[0] = 'A';
//		array_joao[1] = 'D';
//		array_joao[2] = ' ';
//		array_joao[3] = 'A';
//		
//		for(int i=0; i<5; i++){
//		System.out.println((char)array_joao[i]);
//		}
//		for(int i=0; i<array_joao.length; i++){
//			System.out.println((char)array_joao[i]);
//		}
//		
//		Shoe joao = new Shoe();
//		joao.build_deck(array_joao,3);
//		//System.out.println(joao.cards[0]);
		Shoe s = new Shoe(1);
		System.out.println(s.toString());
		System.out.println(s.cards.size());

	}

	Card drawCard() {
		return this.cards.remove(0);
	}

}
