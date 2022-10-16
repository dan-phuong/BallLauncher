package gui;
import java.awt.*;
import java.util.ArrayList;

import gameObjects.Ball;
import gameObjects.GameObject;
import mapBuilder.MapLoader;

/**
 * Graphical data that is used by game to represent GameObjects
 * @author Dan
 *
 */
public class Level {
	int minX, maxX, minY, maxY;
	int passedInt;
	
	public ArrayList<GameObject> mapObjects;
//    public int getMinX() {
//        return minX;
//    }
//
//    public int getMaxX() {
//        return maxX;
//    }
//
//    public int getMinY() {
//        return minY;
//    }
//
//    public int getMaxY() {
//        return maxY;
//    }
	/**
	 * Constructor for this class
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param gamestuff
	 */
    public Level(int x, int y, int width, int height, ArrayList<GameObject> gamestuff) {
        minX = x;
        minY = y;
        maxX = x + width;
        maxY = y + height;
        mapObjects = gamestuff;
        
    }
    /**
     * Sets dimensions
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void set(int x, int y, int width, int height) {
        minX = x;
        minY = y;
        //questions for the -1
        maxX = x + width;
        maxY = y + height;
    }
    
    /**
     * Method that draws the gameobjects given
     * 
     * @param g
     */
    public void draw(Graphics g) {
    	Color dGreen = new Color(46,101,66);
    	Color skyBlue = new Color(130,196,228);
    	Color brownTree = new Color(117,85,56);
    	//blue sky background
    	g.setColor(skyBlue);
    	g.fillRect(0,0, 1350, 600);
    	//green ground
        g.setColor(dGreen);
        g.drawRect(0, 550, 1350, 50);
        g.fillRect(0, 550, 1350, 50);
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, 100, 100);
        //launching platform
        g.setColor(brownTree);
        g.fillRect(250, 400, 50, 50);
        g.fillRect(260, 400, 30, 150);
        g.fillRect(250, 380, 10, 30);
        g.fillRect(290, 380, 10, 30);
        //this chunk of code draws objects on the map
        if(mapObjects != null) {
        	for(GameObject o: mapObjects) {
        		//g.setColor(o.getColor());
        		int xpos = (int) Math.round(o.getPosition().getX());
        		
        		
        		int ypos = (int) Math.round(o.getPosition().getY());
        		int owidth = (int) Math.round(o.getWidth());
        		int oheight = (int) Math.round(o.getHeight());
        		if(o.getObjType().equals("Ball")) {
        			g.setColor(o.getColor());
        			g.fillOval(xpos, ypos, owidth, oheight);
        			g.setColor(Color.black);
        			g.drawOval(xpos, ypos, owidth, oheight);
        		}
        		else {
        			g.setColor(o.getColor());
        			g.fillRect(xpos, ypos, owidth, oheight);
            		g.setColor(Color.black);
            		g.drawRect(xpos, ypos, owidth, oheight);
        		}
        		
        	}
        }
        
    }
    
    public ArrayList<GameObject> getObjectList(){
    	return mapObjects;
    }
}
