import java.lang.String;

public class Test {

	public static void main(String argv[]) {
		String machineName = "test";
		String nodeName = "q0";
		String transition = "q1";
		String read[] = {"a","b","c"};
		String write = "#";
		InOut.writeXMLtoFile("test.xml", machineName, nodeName, transition, read, write);
		
		InOut.readXMLFromFile("example.xml");
	}
	
}
