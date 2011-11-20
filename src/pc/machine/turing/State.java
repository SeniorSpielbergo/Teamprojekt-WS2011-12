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
	 * Represents the different types of states
	 */
	public enum Type {
		/**
		 * A Start state
		 */
		START, 
		/**
		 * A normal state
		 */
		NORMAL, 
		/**
		 * A final state
		 */
		FINAL
	}

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
	protected Type type;
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
	public State(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
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
	 * Returns the state type
	 * @return State type
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * Sets the state type
	 * @param type State type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * Gives a string representation of the State
	 */
	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
}
