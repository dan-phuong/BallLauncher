package gameEngine;

/**
 * The main class to run the DAP program
 * @author Dan
 *
 */
public class GameMaker 
{
	/**
	 * Main Method to instantiate the GameEngine.
	 * @param args
	 */
	public static void main(String[] args)
	{
		GameEngine engine = GameEngine.getEngine();
		engine.startGame();
	}
}
