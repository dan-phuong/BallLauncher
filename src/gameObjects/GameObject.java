package gameObjects;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import physicsEngine.ActionQueue;
import physicsEngine.PhysicsEngine;


/**
 * Class representing objects in-game
 * @author Dan
 *
 */
public class GameObject {

	protected double height, width, HP, startX, startY;
	protected String spritePath;
	protected Velocity velocity;
	protected Position position;
	public Position startingPosition;
	protected double mass;
	protected Color color;
	protected String objType;
	private Position centerPoint;
	private PhysicsEngine physicsObserver;

	/**
	 * Constructor for the GameObject Class
	 * @param startX Double for starting position
	 * @param startY Double for starting position
	 * @param width double
	 * @param height double
	 * @param mass double
	 */
	public GameObject(double startX, double startY, double width, double height, double mass) {
		this.startX = startX;
		this.startY = startY;
		this.height = height;
		this.width = width;
		this.startingPosition = new Position(startX, startY);
		this.position = new Position(startX, startY);
		this.mass = mass;
		double cx = startX + width/2;
		double cy = startY + height/2;
		velocity = new Velocity(0,0);
		HP = 100;
		spritePath = "none";
		color = Color.GRAY;
		objType = "Block";
		this.centerPoint = new Position(cx, cy);
			
	}
	
	/**
	 * 
	 * @return double value of GameObject's x position
	 */
	public double getX() {
		return startX;
	}
	
	/**
	 * 
	 * @return double value of GameObject's y position
	 */
	public double getY() {
		return startY;
	}
	
	/**
	 * Sets starting value of x to given value
	 * @param startX
	 */
	public void setX(double startX) {
		this.startX = startX;
	}
	
	/**
	 * Sets starting vaule of y to given value
	 * @param startY
	 */
	public void setY(double startY) {
		this.startY = startY;
	}
	
	/**
	 * Sets GameObject's position to a given position. Also changes this.centerPoint
	 * @param position
	 */
	public void setPosition(Position position) {
		this.position = position;
		double cX = position.getX() + width/2;
		double cY = position.getY() +height/2;
		this.centerPoint = new Position(cX,cY);
	}
	
	/**
	 * Set GameObject's position to a new position with the given coordinates
	 * @param x Position component
	 * @param y Position component
	 */
	public void setPosition(double x, double y) {
		Position newPosition = new Position(x, y);
		this.position = newPosition;
		
		double cX = position.getX() + width/2;
		double cY = position.getY() +height/2;
		this.centerPoint = new Position(cX,cY);
	}
	
	/**
	 * Sets GameObject's velocity to given velocity
	 * @param velocity
	 */
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Sets GameObject's velocity to a new velocity with given components
	 * @param newXVels
	 * @param newYVels
	 */
	public void setVelocity(double newXVels, double newYVels) {
		this.velocity = new Velocity(newXVels,newYVels);
		
	}
	
	/**
	 * Sets the GameObject's hitpoints to a certain double
	 * @param hP2 double
	 */
	public void setHP(double hP2) {
		this.HP = hP2;
	}
	
	/**
	 * Returns GameObject's hitpoints
	 * @return
	 */
	public double getHP() {
		return this.HP;
	}
	
	/**
	 * Changes GameObject's hitpoints
	 * @param change
	 */
	public void changeHP(int change) {
		HP = HP = change;
	}
	
	/**
	 * 
	 * @return this.position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * 
	 * @return this.velocity
	 */
	public Velocity getVelocity() {
		return velocity;
	}
	
	/**
	 * 
	 * @return this.width double
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return this.height double
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Sets this GameObject's centerpoint
	 * @param position Position
	 */
	public void setCenter(Position position) {
		this.centerPoint = position;
		double xpos = centerPoint.getX() - this.width/2;
		double ypos = centerPoint.getY() - this.height/2;
	}
	
	/**
	 * Sets this GameObject's center point with given doubles
	 * @param x double
	 * @param y double
	 */
	public void setCenter(double x, double y) {
		this.centerPoint = new Position(x,y);
		double xpos = centerPoint.getX() - this.width/2;
		double ypos = centerPoint.getY() - this.height/2;
		this.position = new Position(xpos, ypos);
	}
	
	/**
	 * 
	 * @return Position this.center
	 */
	public Position getCenter() {
		return centerPoint;
	}
	
	/**
	 * 
	 * @return String this.spritePath
	 */
	public String getSprite() {
		return spritePath;
	}
	
	/**
	 * Sets string value of GameObject's spritePath
	 * @param sprite String
	 */
	public void setSprite(String sprite) {
		this.spritePath = sprite;
	}
	
	/**
	 * Adds a Physics Engine to this Object
	 * @param e Physics Engine
	 */
	public void addPhysicsObserver(PhysicsEngine e)
	{
		physicsObserver = e;
	}
	
	/**
	 * Notifies GameObject's physicsObserver of a given action
	 * @param actions
	 */
	public void notifyPhysics(ActionQueue actions)
	{
		physicsObserver.update(this, actions);
	}
	
	/**
	 * 
	 * @return double this.mass
	 */
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Sets GameObject to Color
	 * @param color Color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return Color this.color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Returns GameObject's block type
	 * @return String this.objType
	 */
	public String getObjType() {
		return objType;
	}
	
	/**
	 * Sets GameObject's objType to a given string
	 * @param type
	 */
	public void setObjType(String type) {
		this.objType = type;
	}
	
	/**
	 * Checks if this and another object are colliding
	 * @param a GameObject
	 * @return boolean
	 */
	public boolean checkIsCollision(GameObject a)
	{
		Rectangle rect1 = new Rectangle((int) a.getPosition().getX(), (int)a.getPosition().getY(),(int)a.getWidth(),(int)a.getHeight());
		Rectangle rect2 = new Rectangle((int) this.getPosition().getX(),(int)this.getPosition().getY(),(int)this.getWidth(),(int)this.getHeight());
		if(rect1.intersects(rect2))
		{
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Updates this.position
	 */
	public void updatePosition()
	{
		this.setX(this.getX()+this.getVelocity().getXcomp());
		this.setY(this.getY()+this.getVelocity().getYcomp());
		
	}
	
	/**
	 * Changes center values by given doubles
	 * @param x
	 * @param y
	 */
	public void changeCenterBy(double x, double y) {
		this.centerPoint = new Position(this.centerPoint.getX()+x, this.centerPoint.getY() + y);
		this.position = new Position(this.position.getX()+x, this.position.getY() + y);
	}
	
	/**
	 * changes center point value by taking in input velocity
	 * @param v
	 */
	public void changeCenterWithVelocity(Velocity v) {
		this.centerPoint = new Position(this.centerPoint.getX()+v.getXcomp(), this.centerPoint.getY() + v.getYcomp());
		this.position = new Position(this.position.getX()+v.getXcomp(), this.position.getY() + v.getYcomp());
	}
	
	
	
	
}
