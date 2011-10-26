import java.io.File;
import java.lang.String;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InOut {
	
	public static void writeXMLtoFile(String fileName, String machineName, String nodeName, String transition, String read[], String write) {
		try {
			try {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();
				DOMSource source = new DOMSource(doc);
				String readString = "";
		
				// create root element
				Element rootElement = doc.createElement("machine");
				doc.appendChild(rootElement);
		
				// save name of machine
				Attr attr = doc.createAttribute("name");
				attr.setValue(machineName);
				rootElement.setAttributeNode(attr);
		
				// node element
				Element node = doc.createElement("node");
				rootElement.appendChild(node);
		
				// node name element
				Element nameElement = doc.createElement("name");
				nameElement.appendChild(doc.createTextNode(nodeName));
				node.appendChild(nameElement);
		
				// node transition element
				Element transitionElement = doc.createElement("transition");
				transitionElement.appendChild(doc.createTextNode(transition));
				node.appendChild(transitionElement);
		
				// node read element
				if (read.length > 0) {
					for (int i = 0; i < read.length; i++) {
						readString = readString + read[i];
						if (i < read.length-1) {
							readString = readString + ",";
						}
					}
					Element readElement = doc.createElement("read");
					readElement.appendChild(doc.createTextNode(readString));
					node.appendChild(readElement);
			
					// node write element
					Element writeElement = doc.createElement("write");
					writeElement.appendChild(doc.createTextNode(write));
					node.appendChild(writeElement);
				}
				
				// write the content into xml file
				if (!(new File(fileName)).exists()) {
					StreamResult result = new StreamResult(new File(fileName));
					transformer.transform(source, result);
					System.out.println("Done writing file!\n");
				}
				else {
					System.out.println("File already exists!\n");
				}
			}
			catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			}
		}
		catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	public static void readXMLFromFile(String fileName) {
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
