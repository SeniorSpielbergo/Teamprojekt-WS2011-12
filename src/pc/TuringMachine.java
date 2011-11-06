import java.util.ArrayList;

/** This class represents a Turing machine with it's states, edges and transitions
 * 
 * @author David Wille
 * 
 */
public class TuringMachine {

	protected ArrayList<State> states;
	protected ArrayList<Edge> edges;
	protected String name;
	protected int tapes;
	protected ArrayList<ArrayList<Character>> initial;
	
	/**
	 * Constructs a new Turing machine with edges, states and transitions
	 * @param states All states of the Turing machine
	 * @param edges All edges of the Turing machine
	 * @param name The name of the Turing machine
	 * @param tapes The number of tapes used in the Turing machine
	 * @param initial The initial input on the Turing machine's tapes
	 */
	
	public TuringMachine(ArrayList<State> states, ArrayList<Edge> edges, String name, int tapes, ArrayList<ArrayList<Character>> initial) {
		this.states = states;
		this.edges = edges;
		this.name = name;
		this.tapes = tapes;
		this.initial = initial;
	}
	
	/**
	 * Returns the Turing machine's name
	 * @return The Turing machine's name
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the Turing machine's number of tapes
	 * @return Number of tapes
	 */
	public int getTapes() {
		return this.tapes;
	}
	
	/**
	 * Returns the initial configuration for the Turing machine's tapes
	 * @return Initial configuration for the Turing machine's tapes
	 */
	public ArrayList<ArrayList<Character>> getInitial() {
		return this.initial;
	}
	
	/**
	 * Returns the Turing machine's states
	 * @return Turing Machine's states
	 */
	public ArrayList<State> getStates() {
		return this.states;
	}
	
	/**
	 * Returns the Turing machine's edges
	 * @return Turing Machine's edges
	 */
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

}
