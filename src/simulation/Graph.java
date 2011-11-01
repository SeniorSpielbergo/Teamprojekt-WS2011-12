import java.lang.String;
import java.util.ArrayList;

public class Graph {

	protected ArrayList<State> states;
	protected ArrayList<Edge> edges;
	
	public Graph(ArrayList<State> states, ArrayList<Edge> edges) {
		this.states = states;
		this.edges = edges;
	}
	
	public ArrayList<State> getStates() {
		return this.states;
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

}
