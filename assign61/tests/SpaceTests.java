package assign61.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.Test;
import assign61.Flashlight;
import assign61.Space;

class SpaceTests {

	// Test the addGameObject and getGameObject methods
	@Test
	void putAndGetGameObject() throws Exception {
		Space space = new Space("test", "test space");

		String expected = "test flashlight";
		space.addGameObject(new Flashlight(expected)); // Flashlight is a (subset of) GameObject
		String actual = space.getGameObject(expected).getName();
		assertEquals(expected, actual);
	}

	// Test inventory with 2 flashlights (since Flashlight is the only GameObject)
	@Test
	void showInventoryWithMultipleItems() throws Exception {
		Space space = new Space("test", "test space");

		space.addGameObject(new Flashlight("flashlight1"));
		space.addGameObject(new Flashlight("flashlight2"));
		String expected = "test space\n" + "Room has " + "flashlight1" + " " + "flashlight2 ";
		assertEquals(expected, space.getDescription());

		// Same test, but by setting a new description with setDescription() firsts
		space.setDescription("test space 2");
		expected = "test space 2\n" + "Room has " + "flashlight1" + " " + "flashlight2 ";
		assertEquals(expected, space.getDescription());
	}

	// Test the putNextPlace method and test if the commands to access them are
	// correct
	@Test
	void nextSpacesLeftAndRight() throws Exception {
		Space space = new Space("test", "test space");
		Space space2 = new Space("test2", "test space 2");
		Space space3 = new Space("test3", "test space 3");

		space.putNextPlace("left", space2);
		space.putNextPlace("right", space3);
		assertEquals(space2, space.getNext("left"));
		assertEquals(space3, space.getNext("right"));

		// Test to see if the commands for the available 'next spaces' work
		Set<String> keys = space.getOptions();
		String[] keysArray = keys.toArray(new String[keys.size()]);
		assertEquals(keysArray[0], "left");
		assertEquals(keysArray[1], "right");
	}

}
