import java.lang.String;
import java.util.ArrayList;

public class Graph {

	protected ArrayList<MachineNode> nodes;
	protected ArrayList<Edge> edges;
	
	public Graph(ArrayList<MachineNode> nodes, ArrayList<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public ArrayList<MachineNode> getNodes() {
		return this.nodes;
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

}
