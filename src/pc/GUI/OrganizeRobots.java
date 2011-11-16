package GUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
	private String[][] data;
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
			System.out.println(e.getLocalizedMessage());
			// TODO handle exception
		}
		table = new JTable(data, head);
		tablePane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		//contentPane.add(tablePane);
	}
	
	/**
	 * Shows the window
	 */
	public void showDialog() {
		this.setVisible(true);
	}
	
	private static String[][] readRobotsFromXML() throws IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new File("robots.xml"));
		}
		catch (Exception e) {
			// TODO handle exception
			System.out.println(e.getLocalizedMessage());
		}
		doc.getDocumentElement().normalize();
		
		// get number robots
		System.out.println(doc.getDocumentElement().getAttribute("robots"));
		int robotsNumber = Integer.parseInt(doc.getDocumentElement().getAttribute("robots"));
		String[][] output = new String[robotsNumber][2];
		
		NodeList robotList = doc.getElementsByTagName("robot");
		for (int i = 0; i < robotList.getLength(); i++) {
			Node robotNode = robotList.item(i);

			if (robotNode.getNodeType() != Node.ELEMENT_NODE) {
				break;
			}

			Element robotElement = (Element) robotNode;
			String name = InOut.getTagValue("name", robotElement);
			String mac = InOut.getTagValue("mac", robotElement);
			
			output[i][0] = name;
			output[i][1] = mac;
		}
		
		System.out.println(output.toString());
		
		return output;
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