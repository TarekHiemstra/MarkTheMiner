package assign61.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import assign61.CommandInterpreter;
import assign61.GameEngine;

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

	// Test different inputs to win the game
	@Test
	void inputTest1() throws Exception {
		GameEngine game = new GameEngine(new CommandInterpreter());
		String[] step1 = { "east" };
		String[] step2 = { "up" };
		String[] step3 = { "up" };
		game.stepForward(step1);
		assertEquals("Dimlight", game.getPrompt());
		assertFalse(game.getGameEnd());
		game.stepForward(step2);
		assertEquals("The control room", game.getPrompt());
		assertFalse(game.getGameEnd());
		game.stepForward(step3);
		assertTrue(game.getGameEnd());
	}

	@Test
	void inputTest2() throws Exception {
		GameEngine game = new GameEngine(new CommandInterpreter());
		String[] step1 = { "west" };
		String[] step2 = { "up" };
		game.stepForward(step1);
		assertEquals("The control room", game.getPrompt());
		assertFalse(game.getGameEnd());
		game.stepForward(step2);
		assertTrue(game.getGameEnd());
	}
}
