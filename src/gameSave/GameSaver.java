package gameSave;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import gameEngine.GameEngine;

/**
 * A class used to save a game state, the players and their progress in the game.
 * @author Dan
 *
 */
public class GameSaver 
{
	
	/**
	 * Static method that saves player name and their progress
	 * to the game save file. Overwrites a file called "DapGamePlay.dap",
	 * otherwise creates a new file.
	 * 
	 * @param saveInfo HashMap existing data
	 */
	public static void saveGame(HashMap<String,int[]> saveInfo) {
		//will take in a hashMap with all player names and their progress
		//game should add their name and progress to a hashmap when saving progress
		
		HashMap<String, int[]> playersProgress = saveInfo;
		File gameFile = new File("mapFolder", "saveFiles.dap");
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(gameFile));
			String writeLine = "[Players]";
			for(String name: playersProgress.keySet()) {
				writeLine = writeLine +  " " + name;
			}
			writeLine = writeLine + " ;";
			bw.write(writeLine);
			bw.newLine();
			bw.write("[Progress]");
			bw.newLine();
			for(String keyName: playersProgress.keySet()) {
				String playerLines = keyName;
				for(int j = 0; j < playersProgress.get(keyName).length; j++) {
					playerLines = playerLines + " "+ playersProgress.get(keyName)[j];
				}
				bw.write(playerLines + " ;");
				bw.newLine();
			}
			
			bw.close();
			
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
	
	
	
}
