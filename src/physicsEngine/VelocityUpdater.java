package physicsEngine;

import gameObjects.GameObject;
import gameObjects.Velocity;

/**
 *  Subclass of ObjectUpdate to be implemented by ActionQueue

 * @author Dan
 *
 */
public class VelocityUpdater extends ObjectUpdate
{

	
	private Velocity newVel;
	
	/**
	 * Constructor for a VelocityUpdater
	 * @param o the GameObject to be updated
	 * @param v the new velocity
	 */
	public VelocityUpdater(GameObject o, Velocity v)
	{
		super(o);
		newVel = v;
		
	}
	
	@Override
	/**
	 * Updates the position of object
	 * @return the updated GameObject
	 */
	public GameObject update() 
	{
		// TODO Auto-generated method stub
		this.getReference().setVelocity(newVel);
		return this.getReference();
	}

}
