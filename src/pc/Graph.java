import java.util.ArrayList;

public class Graph {

	protected ArrayList<State> states;
	protected ArrayList<Edge> edges;
	protected String name;
	protected int tapes;
	
	public Graph(ArrayList<State> states, ArrayList<Edge> edges, String name, int tapes) {
		this.states = states;
		this.edges = edges;
		this.name = name;
		this.tapes = tapes;
	}
	
	public ArrayList<State> getStates() {
		return this.states;
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

}
