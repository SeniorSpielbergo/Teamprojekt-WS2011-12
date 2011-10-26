import java.io.File;
import java.lang.String;
import java.util.ArrayList;

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
	
	public static void writeXMLtoFile(String fileName, String machineName, Graph graph) {
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
				
				// get nodes and edges from graph
				ArrayList<MachineNode> nodes = graph.getNodes();
				ArrayList<Edge> edges = graph.getEdges();
				
				// nodes
				for(int i = 0; i < nodes.size(); i++) {
					MachineNode tempNode = nodes.get(i);
					// node element
					Element node = doc.createElement("node");
					rootElement.appendChild(node);
					
					// node id element
					Element nodeIdElement = doc.createElement("id");
					nodeIdElement.appendChild(doc.createTextNode(tempNode.getId()));
					node.appendChild(nodeIdElement);
		
					// node name element
					Element nameElement = doc.createElement("name");
					nameElement.appendChild(doc.createTextNode(tempNode.getName()));
					node.appendChild(nameElement);
				}
				
				// edges
				for(int i = 0; i < edges.size(); i++) {
					Edge tempEdge = edges.get(i);
					// edge element
					Element edge = doc.createElement("edge");
					rootElement.appendChild(edge);
					
					// edge id element
					Element edgeIdElement = doc.createElement("id");
					edgeIdElement.appendChild(doc.createTextNode(tempEdge.getId()));
					edge.appendChild(edgeIdElement);
		
					// edge from element
					Element fromElement = doc.createElement("from");
					fromElement.appendChild(doc.createTextNode(tempEdge.getFrom()));
					edge.appendChild(fromElement);
		
					// edge to element
					Element toElement = doc.createElement("to");
					toElement.appendChild(doc.createTextNode(tempEdge.getTo()));
					edge.appendChild(toElement);
					
					// edge read elements
					String readString = "";
					ArrayList<String> read = tempEdge.getRead();
					for (int j = 0; j < read.size(); j++) {
						readString = readString + read.get(j);
						if (j < read.size()-1) {
							readString = readString + ",";
						}
					}
					Element readElement = doc.createElement("read");
					readElement.appendChild(doc.createTextNode(readString));
					edge.appendChild(readElement);
			
					// edge write element
					Element writeElement = doc.createElement("write");
					writeElement.appendChild(doc.createTextNode(tempEdge.getWrite()));
					edge.appendChild(writeElement);
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
	
	public static Graph readXMLFromFile(String fileName) {
		Graph graph = null;
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
			ArrayList<MachineNode> nodes = new ArrayList<MachineNode>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node currentNode = nodeList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					String id = getTagValue("id", currentElement);
					String name = getTagValue("name", currentElement);
					MachineNode tempNode = new MachineNode(id, name);
					
					System.out.println("\n== Node " + id + " ==\n");
					System.out.println("id: " + id);
					System.out.println("name: " + name);
					nodes.add(tempNode);
				}
			}
			
			// get list of edges
			NodeList edgeList = doc.getElementsByTagName("edge");
			System.out.println("\nEDGES");
			ArrayList<Edge> edges = new ArrayList<Edge>();
			for (int i = 0; i < edgeList.getLength(); i++) {
				Node currentNode = edgeList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					String id = getTagValue("id", currentElement);
					String from = getTagValue("from", currentElement);
					String to = getTagValue("to", currentElement);
					String write = getTagValue("write", currentElement);
					String[] readString = getTagValue("read", currentElement).split(",");
					ArrayList<String> read = new ArrayList<String>();
					for(int j = 0; j < readString.length; j++) {
						read.add(readString[j]);
						System.out.println("read" + j + ": " + readString[j]);
					}
					Edge tempEdge = new Edge(id, from, to, read, write);
					
					System.out.println("\n== Edge " + id + " ==\n");
					System.out.println("id: " + id);
					System.out.println("from: " + from);
					System.out.println("to: " + to);
					System.out.println("write: " + write);
					edges.add(tempEdge);
				}
			}
			graph = new Graph(nodes, edges);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}
	
	private static String getTagValue(String tag, Element currentElement) {
		NodeList nodeList = currentElement.getElementsByTagName(tag).item(0).getChildNodes();

		Node nodeValue = (Node) nodeList.item(0);

		return nodeValue.getNodeValue();
	}

}
