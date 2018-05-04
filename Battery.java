package assign61;

public class Battery implements TimedEventHandler {
	
	private int chargeLevel;
	private int drainSpeed;
	private transient Thread drainTheBattery;
	private TimedEvent batteryDraining;
	
	public Battery (int initialCharge, int Capacity, int drainSpeed) {
		this.drainSpeed = 0;
		this.chargeLevel = initialCharge;
	}

	@Override
	public void handleEvent(String event) {
		// TODO Auto-generated method stub
		if (chargeLevel >0){
			chargeLevel = chargeLevel - drainSpeed;
		} else {
			drainTheBattery.interrupt();
			chargeLevel = 0;
		}
	}

	public void startUsing(){
		batteryDraining = new TimedEvent ("Battery draining",30,this);
		drainTheBattery = new Thread (batteryDraining);
		drainTheBattery.start();
	}
	
	public void stopUsing(){
		drainTheBattery.interrupt();
	}
	
	public int getChargeLevel(){
		return chargeLevel;
	}
	
}
