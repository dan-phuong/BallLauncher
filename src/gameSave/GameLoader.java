package gameSave;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import gameEngine.GameEngine;

/**
 * A class used to load in a game save state into the game. Uses a static method to return a HashMap
 * of player names and their progress levels.
 * @author Dan
 *
 */
public class GameLoader 
{
	/**
	 * Blank constructor GameLoader
	 */
	public GameLoader()
	{
		
	}
	
	/**
	 * Takes in a GameFileSave and GameEngine and modifies data in the game engine.
	 * Information in the save file are player names and their progress.
	 * Progress is represented in integers, 0 being unattempted and uncleared,
	 * 1 being attempted and cleared, and 2 being cleared and attempted
	 * @param file inputFile
	 * @param gameEngine gameEngine who is loading in the file
	 * @return HashMap of players and their progress
	 */
	public static HashMap<String,int[]> loadGame(File file)
	
	//eventually we will reimplement having the GameEngine as a parameter, for testing purposes it will be omitted
	//if it is easier it might be better to take a string filePath as a parameter
	{
		HashMap<String, int[]> playerProgresses = new HashMap<>();
		
		if(!file.exists()) {
			System.out.println("File No exists");
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String rline = br.readLine();
			String [] playerSection = rline.split("\s+");
			
			//must be length -2 since first string is [players] and last string is ;
			String[] players = new String[playerSection.length - 2];
			//players String array should have a list of all listed players
			for(int i = 0; i < players.length; i++) {
				players[i] = playerSection[i+1];
				System.out.println(players[i]);
			}
			for(String p: players) {
				playerProgresses.put(p, null);
			}
			//by this point hashMap should be populated with player names as Keys, null values as Values
			rline = br.readLine();
			if(!rline.equals("[Progress]")) {
				System.exit(1);
			}
			rline = br.readLine();
			while(rline != null) {
				
				String [] playersLevels = rline.split("\s+");
				String playerName = playersLevels[0];
				//will have to confirm that array[i] = array item does not just copy by reference
				int[] levels = new int[playersLevels.length-2];
				
				for(int i = 0; i < levels.length; i++) {
					levels[i] = Integer.parseInt(playersLevels[i+1]);
				}
				playerProgresses.put(playerName, levels);
				rline = br.readLine();
			}
			br.close();
			//here goes the code that will change stuff in the game loader
			
			//temporarily, it would be nice to just return the values directly as a check
			System.out.println("This call is in game loader" + playerProgresses.toString());
			return playerProgresses;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("oops, something went wrong");
		return null;
	}
	
	
}
