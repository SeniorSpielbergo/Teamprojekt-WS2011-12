import java.lang.String;
import java.util.ArrayList;

public class Test {

	public static void main(String argv[]) {
		State n0 = new State("0", "q0", "start");
		ArrayList<State> states = new ArrayList<State>();
		states.add(n0);
		ArrayList<String> read = new ArrayList<String>();
		read.add("0");
		read.add("1");
		read.add("2");
		ArrayList<String> write = new ArrayList<String>();
		write.add("0");
		write.add("1");
		write.add("2");
		ArrayList<String> action = new ArrayList<String>();
		action.add("0");
		action.add("1");
		action.add("2");
		Edge e0 = new Edge("0", "0", "1", read, write, action);
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(e0);
		String machineName = "test";
		//Graph graph = new Graph(states, edges, machineName, 4);
		
		//InOut.writeXMLtoFile("test.xml", machineName, graph);
		InOut.readXMLFromFile("test.xml");
	}
	
}
