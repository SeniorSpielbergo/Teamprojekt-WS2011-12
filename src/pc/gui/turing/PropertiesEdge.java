package gui.turing;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import machine.turing.*;

import gui.*;

public class PropertiesEdge extends JPanel implements ActionListener, ListSelectionListener {

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
	 * Stores the columns names
	 */
	private String[] head = {"Input", "Output", "Action"};
	/**
	 * Stores which columns are editable
	 */
	private boolean[] editable = {false, false, false};
	/**
	 * Stores the edited edge
	 */
	private Edge edge;

	/**
	 * Constructs a panel showing all transitions of the current edge
	 * @param numberTapes Number of tapes in the current machine
	 * @param edge The edge that should be edited
	 */
	public PropertiesEdge(int numberTapes, Edge edge) {
		this.edge = edge;
		this.numberTapes = numberTapes;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMaximumSize(new Dimension(250, 300));
		this.setPreferredSize(new Dimension(250, 300));

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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editTable(table.getSelectedRow());
				}
			}
		});

		ArrayList<Transition> transitions = edge.getTransitions();

		for (int i = 0; i < transitions.size(); i++) {
			// get character lists
			ArrayList<Character> action = transitions.get(i).getAction();
			ArrayList<Character> read = transitions.get(i).getRead();
			ArrayList<Character> write = transitions.get(i).getWrite();
			// add array for row
			String[] transitionString = new String[3];
			for (int j = 0; j < 3; j++) {
				transitionString[j] = "";
			}
			for (int j = 0; j < action.size(); j++) {
				transitionString[0] += read.get(j);
				transitionString[1] += write.get(j);
				transitionString[2] += action.get(j);
				if (j < action.size()-1) {
					transitionString[0] += ", ";
					transitionString[1] += ", ";
					transitionString[2] += ", ";
				}
			}
			model.addRow(transitionString);
		}

		// scroll panel
		tablePane = new JScrollPane(table);
		this.add(tablePane, BorderLayout.CENTER);

		// add / delete container
		GridBagConstraints c = new GridBagConstraints();
		addDeleteContainer = new JPanel(new GridLayout());
		addButton = new JButton("Add");
		deleteButton = new JButton("Remove");
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
	}

	/**
	 * Opens the edit window and stores the edited data
	 * @param row The row that should be edited
	 */
	private void editTable(int row) {
		PropertiesEdgeEdit editWindow = new PropertiesEdgeEdit(numberTapes, edge.getTransitions().get(row));
		Transition editData = editWindow.showEdit();
		editWindow.setLocationRelativeTo(null);
		if (editData != null) {
			String[] rowData = new String[3];
			for (int i = 0; i < 3; i++) {
				rowData[i] = "";
			}
			for (int i = 0; i < numberTapes; i++) {
				rowData[0] += editData.getRead().get(i).toString();
				rowData[1] += editData.getWrite().get(i).toString();
				rowData[2] += editData.getAction().get(i).toString();
				if (i < numberTapes-1) {
					rowData[0] += ", ";
					rowData[1] += ", ";
					rowData[2] += ", ";
				}
			}
			model.updateRow(rowData, row);
			edge.getTransitions().set(row, editData);
		}
	}

	/**
	 * Responds to a clicked button
	 * @param e ActionEvent that indicates changes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			PropertiesEdgeEdit editWindow = new PropertiesEdgeEdit(numberTapes, null);
			editWindow.setLocationRelativeTo(null);
			Transition editData = editWindow.showEdit();
			if (editData != null) {
				String[] newRow = new String[3];
				for (int i = 0; i < 3; i++) {
					newRow[i] = "";
				}
				for (int i = 0; i < numberTapes; i++) {
					newRow[0] += editData.getRead().get(i).toString();
					newRow[1] += editData.getWrite().get(i).toString();
					newRow[2] += editData.getAction().get(i).toString();
					if (i < numberTapes-1) {
						newRow[0] += ", ";
						newRow[1] += ", ";
						newRow[2] += ", ";
					}
				}
				model.addRow(newRow);
				edge.getTransitions().add(editData);
			}
		}
		else if (e.getSource() == deleteButton) {
			if (table.getSelectedRow() != -1) {
				int row = table.getSelectedRow();
				edge.getTransitions().remove(row);
				model.deleteRow(row);
			}
		}
	}

	/**
	 * Responds to selection changes
	 * @param e ListSelectionEvent that indicates changes
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
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