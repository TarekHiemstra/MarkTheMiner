package assign61;

public class ControlRoom extends Space implements TimedEventHandler {

	/**
	 * This is the room that can fill with water
	 */
	
	private int waterLevel;
	private final int MAXWATERLEVEL = 100;
	private final int INCREMENT = 10;
	private static final long serialVersionUID = 1L;
	private boolean tapOpen;
	private  TimedEvent roomFilling;
	private transient Thread waterRunning;
	
	public ControlRoom(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
		waterLevel = 10;
		tapOpen = true;
		roomFilling = new TimedEvent("Control room filling up",10,this);
		waterRunning = new Thread(roomFilling);
		waterRunning.start();
	}

	public void closeTap() {
		super.setDescription("Control room. Water level is "+waterLevel+". Tap is closed");
		tapOpen = false;
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		if (tapOpen) {
			waterLevel = waterLevel + INCREMENT;
			System.out.println("Water level in Control room is now "+waterLevel+". Hurry Up!");
			if (waterLevel >MAXWATERLEVEL) {
				//close this room, end of game
				waterRunning.interrupt();
			}
		}
	}
	
}
