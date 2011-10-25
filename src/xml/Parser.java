import java.io.File;
import java.lang.String;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {

	public static void main(String argv[]) {
		readXMLFromFile("example.xml");
	}
	
	private static void readXMLFromFile(String fileName) {
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			// get list of nodes
			NodeList nodeList = doc.getElementsByTagName("node");
			System.out.println("Turing machine's name: " + doc.getDocumentElement().getAttribute("name"));

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node currentNode = nodeList.item(i);

				System.out.println("\n== Node " + i + " ==\n");

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element currentElement = (Element) currentNode;
					System.out.println("name: " + getTagValue("name", currentElement));
					System.out.println("transition: " + getTagValue("transition", currentElement));
					
					String[] read = getTagValue("read", currentElement).split(",");
					for(int j = 0; j < read.length; j++) {
						System.out.println("read" + j + ": " + read[j]);
					}
					
					System.out.println("write: " + getTagValue("write", currentElement));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getTagValue(String tag, Element currentElement) {
		NodeList nodeList = currentElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nodeValue = (Node) nodeList.item(0);

		return nodeValue.getNodeValue();
	}
}
