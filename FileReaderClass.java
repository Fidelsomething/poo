package blackjack;

import java.io.*; 
/**
 * This class reads the shoe file and the command file.
 * @author public
 *
 */
public class FileReaderClass {
	
	/*Fields*/
	private String cmdFile;
	private String shoeFile;
	int buffer[] = new int[500];
	int dim_buffer;
	
	
	/**
	 * Constructor with the shoe and command files names.
	 * @param cmdFile
	 * @param shoeFile
	 */
	FileReaderClass(String cmdFile, String shoeFile){
		this.cmdFile = cmdFile;
		this.shoeFile = shoeFile;
	}
	/**
	 * This method reads the shoe file, writing the read values to an array, which is then used in the build_deck
	 * method.
	 * @throws IOException
	 * 
	 * @see build_deck
	 */
	public void ReadDeck() throws IOException{
		int aux;
		dim_buffer=0;
		int count_cards = 0;
		
		FileReader f = new FileReader (this.shoeFile);
		while((aux = f.read()) != -1) {
			if(aux ==' '){ 
				count_cards++; 
			}
			this.buffer[dim_buffer] = aux;
			//System.out.println("cena lida: "+(char)this.buffer[dim_buffer]);
			dim_buffer++;
			
		}
		count_cards++;
		System.out.println("Numero de cartas: "+count_cards);
		f.close();
	}
	/**
	 * Reads a line with the commands in the command file and returns a string with the command read.
	 * @return string with command
	 * @throws FileNotFoundException
	 */
	String ReadCommands() throws FileNotFoundException{
		FileReader f = new FileReader (this.cmdFile);
		BufferedReader buffReader = new BufferedReader(f);
		String line;
		
		try {
			line = buffReader.readLine();
			buffReader.close();
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
