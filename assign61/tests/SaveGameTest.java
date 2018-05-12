package assign61.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import assign61.GameController;
import assign61.Main;

class SaveGameTest {

	// Test the save game functionality
	@Test
	void saveGame() throws Exception {
		Main.myGameController = new GameController();
		Main.saveGame();
		File f = new File("MarkTheMiner.ser");
		assertTrue(f.exists() && !f.isDirectory());
	}

}
