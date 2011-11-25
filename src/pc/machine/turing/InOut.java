package machine.turing;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** This class provides methods to read and write Turing machines from / to files
 * 
 * @author David Wille
 * 
 */

public class InOut {
	/**
	 * Writes a Turing machine to a LaTex file
	 * @param fileName Name of the file, where to write to
	 * @param machine Turing machine that should be written to the file
	 */
	public static void writeLatexToFile(String fileName, TuringMachine machine) {
		// check for right file ending
		if (!fileName.endsWith(".tex")) {
			fileName = fileName + ".tex";
		}

		try {
			// copy from template to new file
			File inputFile = new File("template.tex");
			File outputFile = new File(fileName);

			FileReader in = new FileReader(inputFile);
			FileWriter out = new FileWriter(outputFile);
			int c;

			while ((c = in.read()) != -1) {
				out.write(c);
			}
			in.close();
			out.close();

			// edit new file
			BufferedReader reader = new BufferedReader(new FileReader(outputFile));

			// read text to temp string
			String line = "";
			String oldContent = "";
			String automata = "";
			String makeTitleName, newContent;
			while((line = reader.readLine()) != null) {
				oldContent += line + "\n";
			}
			reader.close();

			// write maketitle
			makeTitleName = "\\title{" + machine.getName() + "}";

			// write automata nodes
			automata += writeStatesToLatex(machine.getStates());

			automata += "\n";

			// write automata edges
			automata += writeEdgesToLatex(machine.getEdges());

			newContent = oldContent.replace("{PLACEHOLDER_MAKETITLENAME}", makeTitleName);
			newContent = newContent.replace("{PLACEHOLDER_AUTOMATA}", automata);

			FileWriter writer = new FileWriter(outputFile);
			writer.write(newContent);
			writer.close();
		}
		catch (IOException e) {
		}
	}

	/**
	 * Returns the LaTeX string of nodes for an automata
	 * @param states The states that should be converted to LaTeX
	 * @return String of nodes formated for an automata 
	 */
	private static String writeStatesToLatex(ArrayList<State> states) {
		String output = "";
		State oldLeft = states.get(0), oldMiddle = states.get(0);
		for (int i = 0; i < states.size(); i++) {
			State currentState = states.get(i);
			String type = "";
			String name = currentState.getName();
			// check type of node
//			switch (currentState.getType()) { //TODO: change to new booleans
//			case START:
//				type = ", initial";
//				break;
//			case NORMAL:
//				break;
//			case FINAL:
//				type = ", accepting";
//				break;
//			default:
//				break;
//			}

			if (i % 3 == 0) {
				if (i == 0) {
					output += "\\node[state" + type + "] (" + name + ") {$" + name + "$};\n";
				}
				else {
					output += "\\node[state" + type + "] (" + name + ") [below of = " + oldLeft.getName() + "] {$" + name + "$};\n";
				}
				oldLeft = currentState;
			}
			else if (i % 3 == 1) {
				output += "\\node[state" + type + "] (" + name + ") [right of = " + oldLeft.getName() + "] {$" + name + "$};\n";
				oldMiddle = currentState;
			}
			else if (i % 3 == 2) {
				output += "\\node[state" + type + "] (" + name + ") [right of = " + oldMiddle.getName() + "] {$" + name + "$};\n";
			}

		}
		return output;
	}

	/**
	 * Returns the LaTeX string of edges for an automata
	 * @param edges The edges that should be converted to LaTeX
	 * @return String of edges formated for an automata
	 */
	private static String writeEdgesToLatex(ArrayList<Edge> edges) {
		String output = "";
		for (int i = 0; i < edges.size(); i++) {
			Edge currentEdge = edges.get(i);
			for (int j = 0; j < currentEdge.getTransitions().size(); j++) {
				Transition currentTransition = currentEdge.getTransitions().get(j);
				String transition = writeTransitionString(currentTransition);
				State from = currentEdge.getFrom();
				State to = currentEdge.getTo();
				if (from.equals(to)) {
					output += "\\path[->] (" + from.getName() +  ") edge [loop above] node {" + transition + "} ();\n";
				}
				else {
					output += "\\path[->] (" + from.getName() +  ") edge [bend left] node {" + transition + "} (" + to.getName() +  ");\n";
				}
			}
		}
		return output;
	}

