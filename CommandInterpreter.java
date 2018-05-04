package assign61;


import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;

public class CommandInterpreter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Scanner thisScanner;
	private boolean stop;
	
	public CommandInterpreter(){
		this.thisScanner = new Scanner(System.in);
		this.stop = false;
	}
	
	//
	public String[] getUserInput(String prompt, Set<String> validInputs) {
		// TODO Auto-generated method stub
		boolean validInput = false;
		String userInput = "";
		String userInputParts[] = null;
		
		while (!validInput&&!stop) {
			System.out.println(prompt);
			userInput = this.thisScanner.nextLine();
			
			userInputParts = userInput.split(" ");
			
			if (validInputs.contains(userInputParts[0])) {
				validInput = true;
			} else if (!stop) {
				System.out.println("Invalid command");
			}
		}
		
		if (stop) System.out.println("....interpreter stopped");
		
		return userInputParts;		
	}
	
	public void stop() {
		this.stop = true;
		thisScanner.close();
		System.out.println("****stopping interpreter");
	}
}
