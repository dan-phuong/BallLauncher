package gameEngine;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gameObjects.Ball;
import gameObjects.GameObject;
import gameSave.GameLoader;
import gui.Game;
import gui.GameGui;
import gui.LoadPrompt;
import gui.SavePrompt;
import gui.StartMenu;
import physicsEngine.ActionQueue;
import physicsEngine.PhysicsEngine;

/**
 * The main engine for running the game and communicating with the GameGUI
 * @author Dan
 *
 */
public class GameEngine 
{
	public JFrame frame;
	public StartMenu start;
	public GameGui present_level;
	private static GameEngine engine;
	private PhysicsEngine physics;
	private HashMap<String, int[]> playerSaves;
	private String currentPlayer;
	private Game game;
	
	public Ball birdo;
	public final String [] mapLevels = {"Level_1", "Level_2", "Level_3"};
	private GameEngine(){}
	
	
	/**
	 * Initializes the game and sets up the startMenu GUI.
	 */
	public void startGame() {
		
		playerSaves = new HashMap<String, int[]>();
        currentPlayer = "";
        File loadFile = new File("mapFolder", "saveFiles.dap");
        playerSaves = GameLoader.loadGame(loadFile);
        
        frame = new JFrame("Start");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        start = new StartMenu(frame);
        frame.getContentPane().add(start);
        frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 * Displays the Save menu GUI
	 */
	public void displayStart() {
		
        JFrame frame = new JFrame("Save Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new SavePrompt(frame));
        frame.pack();
        frame.setVisible(true);   
	}
	
	/**
	 * Displays the Load menu GUI
	 */
	public void displayLoad() {
        JFrame frame = new JFrame("Load Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new LoadPrompt(frame));
        frame.pack();
        frame.setVisible(true);
	}
	

	/**
	 * Responsible for communicating with the physics engine to update the Positions
	 * and Velocities of the GameObjects
	 */
	public void runSimulation()
	{
	
		ActionQueue actions = new ActionQueue();
		for(GameObject obj : game.getLevel().mapObjects)
		{
			obj.notifyPhysics(actions);
		}
		
		//flush actions queue
		while(actions.size()>0)
		{
			int i = getObjects().indexOf(actions.nextUpdater().getReference());
			game.getLevel().mapObjects.set(i, actions.performAction());
		}
			//game.run();
		
	}
	
	/**
	 * getter for the GameGui instance variable
	 * @return the current GameGui being run.
	 */
	public GameGui getGui() 
	{
		return present_level;
	}
	
	
	//essentially, this is the run game loop
	/**
	 * Initializes main screen of the game, creates physics engine
	 */
	public void displayGame() 
	{
		Game thisGame = new Game(1350,660);
		Thread gThread = new Thread(thisGame);
		gThread.start();
        SwingUtilities.invokeLater(()-> {
            {
                game = thisGame;

                present_level = new GameGui(thisGame);
                //present_level = new GameGui();
                physics = new PhysicsEngine();
                for(GameObject o : thisGame.getLevel().mapObjects)
                {
                	o.addPhysicsObserver(physics);
                }
            }
        });
	}
	
	/**
	 * If no engine exists, creates a new engine
	 * If one exists, gets that engine.
	 * @return the GameEngine Singleton object
	 */
	public static GameEngine getEngine()
	{
		if(engine == null) {
			engine = new GameEngine();
		}
		return engine;
	}
	
	/**
	 * Gets the player saves stored.
	 * @return the player saves
	 */
	public HashMap<String, int[]> getPlayers(){
		return playerSaves;
	}
	
	/**
	 * adds a new player to the be stored in the player saves
	 * @param player the player name to be stored.
	 */
	public void addPlayer(String player) {
		int[] newPlayerProg = {0,0,0};
		playerSaves.put(player, newPlayerProg);
	}
	
	/**
	 * Gets the current player
	 * @return the current player
	 */
	public String getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * sets the current player
	 * @param player the new player to be set to.
	 */
	public void setCurrentPlayer(String player) {
		this.currentPlayer = player;
	}
	
	/**
	 * gets the current Game instance.
	 * @return the game instance
	 */
	public Game getGame()
	{
		return game;
	}
	/**
	 * Gets an ArrayList of all the objects on the map
	 * @return an ArrayList of GameObjects
	 */
	public ArrayList<GameObject> getObjects()
	{
		return game.getLevel().mapObjects;
	}
	
	
}
