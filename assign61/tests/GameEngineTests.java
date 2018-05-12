package assign61.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import assign61.CommandInterpreter;
import assign61.GameController;
import assign61.GameEngine;
import assign61.Main;

class GameEngineTests {

	// Test the explosion time and test if the game ends after 100 seconds
	@Test
	void explosionTime() throws InterruptedException {
		GameEngine game = new GameEngine(new CommandInterpreter());
		game.closeTap();
		Thread.sleep(99000);
		assertFalse(game.getGameEnd());
		Thread.sleep(1000);
		assertTrue(game.getGameEnd());
	}
}
