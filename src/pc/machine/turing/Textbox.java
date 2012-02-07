package machine.turing;

import java.io.Serializable;

/**
 * Implements a Textbox for commenting the graph.
 * @author Sven Schuster
 */
public class Textbox implements Serializable {
	private static final long serialVersionUID = 6069216510208599325L;
	
	private String text;
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * Creates a new Textbox with given text.
	 * @param text
	 */
	public Textbox(String text) {
		this.text = text;
	}
	
	/**
	 * Creates a new Textbox with given text and geometry.
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Textbox(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets the text.
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Returns the text.
	 * @return text
	 */
	public String getText() {
		return this.text;
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
		return this.text;
	}
	
	@Override
	public Object clone() {
		return new Textbox(new String(this.text), this.x, this.y, this.width, this.height);
	}
}