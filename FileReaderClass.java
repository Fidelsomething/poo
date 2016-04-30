package blackjack;

import java.io.*; 
import java.util.Collections;

public class FileReaderClass {
	
	/*Fields*/
	private String file;
	private int buffer[] = new int[500];
	private int dim_buffer;
	
	
	/*Methods*/
	//constructor
	FileReaderClass(String file){
		this.file = file;
	}
	
	public void ReadDeck() throws IOException{
		int aux;
		dim_buffer=0;
		int count_cards = 0;
		
		FileReader f = new FileReader (this.file);
		while((aux = f.read()) != -1) {
			if(aux ==' '){ 
				count_cards++; 
			}
			this.buffer[dim_buffer] = aux;
			System.out.println("cena lida: "+(char)this.buffer[dim_buffer]);
			dim_buffer++;
			
		}
		count_cards++;
		System.out.println("Numero de cartas: "+count_cards);
		f.close();
	}
	
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileReaderClass baralho = new FileReaderClass("shoe-file.txt");
		baralho.ReadDeck();
		
		System.out.println("dim vector matrix: "+baralho.dim_buffer);
		
		Shoe deck = new Shoe();
		deck.build_deck(baralho.buffer, baralho.dim_buffer);
		System.out.println(deck.cards.get(5));
		Collections.shuffle(deck.cards);
		System.out.println(deck.cards.get(50));
		

	}

}