	/**
	 * Returns the LaTeX string of a transition for an automata
	 * @param transition The transition that should be converted to LaTeX
	 * @return String of a transition formated for an automata
	 */
	private static String writeTransitionString(Transition transition) {
		String output = "";
		String readString = "<";
		String writeActionString = "<";
		Transition currentTransition = transition;
		ArrayList<Character> read = currentTransition.getRead(); 
		ArrayList<Character> write = currentTransition.getWrite(); 
		ArrayList<Character> action = currentTransition.getAction();
		for (int i = 0; i < read.size(); i++) {
			readString += "" + escapeHash(read.get(i));
			if (i == read.size()-1) {
				readString += ">";
			}
			else {
				readString += ", ";
			}
		}
		for (int i = 0; i < write.size(); i++) {
			writeActionString += "" + escapeHash(write.get(i)) + ", ";
			writeActionString += "" + escapeHash(action.get(i)) + ">";
			if (i != write.size()-1) {
				writeActionString += ", ";
			}
		}
		output += readString + " / " + writeActionString;
		return output;
	}

	/**
	 * Checks for hashes and escapes them
	 * @param charToCheck Character to check for a hash
	 * @return The initial character or an escaped hash
	 */
	private static String escapeHash(char charToCheck) {
		if (charToCheck == '#') {
			return "\\#";
		}
		return "" + charToCheck;
	}

	/**
	 * Returns the value of an element with a certain tag
	 * @param tag Tag you want to read
	 * @param currentElement The current element you want to read from
	 * @return The value of that tag
	 * @throws IOException Thrown if not tag with the specified name is found or if it is not unique (used multiple times)
	 */
	public static String getTagValue(String tag, Element currentElement) throws IOException {
		NodeList nodeList = currentElement.getElementsByTagName(tag);
		if (nodeList.getLength() > 1) {
			throw new IOException("The tag '" + tag + "' is ambigious because it was used multiple times on '" 
					+ currentElement.getNodeName() + "'.");
		}
		if (nodeList.getLength() < 1) {
			throw new IOException("Missing tag '" + tag + "' on '" + currentElement.getNodeName() + "'.");
		}
		NodeList childList = nodeList.item(0).getChildNodes();

		Node child = null;
		for (int i = 0; i < childList.getLength(); i++) {
			child = childList.item(i);
			if (child.getNodeType() != Node.TEXT_NODE) {
				break; //ignore attributes etc.
			}
		}

		if (child != null) {
			return child.getNodeValue();
		}
		else {
			System.out.println("WARNING: Tag '" + tag + "' on '" + currentElement.getNodeName() + "' is empty.");
			return "";
		}
	}

	/**
	 * Gets a child element node with a specific name that is a child of the currentElement
	 * @param tag The name of the child element to search
	 * @param currentElement The parent element of the child to search
	 * @return The child node with the specified name
	 * @throws IOException Thrown if the child not is not unique (if the tag is used multiple times) or if the specified tag is not existent
	 */
	public static Element getChildElement(String tag, Element currentElement) throws IOException {
		NodeList nodeList = currentElement.getElementsByTagName(tag);
		Node node = null;
		for (int i=0; i < nodeList.getLength(); i++) {
			if (node != null && nodeList.item(i).getNodeType() == Node.ELEMENT_NODE ) {
				throw new IOException("Multiple tag '" + tag + "' on '" + currentElement.getNodeName() + "' is not allowed.");
			}
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				node = nodeList.item(i);
			}
		}
		if (node == null) {
			throw new IOException("Expected missing tag '" + tag + "' on '" + currentElement.getNodeName() + "'.");
		}
		Element element = (Element) node;
		return element;
	}

}
