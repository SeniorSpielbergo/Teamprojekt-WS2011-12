package GUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
	private JPanel okContainer;
	
	public OrganizeRobots() {
		this.setModal(true);
		String[] head = {"Name", "MAC-Address"};
		Container contentPane = this.getContentPane();
		
		// window title and size
		setTitle("Organize robots");
		setSize(400, 500);
		this.setResizable(false);
		
		// ok button
		okContainer = new JPanel();
		okContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		BoxLayout okLayout = new BoxLayout(okContainer, BoxLayout.X_AXIS);
		okContainer.setLayout(okLayout);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okContainer.add(Box.createHorizontalGlue());
		okContainer.add(okButton);
		this.getRootPane().setDefaultButton(okButton);
		contentPane.add(okContainer, BorderLayout.AFTER_LAST_LINE);
		
		try {
			data = readRobotsFromXML();
		}
		catch (Exception e) {
			// TODO handle exception
		}
		OrganizeRobotsTable model = new OrganizeRobotsTable();
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
		// TODO write out
	}
	
	/**
	 * Responds to a clicked button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			this.setVisible(false);
			dispose();
		}
	}
}