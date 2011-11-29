package machine.turing;
import java.lang.String;
import java.util.ArrayList;

/** This class represents a state with it's id, name, type and edges
 * 
 * @author David Wille
 * 
 */
public class State {
	/**
	 * The state id string for internal use
	 */
	protected String id;
	/**
	 * The name of the state (only used to display to the user)
	 */
	protected String name;
	/**
	 * The state type
	 */
	protected boolean startState; // FIXME: States can be start and final at the same time 
	
	protected boolean finalState;
	/**
	 * Contains all edges starting at this state //TODO: review if it is better to search the edges dynamically
	 */
	protected ArrayList<Edge> edge;
	
	/**
	 * Constructs a state with it's id, name and type
	 * @param id State Id
	 * @param name State name
	 * @param type Type of state
	 */
	
	protected double xcoord, ycoord, height, width;
	
	
	public State(String id, String name, boolean startState, boolean finalState) {
		this.id = id;
		this.name = name;
		this.startState = startState;
		this.finalState = finalState;
		this.edge = new ArrayList<Edge>();
	}
	
	/**
	 * Returns the state Id
	 * @return State Id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Returns the state name
	 * @return State name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the state name
	 * @param name State name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the edges starting from this state
	 * @return Edges starting at this edge  //TODO: review if it is better to search the edges dynamically
	 */
	public ArrayList<Edge> getEdge() {
		return this.edge;
	}
	
	/**
	 * Sets the edges starting at this state
	 * @param edge Edges starting at this state  //TODO: review if it is better to search the edges dynamically
	 */
	public void setEdge(ArrayList<Edge> edge) {
		this.edge = edge;
	}
		
	
	/**
	 * Returns if this state is startstate
	 * @return  true if this state is startstate
	 */
	public boolean isStartState() {
		return startState;
	}
	/**
	 * Sets the startstate 
	 * @param true true if state is startState 
	 */
	public void setStartState(boolean startState) {
		this.startState = startState;
	}
	/**
	 * Returns if this state is finalstate
	 * @return  true if this state is finalstate
	 */
	public boolean isFinalState() {
		return finalState;
	}
	/**
	 * Sets the finalstate
	 * @param true true if state is finalState 
	 */
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}
	
	public double getXcoord() {
		return xcoord;
	}
	public void setXcoord(double xcoord) {
		this.xcoord = xcoord;
	}

	public double getYcoord() {
		return ycoord;
	}

	public void setYcoord(double ycoord) {
		this.ycoord = ycoord;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Gives a string representation of the State
	 */
	@Override
	public String toString() {
		return name;
	}
}
