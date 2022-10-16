package mapBuilder;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import gameObjects.Ball;
import gameObjects.GameObject;
import gameObjects.Velocity;

/**
 * Class that loads in DAP Map files
 * @author Dan
 *
 */
public class MapLoader {
	/**
	 * Return an array of GameObjects given a string representing a map name
	 * @param mapID
	 * @return
	 */
	public static ArrayList<GameObject> loadMap(String mapID) {
		ArrayList<GameObject> objData = new ArrayList<>();
		BufferedReader br;
		String line;
		Ball bird;
		
		
		File mapFile = new File("mapFolder", mapID + ".mlv");
		if(!mapFile.exists()) {
			System.out.println("Map with given ID does not exist");
			return null;
		}
		try {
			br = new BufferedReader(new FileReader(mapFile));
			//reads header "[Map Name]:"
			line = br.readLine();
			//reads map name
			line = br.readLine();
			//reads header "[Object]:"
			line = br.readLine();
			line = br.readLine();
			while((line !=null) && (!line.equals(""))) {
				System.out.println(line);
				String[] attributes = line.split("\s+");
				String type = attributes[0];
				double startX = Double.parseDouble(attributes[1]);
				double startY = Double.parseDouble(attributes[2]);
				double width = Double.parseDouble(attributes[3]);
				double height = Double.parseDouble(attributes[4]);
				double mass = Double.parseDouble(attributes[5]);
				//order for object attributes: objType posX posY width height mass velocityX velocityY HP spritePath ColorR G B
				Velocity velocity = new Velocity(Double.parseDouble(attributes[6]), Double.parseDouble(attributes[7]));
				double HP = Double.parseDouble(attributes[8]);
				String spritePath = attributes[9];
				//String type = attributes[10];
				
				int r = Integer.parseInt(attributes[10]);
				int g = Integer.parseInt(attributes[11]);
				int b = Integer.parseInt(attributes[12]);
				Color color = new Color(r,g,b);
				
				GameObject obj = new GameObject(startX, startY, width, height, mass);
				obj.setVelocity(velocity);
				obj.setHP(HP);
				obj.setSprite(spritePath);
				obj.setObjType(type);
				obj.setColor(color);
				
				if(obj.getObjType().equals("Ball")) {
					bird = new Ball(startX, startY, width, height, mass, spritePath, width/2, 0.0 , 0.0);
					
					bird.setColor(color);
					objData.add(bird);
				}
				else {
					objData.add(obj);
				}
				
				
				line = br.readLine();
				
			}
			br.close();
			return objData;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("whoops, something went wrong with loading the map");
		return null;
		
	}
	
}
