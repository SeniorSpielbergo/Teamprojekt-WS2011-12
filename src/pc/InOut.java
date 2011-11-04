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
	
	/*public static void writeXMLtoFile(String fileName, String machineName, Graph graph) {
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
				
				// get states and edges from graph
				ArrayList<State> states = graph.getStates();
				ArrayList<Edge> edges = graph.getEdges();
				
				// states
				for(int i = 0; i < states.size(); i++) {
					State tempState = states.get(i);
					// state element
					Element state = doc.createElement("state");
					rootElement.appendChild(state);
					
					// save id of state
					Attr attrId = doc.createAttribute("id");
					attrId.setValue(tempState.getId());
					state.setAttributeNode(attrId);
					
					// save type of state
					Attr attrType = doc.createAttribute("type");
					attrType.setValue(tempState.getType());
					state.setAttributeNode(attrType);
		
					// state name element
					Element nameElement = doc.createElement("name");
					nameElement.appendChild(doc.createTextNode(tempState.getName()));
					state.appendChild(nameElement);
				}
				
				// edges
				for(int i = 0; i < edges.size(); i++) {
					Edge tempEdge = edges.get(i);
					// edge element
					Element edge = doc.createElement("edge");
					rootElement.appendChild(edge);
					
					// save id of edge
					Attr attrEdgeId = doc.createAttribute("id");
					attrEdgeId.setValue(tempEdge.getId());
					edge.setAttributeNode(attrEdgeId);
		
					// save from of edge
					Attr attrEdgeFrom = doc.createAttribute("from");
					attrEdgeFrom.setValue(tempEdge.getFrom());
					edge.setAttributeNode(attrEdgeFrom);
					
					// save to of edge
					Attr attrEdgeTo = doc.createAttribute("to");
					attrEdgeTo.setValue(tempEdge.getTo());
					edge.setAttributeNode(attrEdgeTo);
					
					// edge read elements
					ArrayList<String> read = tempEdge.getRead();
					Element readElement = doc.createElement("read");
					edge.appendChild(readElement);
					for (int j = 0; j < read.size(); j++) {
						Element symbolElement = doc.createElement("symbol");
						symbolElement.appendChild(doc.createTextNode(read.get(j)));
						readElement.appendChild(symbolElement);
					}
			
					// edge write element
					Element writeElement = doc.createElement("write");
					writeElement.appendChild(doc.createTextNode(tempEdge.getWrite()));
					edge.appendChild(writeElement);
				}
				
				// write the content into xml file
				if (!(new File(fileName)).exists()) {
					StreamResult result = new StreamResult(new File(fileName));
					transformer.transform(source, result);
					// TODO remove test output
					System.out.println("Done writing file!\n");
				}
				else {
					// TODO remove test output
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
	}*/
	
	public static Graph readXMLFromFile(String fileName) {
		Graph graph = null;
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			// print the Turing machine's name
			// TODO remove test output
			String machineName = doc.getDocumentElement().getAttribute("name");
			String numberTapesString = doc.getDocumentElement().getAttribute("tapes");
			int numberTapes = Integer.parseInt(numberTapesString);
			System.out.println("Turing machine's name: " + machineName);
			
			// TODO remove test output
			System.out.println("\nSTATES");
			// get list of nodes
			NodeList stateList = doc.getElementsByTagName("state");
			ArrayList<State> states = new ArrayList<State>();
			for (int i = 0; i < stateList.getLength(); i++) {
				Node currentNode = stateList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					String id = currentElement.getAttribute("id");
					String type = currentElement.getAttribute("type");
					String name = getTagValue("name", currentElement);
					State tempState = new State(id, name, type);
					states.add(tempState);
					
					// TODO remove test output
					System.out.println("\n== State " + id + " ==\n");
					System.out.println("id: " + id);
					System.out.println("name: " + name);
					System.out.println("type: " + type);
				}
			}
			
			// TODO remove test output
			System.out.println("\nEDGES");
			// get list of edges
			NodeList edgeList = doc.getElementsByTagName("edge");
			ArrayList<Edge> edges = new ArrayList<Edge>();
			ArrayList<Transition> transitions = new ArrayList<Transition>();
			for (int i = 0; i < edgeList.getLength(); i++) {
				Node currentNode = edgeList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					// get from an to
					String from = currentElement.getAttribute("from");
					String to = currentElement.getAttribute("to");
					// TODO remove test output
					System.out.println("\n== Edge " + i + " ==\n");
					System.out.println("from: " + from);
					System.out.println("to: " + to);

					// get transitions
					NodeList transitionList = currentElement.getElementsByTagName("transition");
					ArrayList<Transition> transition = new ArrayList<Transition>();
					for (int j = 0; j < transitionList.getLength(); j++) {
						Node currentTransitionNode = transitionList.item(j);
						String id;
						ArrayList<String> read = new ArrayList<String>();
						ArrayList<String> write = new ArrayList<String>();
						ArrayList<String> action = new ArrayList<String>();
						if (currentTransitionNode.getNodeType() == Node.ELEMENT_NODE) {
							Element currentTransitionElement = (Element) currentTransitionNode;
							id = currentTransitionElement.getAttribute("id");
							// TODO remove test output
							System.out.println("== TRANSITION" + id + "==");

							// get read
							NodeList readList = currentTransitionElement.getElementsByTagName("read");
							for (int k = 0; k < readList.getLength(); k++) {
								Node currentReadNode = readList.item(k);
								if (currentReadNode.getNodeType() == Node.ELEMENT_NODE) {
									Element currentReadElement = (Element) currentReadNode;
									NodeList readSymbolList = currentReadElement.getChildNodes();
									for (int l = 0; l < readSymbolList.getLength(); l++) {
										Node currentReadSymbolNode = readSymbolList.item(l);
										if (currentReadSymbolNode.getNodeType() == Node.ELEMENT_NODE) {
											read.add(currentReadSymbolNode.getTextContent());
											// TODO remove test output
											System.out.println("read: " + currentReadSymbolNode.getTextContent());
										}
									}
								}
							}
							
							// get write
							NodeList writeList = currentTransitionElement.getElementsByTagName("write");
							for (int k = 0; k < writeList.getLength(); k++) {
								Node currentWriteNode = writeList.item(k);
								if (currentWriteNode.getNodeType() == Node.ELEMENT_NODE) {
									Element currentWriteElement = (Element) currentWriteNode;
									NodeList writeSymbolList = currentWriteElement.getChildNodes();
									for (int l = 0; l < writeSymbolList.getLength(); l++) {
										Node currentWriteSymbolNode = writeSymbolList.item(l);
										if (currentWriteSymbolNode.getNodeType() == Node.ELEMENT_NODE) {
											read.add(currentWriteSymbolNode.getTextContent());
											// TODO remove test output
											System.out.println("write: " + currentWriteSymbolNode.getTextContent());
										}
									}
								}
							}
							
							// get action
							NodeList actionList = currentTransitionElement.getElementsByTagName("action");
							for (int k = 0; k < actionList.getLength(); k++) {
								Node currentActionNode = actionList.item(k);
								if (currentActionNode.getNodeType() == Node.ELEMENT_NODE) {
									Element currentActionElement = (Element) currentActionNode;
									NodeList actionSymbolList = currentActionElement.getChildNodes();
									for (int l = 0; l < actionSymbolList.getLength(); l++) {
										Node currentActionSymbolNode = actionSymbolList.item(l);
										if (currentActionSymbolNode.getNodeType() == Node.ELEMENT_NODE) {
											action.add(currentActionSymbolNode.getTextContent());
											// TODO remove test output
											System.out.println("direction: " + currentActionSymbolNode.getTextContent());
										}
									}
								}
							}
							
							Transition tempTransition = new Transition(id, read, write, action);
							transitions.add(tempTransition);
						}
					}
					
					Edge tempEdge = new Edge(from, to, transition);
					edges.add(tempEdge);
				}
			}
			graph = new Graph(states, edges, machineName, numberTapes);
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
