package gui.turing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.CustomTable;

import javax.swing.*;

public class PropertiesEdgeEdit extends JDialog implements ActionListener {
	
	static final long serialVersionUID = -3667258249137827980L;
	private JTable table;
	private JScrollPane tablePane;
	private CustomTable model;
	private JButton cancelButton;
	private JButton saveButton;
	private JPanel saveCancelContainer;
	/**
	 * Stores whether the table is initialized
	 */
	private boolean tableInitialized = false;
	/**
	 * Stores the number of tapes
	 */
	private int numberTapes;
	/**
	 * Stores the data returned by the dialog
	 */
	private String[] returnData;
	/**
	 * Stores the columns names
	 */
	private String[] head = {"Tape", "Input", "Output", "Action"};
	/**
	 * Stores which columns are editable
	 */
	private boolean[] editable = {false, true, true, true};
	
	/**
	 * Constructs a dialog to edit the properties of a transition
	 * @param numberTapes Number of tapes in the current machine
	 */
	public PropertiesEdgeEdit(int numberTapes) {
		this.setModal(true);
		this.numberTapes = numberTapes;
		Container contentPane = this.getContentPane();
		
		// window title and size
		this.setTitle("Edit transition properties");
		this.setSize(300, 250);
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
		
		// add table
		model = new CustomTable(head, editable);
		table = new JTable(model);
//		table.getModel().addTableModelListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setFocusable(false);
//		listSelectionModel = table.getSelectionModel();
//		listSelectionModel.addListSelectionListener(this);
		
		// initialize table
		for (int i = 0; i < numberTapes; i++) {
			String[] tempData = {"" + i, "*", "#", "N"};
			model.addRow(tempData);
			table.setEditingColumn(i);
		}
		
		tablePane = new JScrollPane(table);
		tablePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		contentPane.add(tablePane);
		tableInitialized = true;
	}
	
	/**
	 * Shows the Dialog
	 * @return The data edited by the user
	 */
	public String[] showEdit() {
		this.setVisible(true);
		
		return returnData;
	}
	
	/**
	 * Responds to a clicked button
	 * @param e ActionEvent that indicates changes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			String[] tempData = {"*", "#", "N"};
			returnData = tempData;
			this.setVisible(false);
			dispose();
		}
		else if (e.getSource() == cancelButton) {
			returnData = null;
			this.setVisible(false);
			dispose();
		}
	}
	
}