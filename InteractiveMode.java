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
//		String[] validCommands = {"b","$","d","h","s","i","u","p","2","ad","st"};
//		boolean isValid = false;
//		for(String s: validCommands){
//			if(command.equals(s)){
//				isValid = true;
//			}
//		}
//		if(!isValid){
//			System.out.println("illegal command");
//		}
		//System.out.println("Command read:  "+command);
		return command;  
	 }  
		 
	

}
