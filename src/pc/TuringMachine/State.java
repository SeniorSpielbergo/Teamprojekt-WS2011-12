package TuringMachine;
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
		START, NORMAL, FINAL
	}

	protected String id;
	protected String name;
	protected Type type;
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
	 * Returns the state type
	 * @return State type
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * Returns the edges starting from this state
	 * @return Edges starting at this edge
	 */
	public ArrayList<Edge> getEdge() {
		return this.edge;
	}
	
	/**
	 * Sets the state name
	 * @param name State name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the state type
	 * @param type State type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * Sets the edges starting at this state
	 * @param edge Edges starting at this state
	 */
	public void setEdge(ArrayList<Edge> edge) {
		this.edge = edge;
	}
	
	// TODO javadoc
	public boolean equals(String type){
		return this.type.equals(type);
	}
}
