package physicsEngine;

import gameObjects.GameObject;

/**
 * Abstract class defining ObjectUpdate instances.
 * @author Dan
 *
 */
public abstract class ObjectUpdate 
{
	private GameObject thingToEffect;
	
	/**
	 * Constructor for an ObjectUpdate
	 * @param o the GameObject to be updated
	 */
	public ObjectUpdate(GameObject o)
	{
		thingToEffect = o;
	}
	
	/**
	 * method that will actually update the GameObject
	 * @return The updated GameObject
	 */
	public abstract GameObject update();
	
	/**
	 * gets the object being changed.
	 * @return the thing to effect.
	 */
	public GameObject getReference()
	{
		return thingToEffect;
	}
}
