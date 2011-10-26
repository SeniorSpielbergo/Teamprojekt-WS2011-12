import java.lang.String;

public class Test {

	public static void main(String argv[]) {
		MachineNode n0 = new MachineNode(0, "q0");
		MachineNode[] nodes = {n0};
		String[] read = {"a","b","c"};
		Edge e0 = new Edge(0, 0, 1, read, "#");
		Edge[] edges = {e0};
		String machineName = "test";
		
		InOut.writeXMLtoFile("test.xml", machineName, nodes, edges);
		InOut.readXMLFromFile("example.xml");
	}
	
}
