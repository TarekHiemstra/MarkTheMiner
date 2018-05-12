package assign61;

import java.io.Serializable;

public class GameController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameEngine myGameEngine;
	private CommandInterpreter myInterpreter;
	
	public GameController(){
		myInterpreter = new CommandInterpreter();
		myGameEngine  = new GameEngine(myInterpreter); //engine needs reference in order to stop interpreter
	}

	public void start() {
		// TODO Auto-generated method stub
		String[] userInput = null;
		
		while (!myGameEngine.getGameEnd()){
			userInput = myInterpreter.getUserInput(myGameEngine.getPrompt(),myGameEngine.getGameCommands().getValidCommands());		
			if (userInput != null&&!myGameEngine.getGameEnd()) {
				myGameEngine.stepForward(userInput);
			}
		}
		System.exit(0);
	}
}
