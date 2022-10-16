package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import gameEngine.GameEngine;
import gameObjects.Ball;
import gameObjects.GameObject;
import mapBuilder.MapLoader;

import java.awt.event.*;

/**
 * Class where that calls drawing methods and does other graphical calculations for the GameGui class.
 * Implements MouseListeners and the Runnable interfaces, MouseListener allows Game to recieve user
 * input from their mouse, and the Runnable interface allows for this class to run on a separate thread.
 * @author Dan
 *
 */
public class Game extends JPanel implements MouseListener, MouseMotionListener, Runnable{
    private DrawCanvas canvas;
    private int canvasWidth, canvasHeight;
    public Ball ball;
    double mouseX, mouseY, speed, angle;
    boolean mouseClickedBall;
    Level level_used;
    private ArrayList<GameObject> gamestuff;
    private int clickedX, clickedY;
    private boolean dragBall;
    private int startingbirdX, startingbirdY;
    private boolean automode;
    /**
     * Constructor for the Game class. Takes in a width and a height which is used to define how
     * large the panel is.
     * @param width
     * @param height
     */
    public Game(int width, int height) {
    	Thread.currentThread().setName("GameDrawThread");
    	gamestuff = MapLoader.loadMap("Level_1");
    	dragBall = false;
    	automode = false;
        canvasHeight = height; 
        canvas = new DrawCanvas();
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        level_used = new Level(0, 0, canvasWidth, canvasHeight, gamestuff);
        ball = (Ball)gamestuff.get(0);
        startingbirdX = (int) ball.getCenter().getX();
        startingbirdY = (int) ball.getCenter().getY();
    }
    /**
     * A private class that uses the paintComponent function to invoke Level's draw method.
     * Has logic to draw a guiding line that can be used to help the players during gameplay.
     * @author bryan
     *
     */
    public class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            level_used.draw(g);
            if (mouseClickedBall) {
            	int mx = (int) ball.getCenter().getX();
            	int my = (int) ball.getCenter().getY();
                g.drawLine(mx, my, (int) mouseX, (int) mouseY); 
            }
        }
    }
    
    private boolean mouseBallDetection(MouseEvent drag, Ball ball) {
    	System.out.println("Ball center" + ball.getCenter().getX() + " " + ball.getCenter().getY());
    	
    	boolean result = ((drag.getX() >= ball.getCenter().getX() - ball.radius && drag.getX() <= ball.getCenter().getX() + ball.radius)
    			&& (drag.getY() >= ball.getCenter().getY() - ball.radius && drag.getY() <= ball.getCenter().getY() + ball.radius));
    	return result;
    }
	@Override
	public void mouseDragged(MouseEvent event) {
        if (mouseBallDetection(event, ball)) {
        	System.out.println("dragging");
            mouseClickedBall = true;
        }

        if (mouseClickedBall) {
            speed = ((ball.getX() - event.getX()) * (ball.getX() - event.getX()) + (ball.getY() - event.getY()) * (ball.getY() - event.getY())) * 0.0003;
            angle = Math.toDegrees(Math.atan2(ball.getX() - event.getX(), ball.getY() - event.getY())) - 90;
            System.out.println("Ball Speed: "+speed);
            System.out.println("Ball Angle: "+angle+"\n");
            mouseX = event.getX();
            mouseY = event.getY();
            mouseClickedBall = true;
        }		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {	
	}
	@Override
	public void mouseClicked(MouseEvent e) {	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
        if (mouseClickedBall) {
            ball.setSpeedAndAngle(speed, angle);
            mouseClickedBall = false;
            System.out.println("print start");
        }
        dragBall = false;
        automode = true;
        mouseClickedBall = false;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(60);
				if(automode) {
					GameEngine.getEngine().runSimulation();
                    ball.gravity(0.99);
				}
				canvas.repaint();
				
			} catch (InterruptedException e) {
				e.printStackTrace();	}
					}
		}
	
	/**
	 * 
	 * @return Level this game uses
	 */
	public Level getLevel()
	{
		return level_used;
	}
}
