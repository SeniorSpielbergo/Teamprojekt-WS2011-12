package GUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;

import TuringMachine.*;

public class OrganizeRobots extends JDialog implements ActionListener {

	static final long serialVersionUID = -3667258249137827980L;
	private JTable table;
	private JScrollPane tablePane;
	private ArrayList<ArrayList<String>> data;
	private JButton okButton;
	private JButton addButton;
	private JButton deleteButton;
	private JPanel okContainer;
	private JPanel addDeleteContainer;
	private OrganizeRobotsTable model;
	
	public OrganizeRobots() {
		this.setModal(true);
		Container contentPane = this.getContentPane();
		
		// window title and size
		setTitle("Organize robots");
		setSize(400, 500);
		this.setResizable(false);
		
		// ok button
		okContainer = new JPanel();
		okContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		BoxLayout okLayout = new BoxLayout(okContainer, BoxLayout.X_AXIS);
		okContainer.setLayout(okLayout);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okContainer.add(Box.createHorizontalGlue());
		okContainer.add(okButton);
		this.getRootPane().setDefaultButton(okButton);
		contentPane.add(okContainer, BorderLayout.AFTER_LAST_LINE);
		
		// add / delete button
		addDeleteContainer = new JPanel();
		addDeleteContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		BoxLayout addDeleteLayout = new BoxLayout(addDeleteContainer, BoxLayout.X_AXIS);
		addDeleteContainer.setLayout(addDeleteLayout);
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		addDeleteContainer.add(deleteButton);
		addDeleteContainer.add(Box.createHorizontalGlue());
		addDeleteContainer.add(addButton);
		contentPane.add(addDeleteContainer, BorderLayout.AFTER_LINE_ENDS);
		
		try {
			data = readRobotsFromXML();
		}
		catch (Exception e) {
			// TODO handle exception
		}
		model = new OrganizeRobotsTable();
		table = new JTable(model);
		
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				model.addRow(data.get(i).get(0), data.get(i).get(1));
				table.setEditingColumn(i);
			}
		}
		
		tablePane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		
		contentPane.add(tablePane);
	}
	
	/**
	 * Shows the window
	 */
	public void showDialog() {
		this.setVisible(true);
	}
	
	public static ArrayList<ArrayList<String>> readRobotsFromXML() throws IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new File("robots.xml"));
		}
		catch (Exception e) {
			// TODO handle exception
		}
		doc.getDocumentElement().normalize();
		
		// get number robots
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		
		NodeList robotList = doc.getElementsByTagName("robot");
		for (int i = 0; i < robotList.getLength(); i++) {
			Node robotNode = robotList.item(i);

			if (robotNode.getNodeType() != Node.ELEMENT_NODE) {
				break;
			}

			Element robotElement = (Element) robotNode;
			String name = InOut.getTagValue("name", robotElement);
			String mac = InOut.getTagValue("mac", robotElement);
			
			ArrayList<String> robot = new ArrayList<String>();
			robot.add(name);
			robot.add(mac);
			output.add(robot);
		}
		
		return output;
	}
	
	private void writeRobotsToXML(ArrayList<ArrayList<String>> robots) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = null;
		DocumentBuilder docBuilder = null;
		
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
		} catch (TransformerConfigurationException e) {
			// TODO handle exception
		} catch (ParserConfigurationException e) {
			// TODO handle exception
		}
		
		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement("robots");
		doc.appendChild(rootElement);
		
		for (int i = 0; i < robots.size(); i++) {
			Element robotElement = doc.createElement("robot");
			rootElement.appendChild(robotElement);
			
			Element nameElement = doc.createElement("name");
			nameElement.appendChild(doc.createTextNode(robots.get(i).get(0)));
			robotElement.appendChild(nameElement);
			
			Element macElement = doc.createElement("mac");
			macElement.appendChild(doc.createTextNode(robots.get(i).get(1)));
			robotElement.appendChild(macElement);
		}
		
		StreamResult result = new StreamResult(new File("robots.xml"));
		DOMSource source = new DOMSource(doc);
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO handle exception
		}
	}
	
	/**
	 * Responds to a clicked button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			writeRobotsToXML(data);
			this.setVisible(false);
			dispose();
		}
		else if (e.getSource() == addButton) {
			model.addRow("", "");
		}
		else if (e.getSource() == deleteButton) {
			if (table.getSelectedRow() != -1) {
				model.deleteRow(table.getSelectedRow());
			}
			else {
				JOptionPane.showMessageDialog(null, "You have to select a row first in order to delete it!");
			}
		}
	}
}