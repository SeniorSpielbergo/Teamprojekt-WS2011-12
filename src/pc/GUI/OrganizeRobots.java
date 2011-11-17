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
import javax.swing.event.*;

import TuringMachine.*;

/** This class represents the window used to organize the robots
 * 
 * @author David Wille
 * 
 */

public class OrganizeRobots extends JDialog implements ActionListener, TableModelListener, ListSelectionListener {

	static final long serialVersionUID = -3667258249137827980L;
	private JTable table;
	private JScrollPane tablePane;
	private ArrayList<ArrayList<String>> data;
	private JButton saveButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton cancelButton;
	private JPanel saveCancelContainer;
	private JPanel addDeleteContainer;
	private OrganizeRobotsTable model;
	private ListSelectionModel listSelectionModel;
	private boolean tableInitialized = false;
	private boolean dataChanged = false;
	
	public OrganizeRobots() {
		this.setModal(true);
		Container contentPane = this.getContentPane();
		
		// window title and size
		setTitle("Organize robots");
		setSize(450, 350);
		this.setResizable(false);
		
		// ok button
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
		addDeleteContainer = new JPanel();
		addDeleteContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
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
			data = loadRobotsFromXML();
		}
		catch (Exception e) {
			ErrorDialog.showError("Parsing the XML file failed.", e);
		}
		model = new OrganizeRobotsTable();
		table = new JTable(model);
		table.getModel().addTableModelListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				model.addRow(data.get(i).get(0), data.get(i).get(1));
				table.setEditingColumn(i);
			}
		}
		
		tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		
		contentPane.add(tablePane);
		tableInitialized = true;
	}
	
	/**
	 * Shows the window
	 */
	public void showDialog() {
		this.setVisible(true);
	}
	
	public static ArrayList<ArrayList<String>> loadRobotsFromXML() throws IOException {
		File file = new File("robots.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		
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
		
		// get number robots
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		
		if (file.exists()) {
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
		}
		
		return output;
	}
	
	private void saveRobotsToXML(ArrayList<ArrayList<String>> robots) {
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
			ErrorDialog.showError("Cannot write XML files. Serious configuration error.", e);
		} catch (ParserConfigurationException e) {
			ErrorDialog.showError("Cannot write XML files. Serious configuration error.", e);
		}
		
		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement("robots");
		doc.appendChild(rootElement);
		
		for (int i = 0; i < robots.size(); i++) {
			if (robots.get(i).get(0) != "" && robots.get(i).get(1) != "") {
				Element robotElement = doc.createElement("robot");
				rootElement.appendChild(robotElement);
				
				Element nameElement = doc.createElement("name");
				nameElement.appendChild(doc.createTextNode(robots.get(i).get(0)));
				robotElement.appendChild(nameElement);
				
				Element macElement = doc.createElement("mac");
				macElement.appendChild(doc.createTextNode(robots.get(i).get(1)));
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
	
	/**
	 * Responds to a clicked button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			saveRobotsToXML(data);
			this.setVisible(false);
			dispose();
		}
		else if (e.getSource() == cancelButton) {
			if (dataChanged) {
				int answer = JOptionPane.showConfirmDialog(null, "The data changed! Do you really want quit without saving?",
													"Not saved!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (answer == JOptionPane.NO_OPTION) {
					return;
				}
				else if (answer == JOptionPane.YES_OPTION) {
					this.setVisible(false);
					dispose();
				}
			}
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
				JOptionPane.showMessageDialog(null, "You first have to select a row in order to delete it!");
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if (tableInitialized) {
			int row = e.getFirstRow();
			int col = e.getColumn();
			if (e.getType() == TableModelEvent.UPDATE) {
				String newData = (String) this.table.getModel().getValueAt(row, col);
				data.get(row).set(col, newData);
				dataChanged = true;
			}
			else if (e.getType() == TableModelEvent.INSERT) {
				ArrayList<String> tempData = new ArrayList<String>();
				tempData.add("");
				tempData.add("");
				data.add(tempData);
				dataChanged = true;
			}
			else if (e.getType() == TableModelEvent.DELETE) {
				data.remove(row);
				dataChanged = true;
			}
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		int row = table.getSelectedRow();
		table.setColumnSelectionInterval(0, 1);
		table.setRowSelectionInterval(row, row);
	}
}