package gui.turing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.*;

public class PropertiesEdge extends JPanel implements ActionListener {
	
	static final long serialVersionUID = -3667258249137827980L;
	private JTable table;
	private JScrollPane tablePane;
	private CustomTable model;
	private JButton addButton;
	private JButton deleteButton;
	
	public PropertiesEdge() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// content panel
		this.setBorder(BorderFactory.createTitledBorder("Properties"));

		// add container
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.insets = new Insets(5,5,5,20);
		this.add(deleteButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.15;
		c.weighty = 1.0;
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.insets = new Insets(5,20,5,5);
		this.add(addButton, c);
		
		// table
		String[] head = {"Input", "Output", "Action"};
		model = new CustomTable(head);
		table = new JTable(model);
		
		// scroll panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weightx = 1.0;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		tablePane = new JScrollPane(table);
		this.add(tablePane, c);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			model.addRow("", "");
		}
		else if (e.getSource() == deleteButton) {
			if (table.getSelectedRow() != -1) {
				model.deleteRow(table.getSelectedRow());
			}
		}
	}
}