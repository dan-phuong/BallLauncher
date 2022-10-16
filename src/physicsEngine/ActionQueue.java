package physicsEngine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gameObjects.GameObject;
/**
 * The AcitonQueue uses ObjectUpdate instances to create a List of actions that can be executed.
 * Follows the Command design pattern in association with ObjectUpdate and its subclasses.
 * @author Dan
 *
 */
public class ActionQueue 
{
	private LinkedList<ObjectUpdate> queue;
	
	/**
	 * Constructor for ActionQueue
	 */
	public ActionQueue()
	{
		queue = new LinkedList<ObjectUpdate>();
	}
	
	/**
	 * Adds an action to the queue
	 * @param o the ObjectUpdate to be added to queue
	 */
	public void addAction(ObjectUpdate o)
	{
		queue.addFirst(o);
	}
	
	/**
	 * Gets the next Action that will be performed.
	 * @return an ObjectUpdate that will perform its action next.
	 */
	public ObjectUpdate nextUpdater()
	{
		return queue.peek();
	}
	
	/**
	 * Runs the action of the next ObjectUpdate in queue
	 * @return an updated GameObject
	 */
	public GameObject performAction()
	{
		return queue.pop().update();
	}
	
	/**
	 * gets the size of the queue
	 * @return how many actions are in queue
	 */
	public int size()
	{
		return queue.size();
	}
	
	
}
