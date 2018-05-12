package assign61.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import assign61.Battery;
import assign61.Flashlight;

class BatteryAndFlashlightTests {

	// Testing the battery itself and when it is inserted in a Flashlight
	@Test
	void batteryBehaviour() throws InterruptedException {

		// Testing the battery itself. Also test for auto drain.
		Battery battery = new Battery(50, 100, 5);
		battery.startUsing();
		// 2 seconds extra so that the test has time to start and to create the objects
		Thread.sleep(2000);
		Thread.sleep(30000);
		assertEquals(45, battery.getChargeLevel());
		Thread.sleep(30000);
		assertEquals(40, battery.getChargeLevel());
		battery.stopUsing();

		// Test if auto drain works when the battery is placed in a flashlight
		Flashlight flashlight = new Flashlight("test flashlight to insert the battery");
		flashlight.insertBattery(battery);
		assertTrue(flashlight.setOn());
		Thread.sleep(30000);
		assertEquals(35, battery.getChargeLevel());
		Thread.sleep(30000);
		assertEquals(30, battery.getChargeLevel());

		// Test if drain stops when you turn off the flashlight
		flashlight.setOff();
		Thread.sleep(30000);
		assertEquals(30, battery.getChargeLevel());

		// Test to see if you are able to turn on the flashlight when the battery has
		// been removed
		flashlight.removeBattery();
		assertFalse(flashlight.setOn());

		// Insert an empty battery with 0 charge
		battery = new Battery(0, 100, 5);
		flashlight.insertBattery(battery);
		assertFalse(flashlight.setOn());
		assertEquals(0, battery.getChargeLevel());

	}

}
