package gui;
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.event.*;

import machine.turing.*;

/** This class represents the window used to organize the robots.
 * @author David Wille
 */
public class OrganizeRobots extends JDialog implements ActionListener, TableModelListener, ListSelectionListener {

	static final long serialVersionUID = -3667258249137827980L;
	private JTable table;
	private JScrollPane tablePane;
	private JButton saveButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton cancelButton;
	private JPanel saveCancelContainer;
	private JPanel addDeleteContainer;
	private CustomTable model;
	private ListSelectionModel listSelectionModel;
	private ArrayList<ArrayList<Object>> data;
	private boolean tableInitialized = false;
	private String[] head = {"Name", "MAC-Address", "Master"};
	private boolean[] editable = {true, true, true};
	
	/**
	 * Constructs the window for organizing the robots.
	 */
	public OrganizeRobots() {
		this.setModal(true);
		Container contentPane = this.getContentPane();
		
		// window title and size
		this.setTitle("Organize robots");
		this.setSize(450, 350);
		this.setResizable(false);
		
		// ok and cancel button
		saveCancelContainer = new JPanel();
		saveCancelContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		BoxLayout okLayout = new BoxLayout(saveCancelContainer, BoxLayout.X_AXIS);
		saveCancelContainer.setLayout(okLayout);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		saveButton = new JButton("OK");
		saveButton.addActionListener(this);
		saveCancelContainer.add(cancelButton);
		saveCancelContainer.add(Box.createHorizontalGlue());
		saveCancelContainer.add(saveButton);
		this.getRootPane().setDefaultButton(saveButton);
		contentPane.add(saveCancelContainer, BorderLayout.AFTER_LAST_LINE);
		
		// add / delete button
		addDeleteContainer = new JPanel(new GridBagLayout());
		addDeleteContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagConstraints c = new GridBagConstraints();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		deleteButton = new JButton("Remove");
		deleteButton.addActionListener(this);
		deleteButton.setEnabled(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		addDeleteContainer.add(addButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		addDeleteContainer.add(deleteButton, c);

		contentPane.add(addDeleteContainer, BorderLayout.AFTER_LINE_ENDS);
		
		try {
			data = loadRobotsFromXML();
		}
		catch (Exception e) {
			ErrorDialog.showError("Parsing the XML file failed.", e);
		}
		// add table
		model = new CustomTable(head, editable);
		table = new JTable(model);
		table.getModel().addTableModelListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setFocusable(false);
		listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		
		// if robots.xml not empty
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				Object[] tempData = {data.get(i).get(0), data.get(i).get(1), data.get(i).get(2)};
				model.addRow(tempData);
				table.setEditingColumn(i);
			}
		}
		
		tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		contentPane.add(tablePane);
		tableInitialized = true;
	}
	
	/**
	 * Shows the window.
	 */
	public void showDialog() {
		this.setVisible(true);
	}
	
	/**
	 * Loads the robots from the robots xml file.
	 * @return ArrayList of robots with their MAC-Addresses.
	 * @throws IOException Exception on loading problems.
	 */
	public static ArrayList<ArrayList<Object>> loadRobotsFromXML() throws IOException {
		File file = new File("robots.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		
		// only parse if file exists
		if (file.exists()) {
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				doc = db.parse(file);
			}
			catch (Exception e) {
				ErrorDialog.showError("Parsing XML failed. Check the syntax of the file 'robots.xml'!", e);
			}
			doc.getDocumentElement().normalize();
		}
		
		ArrayList<ArrayList<Object>> output = new ArrayList<ArrayList<Object>>();
		
		// only parse if file exists
		if (file.exists()) {
			// get robots with attributes
			NodeList robotList = doc.getElementsByTagName("robot");
			for (int i = 0; i < robotList.getLength(); i++) {
				Node robotNode = robotList.item(i);
	
				if (robotNode.getNodeType() != Node.ELEMENT_NODE) {
					break;
				}
	
				Element robotElement = (Element) robotNode;
				String name = InOut.getTagValue("name", robotElement);
				String mac = InOut.getTagValue("mac", robotElement);
				
				ArrayList<Object> robot = new ArrayList<Object>();
				robot.add(name);
				robot.add(mac);
				if (robotElement.getAttribute("master").equals("true")) {
					robot.add(Boolean.TRUE);
				}
				else if (robotElement.getAttribute("master").equals("false")) {
					robot.add(Boolean.FALSE);
				}
				output.add(robot);
			}
		}
		
		return output;
	}
	
	private void saveRobotsToXML(ArrayList<ArrayList<Object>> robots) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = null;
		DocumentBuilder docBuilder = null;
		
		// prepare xml
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
		} catch (TransformerConfigurationException e) {
			ErrorDialog.showError("Cannot write XML files. Serious configuration error.", e);
		} catch (ParserConfigurationException e) {
			ErrorDialog.showError("Cannot write XML files. Serious configuration error.", e);
		}
		
		// write to file
		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement("robots");
		doc.appendChild(rootElement);
		
		for (int i = 0; i < robots.size(); i++) {
			if (robots.get(i).get(0) != "" && robots.get(i).get(1) != "") {
				Element robotElement = doc.createElement("robot");
				rootElement.appendChild(robotElement);
				
				Attr attrMaster = doc.createAttribute("master");
				attrMaster.setValue(robots.get(i).get(2).toString());
				robotElement.setAttributeNode(attrMaster);
				
				Element nameElement = doc.createElement("name");
				nameElement.appendChild(doc.createTextNode(robots.get(i).get(0).toString()));
				robotElement.appendChild(nameElement);
				
				Element macElement = doc.createElement("mac");
				macElement.appendChild(doc.createTextNode(robots.get(i).get(1).toString()));
				robotElement.appendChild(macElement);
			}
		}
		
		StreamResult result = new StreamResult(new File("robots.xml"));
		DOMSource source = new DOMSource(doc);
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			ErrorDialog.showError("Problem writing to 'robots.xml'!", e);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			if (this.table.isEditing()) {
				table.getCellEditor().stopCellEditing();
			}
			saveRobotsToXML(data);
			this.setVisible(false);
			dispose();
		}
		else if (e.getSource() == cancelButton) {
			this.setVisible(false);
			dispose();
		}
		else if (e.getSource() == addButton) {
			Object[] tempData = {"", "", Boolean.FALSE};
			model.addRow(tempData);
		}
		else if (e.getSource() == deleteButton) {
			if (table.getSelectedRow() != -1) {
				model.deleteRow(table.getSelectedRow());
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if (tableInitialized) {
			int row = e.getFirstRow();
			int col = e.getColumn();
			if (e.getType() == TableModelEvent.UPDATE) {
				Object newData = this.table.getModel().getValueAt(row, col);
				data.get(row).set(col, newData);
			}
			else if (e.getType() == TableModelEvent.INSERT) {
				ArrayList<Object> tempData = new ArrayList<Object>();
				tempData.add("");
				tempData.add("");
				tempData.add(Boolean.FALSE);
				data.add(tempData);
			}
			else if (e.getType() == TableModelEvent.DELETE) {
				data.remove(row);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (table.getSelectedRow() == -1 ) {
			this.deleteButton.setEnabled(false);
		}
		else {
			this.deleteButton.setEnabled(true);
		}
		if (table.getRowCount() != 0) {
			int row = table.getSelectedRow();
			if (row == -1) {
				row = table.getRowCount() - 1;
			}
			table.setColumnSelectionInterval(0, 2);
			table.setRowSelectionInterval(row, row);
		}
	}
}