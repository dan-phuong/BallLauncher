package gameObjects;

/**
 * Class used by game physics and gui to represent an object's x and y values in space.
 * 
 * @author Dan
 *
 */
public class Position {
	private double x;
	private double y;
	
	/**
	 * Constructor for Position class.
	 * 
	 * @param x horizontal value
	 * @param y vertical value
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Updates current Position values to change by given amounts.
	 * 
	 * @param x horizontal change
	 * @param y vertical change
	 */
	public void updatePosition(double dx, double dy) {
		x = x + dx;
		y = y + dy;
	}
	
	public void updateWithVelocity(Velocity v) {
		x = x +v.getXcomp();
		y = y + v.getYcomp();
	}
	
	/**
	 * Returns current Position
	 * 
	 * @return Position
	 */
	public Position getPosition() {
		return this;
	}
	
	/**
	 * Sets Position values to given values
	 * 
	 * @param x horizontal value
	 * @param y vertical value
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	//following methods will probably not be used in real project, just helpers for now
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
