package physicsEngine;

import gameObjects.GameObject;

/**
 * Subclass of ObjectUpdate to be implemented by ActionQueue
 * @author Dan
 *
 */
public class PositionUpdater extends ObjectUpdate{

	/**
	 * Constructor for a POsitionUpdater
	 * @param object the object to be updated
	 */
	public PositionUpdater(GameObject object)
	{
		super(object);
	}
	
	@Override
	/**
	 * Updates the position of object
	 * @return the updated GameObject
	 */
	public GameObject update() {
		// TODO Auto-generated method stub
		this.getReference().changeCenterWithVelocity(this.getReference().getVelocity());
		return this.getReference();
	}

}
