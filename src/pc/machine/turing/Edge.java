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
	 * The list of via points
	 */
	protected ArrayList<Point> via;
	/**
	 * Contains all transitions of this edge
	 */
	protected ArrayList<Transition> transitions;
	
	/**
	 * The label x position
	 */
	protected int posLabelX = 0;
	/**
	 * The label y position
	 */
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
		this.via = new ArrayList<Point>();
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
	 * Gives a string representation of the edge
	 */
	@Override
	public String toString() {
		String transitionsString = "";
		int numberOfTransitions = (transitions.size() <= 3 ? transitions.size() : 3); //print up to 4 transitions
		for (int i = 0 ; i < numberOfTransitions; i++) {
			transitionsString += transitions.get(i);
			if (i < numberOfTransitions-1) {
				transitionsString += "\n";
			}
		}
		if (transitions.size() > numberOfTransitions) { //print ellipsis if more transitions exist
			transitionsString += "\n...";
		}
		return transitionsString;
	}
	
	/**
	 * Sets the label x position
	 * @param x The x position
	 */
	public void setPosLabelX(int x) {
		this.posLabelX = x;
	}
	
	/**
	 * Sets the label y position
	 * @param y The y position
	 */
	public void setPosLabelY(int y) {
		this.posLabelY = y;
	}
	
	/**
	 * Returns the current label position
	 * @return The point where the label is positioned
	 */
	public Point getPosLabel() {
		return new Point(this.posLabelX, this.posLabelY);
	}

	/**
	 * Returns the list of via points for this edge
	 * @return List of vias
	 */
	public ArrayList<Point> getVia() {
		return via;
	}
	
	@Override
	public Object clone() {
		return new Edge((State) this.from.clone(), 
				(State) this.to.clone(), 
				(ArrayList<Transition>) this.transitions.clone());
	}
}
