package gui.turing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import gui.*;

public class PropertiesEdge extends JPanel implements ActionListener, TableModelListener, ListSelectionListener {
	
	static final long serialVersionUID = -3667258249137827980L;
	private JTable table;
	private JPanel addDeleteContainer;
	private JScrollPane tablePane;
	private CustomTable model;
	private JButton addButton;
	private JButton deleteButton;
	private int numberTapes;
	private ListSelectionModel listSelectionModel;
	/**
	 * Stores whether the table is initialized
	 */
	private boolean tableInitialized = false;
	/**
	 * Stores the columns names
	 */
	private String[] head = {"Input", "Output", "Action"};
	/**
	 * Stores which columns are editable
	 */
	private boolean[] editable = {false, false, false};
	
	/**
	 * Constructs a panel showing all transitions of the current edge
	 * @param numberTapes Number of tapes in the current machine
	 */
	public PropertiesEdge(int numberTapes) {
		this.numberTapes = numberTapes;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// content panel
		this.setBorder(BorderFactory.createTitledBorder("Properties"));
		
		// table
		model = new CustomTable(head, editable);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setFocusable(false);
		listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		
		// initialize table
		// TODO
		String[] tempData = {"*", "#", "N"};
		model.addRow(tempData);
		
		// scroll panel
		tablePane = new JScrollPane(table);
		this.add(tablePane, BorderLayout.CENTER);
		
		// add / delete container
		GridBagConstraints c = new GridBagConstraints();
		addDeleteContainer = new JPanel(new GridLayout());
		addButton = new JButton("+");
		deleteButton = new JButton("-");
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,20);
		addDeleteContainer.add(deleteButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,20,5,5);
		addDeleteContainer.add(addButton, c);
		this.add(addDeleteContainer, BorderLayout.AFTER_LAST_LINE);
		
		tableInitialized = true;
	}

	/**
	 * Responds to a clicked button
	 * @param e ActionEvent that indicates changes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			PropertiesEdgeEdit editWindow = new PropertiesEdgeEdit(numberTapes);
			editWindow.setLocationRelativeTo(null);
			String[] tempData = editWindow.showEdit();
			if (tempData != null) {
				model.addRow(tempData);
			}
		}
		else if (e.getSource() == deleteButton) {
			if (table.getSelectedRow() != -1) {
				model.deleteRow(table.getSelectedRow());
			}
		}
	}
	
	/**
	 * Responds to data changes in the table
	 * @param e TableModelEvent that indicates changes
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		if (tableInitialized) {
			int row = e.getFirstRow();
			int col = e.getColumn();
			if (e.getType() == TableModelEvent.UPDATE) {
				// TODO update
			}
			else if (e.getType() == TableModelEvent.INSERT) {
				// TODO insert
			}
			else if (e.getType() == TableModelEvent.DELETE) {
				// TODO delete
			}
		}
	}

	/**
	 * Responds to selection changes
	 * @param e ListSelectionEvent that indicates changes
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int row = table.getSelectedRow();
		if (row == -1) {
			row = table.getRowCount() - 1;
		}
		table.setColumnSelectionInterval(0, 2);
		table.setRowSelectionInterval(row, row);
	}
}