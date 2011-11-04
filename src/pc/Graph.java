import java.util.ArrayList;

public class Graph {

	protected ArrayList<State> states;
	protected ArrayList<Edge> edges;
	protected String name;
	protected int tapes;
	protected ArrayList<ArrayList<Character>> initial;
	
	public Graph(ArrayList<State> states, ArrayList<Edge> edges, String name, int tapes, ArrayList<ArrayList<Character>> initial) {
		this.states = states;
		this.edges = edges;
		this.name = name;
		this.tapes = tapes;
		this.initial = initial;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTapes() {
		return this.tapes;
	}
	
	public ArrayList<ArrayList<Character>> getInitial() {
		return this.initial;
	}
	
	public ArrayList<State> getStates() {
		return this.states;
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

}
