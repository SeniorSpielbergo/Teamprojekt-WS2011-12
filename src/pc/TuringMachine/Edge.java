package TuringMachine;
import java.util.ArrayList;

/** This class represents an edge with it'ss start and end state and it's transitions
 * 
 * @author David Wille
 * 
 */
public class Edge {

	protected State from;
	protected State to;
	protected ArrayList<Transition> transition;
	
	/**
	 * Constructs an edge from a state to another with it's transitions
	 * @param from Start state
	 * @param to End state
	 * @param transition Transitions for this edge
	 */
	public Edge(State from, State to, ArrayList<Transition> transition) {
		this.from = from;
		this.to = to;
		this.transition = transition;
	}
	
	/**
	 * Returns the edge's start state
	 * @return Start state for this edge
	 */
	public State getFrom() {
		return this.from;
	}
	
	/**
	 * Returns the edge's end state
	 * @return End state for this edge
	 */
	public State getTo() {
		return this.to;
	}
	
	/**
	 * Returns the transitions for this edge
	 * @return Transitions for this edge
	 */
	public ArrayList<Transition> getTransition() {
		return this.transition;
	}
	
	/**
	 * Sets the start state
	 * @param from Start state
	 */
	public void setFrom(State from) {
		this.from = from;
	}
	
	/**
	 * Sets the end state
	 * @param to End state
	 */
	public void setTo(State to) {
		this.to = to;
	}
	
	/**
	 * Sets the transitions for this edge
	 * @param transition Transitions for this edge
	 */
	public void setTransition(ArrayList<Transition> transition) {
		this.transition = transition;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", transition="
				+ transition + "]\n";
	}
	
	

}
