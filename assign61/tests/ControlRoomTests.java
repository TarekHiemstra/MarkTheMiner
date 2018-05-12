package assign61.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import assign61.ControlRoom;

class ControlRoomTests {

	// Auto drain and closeTap method test
	@Test
	void closeTheTapInTime() throws InterruptedException {

		ControlRoom controlRoom = new ControlRoom("test", "test control room");
		// 2 seconds extra so that the test has time to start and to create the objects
		Thread.sleep(2000);
		Thread.sleep(10000);
		assertEquals(20, controlRoom.getWaterLevel());
		Thread.sleep(10000);
		assertEquals(30, controlRoom.getWaterLevel());

		// Test the closeTap method
		controlRoom.closeTap();
		Thread.sleep(10000);
		assertEquals(30, controlRoom.getWaterLevel());
	}

	// Test to see if the water level does not exceed 100
	@Test
	void closeTheTapNotInTime() throws InterruptedException {

		ControlRoom controlRoom = new ControlRoom("test", "test control room");
		Thread.sleep(110000);
		assertEquals(100, controlRoom.getWaterLevel());
	}

}
