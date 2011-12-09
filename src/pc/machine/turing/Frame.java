package machine.turing;

import java.io.Serializable;

public class Frame implements Serializable {
	private static final long serialVersionUID = 3300792709541871175L;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Frame() {}
	
	public Frame(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return "";
	}
}
