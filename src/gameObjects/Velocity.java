package gameObjects;

/**Class used by game physics logic meant to emulate velocity
 * 
 * @author Dan
 *
 */
public class Velocity {
	private double dx;
	private double dy;
	
	/**
	 * Constructor for Velocity Class.
	 * 
	 * @param dx horizontal value
	 * @param dy vertical value
	 */
	public Velocity(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Updates current Velocity value by the amount input.
	 * 
	 * @param x change to dx
	 * @param y change to dy
	 */
	public void updateVelocity(double x, double y) {
		dx = dx + x;
		dy = dy + y;
	}
	
	/**
	 * Sets velocity values to new x and y values
	 * 
	 * @param x horizontal value
	 * @param y vertical value
	 */
	public void setVelocity(double x, double y) {
		dx = x;
		dy = y;
	}
	
	/**
	 * Returns Velocity
	 * @return Velocity
	 */
	public Velocity getVelocity() {
		return this;
	}
	
	/**
	 * Returns X portion of this Velocity
	 * @return double Velocity X
	 */
	public double getXcomp() {
		return dx;
	}
	
	/**
	 * Returns Y portion of this Velocity
	 * @return double Velocity Y
	 */
	public double getYcomp() {
		return dy;
	}

}
