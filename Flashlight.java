package assign61;

import java.io.Serializable;

public class Flashlight extends LightSource implements Serializable {

	private Battery battery;
	private boolean lampOn;
	
	public Flashlight (String name) {
		super(name);
	}

	@Override
	public boolean setOn() {
		// TODO Auto-generated method stub
		if (battery != null&&battery.getChargeLevel()>0) {
			lampOn = true;
			battery.startUsing();
			System.out.println("Flashlight is now on");
		} else {
			lampOn = false;
		}
		return lampOn;
	}

	@Override
	public void setOff() {
		// TODO Auto-generated method stub
		battery.stopUsing();
	}
	
	public void removeBattery() {
		battery = null;
	}
	
	public void insertBattery(Battery battery) {
		this.battery = battery;
	}
}
