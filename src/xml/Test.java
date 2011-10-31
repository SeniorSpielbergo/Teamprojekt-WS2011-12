import java.lang.String;
import java.util.ArrayList;

public class Test {

	public static void main(String argv[]) {
		State n0 = new State("0", "q0");
		ArrayList<State> states = new ArrayList<State>();
		states.add(n0);
		ArrayList<String> read = new ArrayList<String>();
		read.add("a");
		read.add("b");
		read.add("c");
		Edge e0 = new Edge("0", "0", "1", read, "#");
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(e0);
		Graph graph = new Graph(states, edges);
		String machineName = "test";
		
		InOut.writeXMLtoFile("test.xml", machineName, graph);
		InOut.readXMLFromFile("example.xml");
	}
	
}
