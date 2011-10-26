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
	
	public static void writeXMLtoFile(String fileName, String machineName, MachineNode[] nodes, Edge[] edges) {
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
		
				// create root element
				Element rootElement = doc.createElement("machine");
				doc.appendChild(rootElement);
		
				// save name of machine
				Attr attr = doc.createAttribute("name");
				attr.setValue(machineName);
				rootElement.setAttributeNode(attr);
				
				
				// due to problems commented out
				
				// nodes
				/*for(int i = 0; i < nodes.length; i++) {
					// node element
					Element[] node = doc.createElement("node");
					rootElement.appendChild(node);
					
					// node id element
					Element nodeIdElement = doc.createElement("id");
					nodeIdElement.appendChild(doc.createTextNode("" + nodes[i].getId()));
					node.appendChild(nodeIdElement);
		
					// node name element
					Element nameElement = doc.createElement("name");
					nameElement.appendChild(doc.createTextNode(nodes[i].getName()));
					node.appendChild(nameElement);
				}
				
				// edges
				for(int i = 0; i < edges.length; i++) {
					// edge element
					Element edge = doc.createElement("edge");
					rootElement.appendChild(edge);
					
					// edge id element
					Element edgeIdElement = doc.createElement("id");
					edgeIdElement.appendChild(doc.createTextNode("" + edges[i].getId()));
					edge.appendChild(edgeIdElement);
		
					// edge from element
					Element fromElement = doc.createElement("from");
					fromElement.appendChild(doc.createTextNode("" + edges[i].getFrom()));
					edge.appendChild(fromElement);
		
					// edge to element
					Element toElement = doc.createElement("to");
					toElement.appendChild(doc.createTextNode("" + edges[i].getTo()));
					edge.appendChild(toElement);
					
					// edge read elements
					String readString = "";
					String[] read = edges[i].getRead();
					for (int j = 0; j < read.length; j++) {
						readString = readString + read[j];
						if (j < read.length-1) {
							readString = readString + ",";
						}
					}
					Element readElement = doc.createElement("read");
					readElement.appendChild(doc.createTextNode(readString));
					edge.appendChild(readElement);
			
					// edge write element
					Element writeElement = doc.createElement("write");
					writeElement.appendChild(doc.createTextNode(edges[i].getWrite()));
					edge.appendChild(writeElement);
				}*/
				
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
			// print the Turing machine's name
			System.out.println("Turing machine's name: " + doc.getDocumentElement().getAttribute("name"));
			// get list of nodes
			NodeList nodeList = doc.getElementsByTagName("node");
			System.out.println("\nNODES");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node currentNode = nodeList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					System.out.println("\n== Node " + getTagValue("id", currentElement) + " ==\n");
					System.out.println("id: " + getTagValue("id", currentElement));
					System.out.println("name: " + getTagValue("name", currentElement));
				}
			}
			
			// get list of edges
			NodeList edgeList = doc.getElementsByTagName("edge");
			System.out.println("\nEDGES");
			
			for (int i = 0; i < edgeList.getLength(); i++) {
				Node currentNode = edgeList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					String id = getTagValue("id", currentElement);
					System.out.println("\n== Edge " + id + " ==\n");
					System.out.println("id: " + id);
					System.out.println("from: " + getTagValue("from", currentElement));
					System.out.println("to: " + getTagValue("to", currentElement));
					
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
