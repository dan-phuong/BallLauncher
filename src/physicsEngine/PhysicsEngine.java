package physicsEngine;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import gameEngine.GameEngine;
import gameObjects.Ball;
import gameObjects.GameObject;
import gameObjects.Velocity;

/**
 * Observer responsible for detecting collisions and modifying objects' positions and velocities.
 * @author Dan
 *
 */
public class PhysicsEngine 
{

	/**
	 * Constructor
	 */
	public PhysicsEngine()
	{
		
	}
	
	/**
	 * Checks for a given objects collisions, and updates the velocities and positions
	 * of that object.
	 * 
	 * @param obj The GameObject to be checked
	 * @param actions the ActionQueue to store desired changes
	 */
	public void update(GameObject obj, ActionQueue actions)
	{
		ArrayList<GameObject> toCheck = GameEngine.getEngine().getObjects();
		for(GameObject g: toCheck)
		{
			//g.changeCenterBy(1, -1);
			//System.out.println("name: " + g.getObjType());
			if(g.getObjType().equals("Ball")) {
				Ball ball = (Ball)g;
				
				ball.changeCenterBy(ball.getSpeedX(), ball.getSpeedY());
				ball.setVelocity(ball.getSpeedX(), ball.getSpeedY());
				//avil.gravity(1);
			}
			
			if(g==obj)
			{
				
			}
			else if(obj.checkIsCollision(g))
			{
				Velocity newVel = new Velocity(handleXCollision(obj,g), handleYCollision(obj,g));
				System.out.println("velocity numbers: " + newVel.getXcomp() + " "+ newVel.getYcomp());
				g.setVelocity(newVel);
				g.changeCenterWithVelocity(g.getVelocity());
				
				//HandleCollision, find new velocity
				actions.addAction(new VelocityUpdater(obj,newVel));
				//Update velocity
			}
		}
		actions.addAction(new PositionUpdater(obj));
		for(int i = 0; i < actions.size(); i++) {
			actions.performAction();
		}
		//update position
	}
		
	/**
	 * Calculates the final velocity for a 1-D collision between two objects.
	 * 
	 * @param a The first object in the collision
	 * @param b The second object in the collision
	 * @return the final x-velocity of the first object in the collision
	 */
	private double handleXCollision(GameObject a, GameObject b)
	{
		double[] newXVels = newVelocities(a.getVelocity().getXcomp(),b.getVelocity().getXcomp(),a.getMass(),b.getMass());
		//double[] newXVels = newVelocities(a.getVelocity().getXcomp(),b.getVelocity().getXcomp(),40,40);

		return newXVels[0];
		
	}
	
	/**
	 * Calculates the final velocity for a 1-D collision between two objects.
	 * 
	 * @param a The first object in the collision
	 * @param b The second object in the collision
	 * @return the final y-velocity of the first object in the collision
	 */
	private double handleYCollision(GameObject a, GameObject b)
	{
		double[] newYVels = newVelocities(a.getVelocity().getYcomp(),b.getVelocity().getYcomp(),a.getMass(),b.getMass());
		//double[] newYVels = newVelocities(a.getVelocity().getYcomp(),b.getVelocity().getYcomp(),40,40);
		return newYVels[0];
		
	}
	
	/**
	 * The physics equations that calculate a 1-D collision
	 * @param vai object a's initial velocity
	 * @param vbi object b's intitial velocity
	 * @param ma object a's mass
	 * @param mb object b's mass
	 * @return the final x and y velocities of the objects
	 */
	private double[] newVelocities(double vai, double vbi, double ma, double mb)
	{
		/*
		 * This method finds the new velocities formed from an assumed one-dimensional collision
		 * va and vb are imaginary velocities, where the initial velocity of vb is 0
		 * 
		 * 
		 */
		double vci = vai - vbi;
		
		double vdf = 2*(vci)/(mb/ma+1);
		
		double vbf = vdf + vbi;
		
		double vcf = vci - (mb)*(vdf)/(ma);
		
		double vaf = vcf + vbi;
		
		double[] vars = {vaf,vbf};
		return vars;
	}
	
	
}
