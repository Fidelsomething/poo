package blackjack;

//import java.util.Scanner;
import java.io.*;  

class InteractiveMode  implements GameMode{

	@Override
	public String GetCommand() throws IOException {
		// TODO Auto-generated method stub
		/*
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter command");
		String command = sc.next();
		System.out.println("Caracter lido: " + command);
		
		sc.close();
		*/
		
		
		
		InputStreamReader r = new InputStreamReader(System.in);  
		BufferedReader br = new BufferedReader(r);  
		  
		System.out.println("Enter command:");  
		String command = br.readLine();  
		//System.out.println("Command read:  "+command);
		return command;  
	 }  
		 
	

}
