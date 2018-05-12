package assign61;

import java.io.Serializable;

public class Battery implements TimedEventHandler, Serializable {
		
	private int chargeLevel;
	private int drainSpeed;
	private transient Thread drainTheBattery;
	private TimedEvent batteryDraining;
	
	public Battery (int initialCharge, int Capacity, int drainSpeed) {
		this.drainSpeed = drainSpeed;
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
