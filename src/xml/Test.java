import java.lang.String;
import java.util.ArrayList;

public class Test {

	public static void main(String argv[]) {
		MachineNode n0 = new MachineNode("0", "q0");
		ArrayList<MachineNode> nodes = new ArrayList<MachineNode>();
		nodes.add(n0);
		ArrayList<String> read = new ArrayList<String>();
		read.add("a");
		read.add("b");
		read.add("c");
		Edge e0 = new Edge("0", "0", "1", read, "#");
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(e0);
		Graph graph = new Graph(nodes, edges);
		String machineName = "test";
		
		InOut.writeXMLtoFile("test.xml", machineName, graph);
		InOut.readXMLFromFile("example.xml");
	}
	
}
