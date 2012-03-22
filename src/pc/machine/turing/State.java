package machine.turing;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

/** 
 * This class represents a state with its id, name, type and edges.
 * @author David Wille
 * @author Sven Schuster
 */
public class State implements Serializable {
	private static final long serialVersionUID = -677700196207258408L;
	private String id;
	private String name;
	private boolean startState;
	private boolean finalState;
	private ArrayList<Edge> edge;
	private int xcoord, ycoord, height, width;
	
	/**
	 * Constructs a state with its id, name and type.
	 * @param id State Id
	 * @param name State name
	 * @param type Type of state
	 */
	public State(String id, String name, boolean startState, boolean finalState) {
		this.id = id;
		this.name = name;
		this.startState = startState;
		this.finalState = finalState;
		this.edge = new ArrayList<Edge>();
	}
	
	/**
	 * Returns the state id.
	 * @return state id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Returns the state name.
	 * @return state name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the state name.
	 * @param name State name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the edges starting from this state.
	 * @return Edges starting at this edge  
	 */
	public ArrayList<Edge> getEdge() {
		return this.edge;
	}
	
	/**
	 * Sets the edges starting at this state.
	 * @param edge Edges starting at this state  
	 */
	public void setEdge(ArrayList<Edge> edge) {
		this.edge = edge;
	}
		
	
	/**
	 * Returns if this state is start state.
	 * @return  true if this state is start state
	 */
	public boolean isStartState() {
		return startState;
	}
	/**
	 * Sets the start state. 
	 * @param startState true if state is startState 
	 */
	public void setStartState(boolean startState) {
		this.startState = startState;
	}
	/**
	 * Returns if this state is final state.
	 * @return  true if this state is final state
	 */
	public boolean isFinalState() {
		return finalState;
	}
	/**
	 * Sets the finalstate.
	 * @param finalState true if state is final State 
	 */
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}
	
	/**
	 * Returns the x coordinate.
	 * @return x
	 */
	public int getXcoord() {
		return xcoord;
	}
	
	/**
	 * Sets the x coordinate.
	 * @param x
	 */
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}

	/**
	 * Returns the y coordinate.
	 * @return y
	 */
	public int getYcoord() {
		return ycoord;
	}

	/**
	 * Sets the y coordinate.
	 * @param y
	 */
	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}

	/**
	 * Returns the height.
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 * @return height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns the width.
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 * @return width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return name;
	}
	@Override
	public Object clone(){
		State tmp = new State(new String(this.getId()), new String(this.getName()),this.isStartState(),this.isFinalState());
		tmp.setHeight(this.getHeight());
		tmp.setWidth(this.getWidth());
		tmp.setXcoord(this.getXcoord());
		tmp.setYcoord(this.getYcoord());
		return tmp;
	}
}