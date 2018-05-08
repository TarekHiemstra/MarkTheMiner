package assign61;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GameEngine implements TimedEventHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommandInterpreter myInterpreter; 
	private Player myPlayer;
	private boolean gameEnd;
	private GameCommands gameCommands;
	//private final String ENDCOMMAND="end";
	//private final String SAVECOMMAND = "save";
	//private final String TAKECOMMAND = "take";
	private ControlRoom control;
	private Space exit;
	
	private transient Thread explosionThread;

	public GameEngine(CommandInterpreter commandInterpreter){

		this.myPlayer 		= new Player();
		this.gameEnd 		= false;
		this.myInterpreter 	= commandInterpreter;
		this.gameCommands = new GameCommands(myPlayer);

		//create spaces
		Space entry 		= new Space("Entry","Welcome back. \n"
				+ "You're in a collapsed mine 900 ft below the ground.\n"
				+ "Ticking sound comes through the collapsed cave. \n"
				+ "Somewhere close by there is a running water tap. Better get out of here asap!");
		Space dimLight 		= new Space ("Dimlight","The room is dimly lit and nothing can be seen");
		control = new ControlRoom ("The control room","Watch out this room is getting filled with water");
		exit 				= new Space("Exit","Congratulations you saved Mark");

		//set up the map
		entry.putNextPlace("east",dimLight);
		entry.putNextPlace("west",control);
		dimLight.putNextPlace("west",entry);
		dimLight.putNextPlace("up",control);
		control.putNextPlace("up",exit);
		
		//create game objects
		Flashlight flashlight = new Flashlight ("flashlight");
		flashlight.insertBattery(new Battery (50, 100, 5));
		dimLight.addGameObject(flashlight);

		//register to listen to timed event
		TimedEvent explosion = new TimedEvent ("explosion", 100, this);
		explosionThread = new Thread(explosion);
		explosionThread.start();

		//set starting space for the player
		myPlayer.setCurrentSpace(entry);
	}

	public GameCommands getGameCommands(){
		return gameCommands;
	}
	
	/* OLD CODE
	public Set<String> getOptions() {
		// TODO Auto-generated method stub
		//LUOKAN SISÃ„Ã„N???? anna komennot
		//Set interface doesn't allow add, a concrete set is required
		HashSet<String> hs= new HashSet<String>(myPlayer.getCurrentSpace().getOptions());
		hs.add(ENDCOMMAND);
		hs.add(SAVECOMMAND);
		hs.add(TAKECOMMAND);
		
		//name of an object in player's inventory can be used as the first part of a command
		for (String key:myPlayer.getInventory().keySet()) {
			hs.add(key);
		}
		return hs;
	}
*/
	public String getPrompt() {
		// TODO Auto-generated method stub
		return myPlayer.getCurrentSpace().getDescription();
	}

	public void stepForward(String[] userInput) {
		if (control.getWaterLevel() >= 100 ) {
			this.gameEnd = true;
			myInterpreter.stop();
		} else if (userInput[0].equalsIgnoreCase(GameCommands.ENDCOMMAND)) {
			gameEnd = true;
			explosionThread.interrupt();
			myInterpreter.stop();
		} else if (userInput[0].equalsIgnoreCase(GameCommands.SAVECOMMAND)){
			Main.saveGame();	
		} else if (userInput[0].equalsIgnoreCase(GameCommands.TAKECOMMAND)&&userInput.length>1){
			System.out.println("***user input part2="+userInput[1]);
			GameObject objectToBeAdded = myPlayer.getCurrentSpace().getGameObject(userInput[1]);
			if (objectToBeAdded!=null){
				myPlayer.putGameObject(objectToBeAdded);
				System.out.println("***adding object to player inventory");
			} else {
				System.out.println("No such game object "+userInput[1]);
			}
		} else if (myPlayer.getInventory().containsKey(userInput[0])&&userInput.length>1) {
			GameObject thisGameObject = myPlayer.getInventory().get(userInput[0]);
			System.out.println("working on inventory item:"+thisGameObject.getName());
			if (thisGameObject instanceof OnOff) {
				System.out.println("it is an OnOff item");
				OnOff onOffDevice = (OnOff)thisGameObject;
				if (userInput[1].equalsIgnoreCase("on")) {
					onOffDevice.setOn();
				} else if (userInput[1].equalsIgnoreCase("off")) {
					onOffDevice.setOff();
				}
			}
		} else {
			myPlayer.setLocation(myPlayer.getCurrentSpace().getNext(userInput[0]));
			if (myPlayer.getCurrentSpace()==exit) {
				System.out.println(myPlayer.getCurrentSpace().getDescription());
				gameEnd = true;
				explosionThread.interrupt();
				myInterpreter.stop();
			}
		}
	}

	public void handleEvent(String event) {
		if (event.equalsIgnoreCase("explosion")) {
			System.out.println ("You were not fast enough. Mine was collapsed in an explosion.");
			gameEnd = true;
			myInterpreter.stop();
		}	
	}

	public boolean getGameEnd(){
		return this.gameEnd;
	}
}
