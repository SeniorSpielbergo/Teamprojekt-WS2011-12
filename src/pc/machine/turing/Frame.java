package machine.turing;

import java.io.Serializable;

/**
 * Implements a frame to display 'subsystems' within the graph.
 * @author Sven Schuster
 */
public class Frame implements Serializable {
	private static final long serialVersionUID = 3300792709541871175L;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	/**
	 * Creates a new frame with given geometry.
	 */	
	public Frame(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the x value. 
	 * @return x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Returns the y value. 
	 * @return y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Returns the width. 
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Returns the height. 
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Sets the x value.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y value.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the width.
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Sets the height.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public Object clone() {
		return new Frame(this.x, this.y, this.width, this.height);
	}
}