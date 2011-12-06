package machine.turing;
import java.io.Serializable;
import java.util.ArrayList;

/** This class represents an edge with it'ss start and end state and it's transitions
 * 
 * @author David Wille
 * 
 */
public class Edge implements Serializable {
	private static final long serialVersionUID = 4600179693147369571L;
	/**
	 * The state where the edge starts from
	 */
	protected State from;
	/**
	 * The state where the edge ends
	 */
	protected State to;
	/**
	 * Contains all transitions of this edge
	 */
	protected ArrayList<Transition> transitions;

	protected int posLabelX = 0;
	protected int posLabelY = 0;
	/**
	 * Constructs an edge from a state to another with it's transitions
	 * @param from Start state
	 * @param to End state
	 * @param transitions Transitions for this edge
	 */
	public Edge(State from, State to, ArrayList<Transition> transitions) {
		this.from = from;
		this.to = to;
		this.transitions = transitions;
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
	public ArrayList<Transition> getTransitions() {
		return this.transitions;
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
	 * @param transitions Transitions for this edge
	 */ //FIXME: is this method necessary?
	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	/**
	 * Gives a string representation of the edge
	 */
	@Override
	public String toString() {
		String transitionsString = "";
		for (int i = 0 ; i < transitions.size(); i++) {
			transitionsString += transitions.get(i);
			if (i < transitions.size()-1) {
				transitionsString += "\n";
			}
		}
		return transitionsString;
	}
	
	public void setPosLabelX(int x) {
		this.posLabelX = x;
	}
	
	public void setPosLabelY(int y) {
		this.posLabelY = y;
	}
	
	public int getPosLabelX() {
		return this.posLabelX;
	}
	
	public int getPosLabelY() {
		return this.posLabelY;
	}
	

}
