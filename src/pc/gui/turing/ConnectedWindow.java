package gui.turing;

import gui.CustomTable;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tape.Robot;

public class ConnectedWindow extends JDialog implements Observer {

	private static final long serialVersionUID = -7819314476730849863L;
	private CustomTable model;
	private JTable table;
	private JScrollPane tablePane;
	private String[] head = {"Name", "Connected"};
	private boolean[] editable = {false, false};
	private ArrayList<String> robotNames;
	
	public ConnectedWindow(ArrayList<String> robotNames) {
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		center.setLocation(center.getX()-150, center.getY()-125);
		this.setLocation(center);
		this.robotNames = robotNames;
		this.setTitle("Connecting...");
		this.setSize(350,250);
		this.setResizable(false);
		this.setVisible(true);
		
		model = new CustomTable(head, editable);
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setFocusable(false);
		
		tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		if (this.robotNames != null) {
			for (int i = 0; i < this.robotNames.size(); i++) {
				Object[] tempData = {this.robotNames.get(i), Boolean.FALSE};
				model.addRow(tempData);
				table.setEditingColumn(i);
			}
		}
		
		this.add(tablePane);
	}
	
	private void robotConnected(String robotName) {
		int robotRow = -1;
		Object[] update = null;
		for (int i = 0; i < robotNames.size(); i++) {
			if (model.getValueAt(i, 0).equals(robotName)) {
				robotRow = i;
				Object[] temp = {robotName, Boolean.TRUE};
				update = temp;
				break;
			}
		}
		if (robotRow != -1 && update != null) {
			model.updateRow(update, robotRow);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Robot) {
			String robotName = (String) arg;
			this.robotConnected(robotName);
		}
	}
	
}
