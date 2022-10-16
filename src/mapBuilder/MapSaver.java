package mapBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import gameObjects.GameObject;

/**
 * Class that has method to save DAP maps
 * @author Dan
 *
 */
public class MapSaver {
	
	/**
	 * Creates a map File with the given gameObjects and mapID.
	 * If map file with matching ID does not exist, a new map file is made
	 * @param id MapName
	 * @param gameObjects Arraylist
	 */
	public static void saveMap(String id, ArrayList<GameObject> gameObjects) {
		String mapName = id;
		ArrayList<GameObject> objs = gameObjects;
		int quantity = objs.size();
		
		File fun = new File("mapFolder", (id + ".mlv"));
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fun));
			bw.write("[Map Name]:");
			bw.newLine();
			bw.write("'" + mapName + "'");
			bw.newLine();
			bw.write("[Objects]:");
			bw.newLine();
			//order for object attributes: objType posX posY width height mass velocityX velocityY HP spritePath ColorR ColorG ColorB
			
			for(GameObject thing: objs) {
				String line = thing.getObjType()+ " " + thing.getPosition().getX() + " " + thing.getPosition().getY() + " "
						+ thing.getWidth() + " " + thing.getHeight() + " "+ thing.getMass() + " " 
						+ thing.getVelocity().getXcomp() + " "+ thing.getVelocity().getYcomp() + " " + thing.getHP() + " " + thing.getSprite() + " " 
						+ thing.getColor().getRed() + " " + thing.getColor().getGreen() + " " + thing.getColor().getBlue() + " ;" ;
				bw.write(line);
				bw.newLine();
			}
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
