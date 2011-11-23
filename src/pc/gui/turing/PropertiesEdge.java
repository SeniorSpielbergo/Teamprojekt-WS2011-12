package gui.turing;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import machine.turing.*;

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
	private ArrayList<ArrayList<ArrayList<String>>> currentTransitions = new ArrayList<ArrayList<ArrayList<String>>>();
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
	 * @param edge The edge that should be edited
	 */
	public PropertiesEdge(int numberTapes, Edge edge) {
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
		
		// TODO initialize table
		ArrayList<Transition> transitions = edge.getTransitions();
		
		for (int i = 0; i < transitions.size(); i++) {
			// get character lists
			ArrayList<Character> action = transitions.get(i).getAction();
			ArrayList<Character> read = transitions.get(i).getRead();
			ArrayList<Character> write = transitions.get(i).getWrite();
			// add string lists for editing
			ArrayList<ArrayList<String>> transition = new ArrayList<ArrayList<String>>(); 
			ArrayList<String> readList = new ArrayList<String>();
			ArrayList<String> writeList = new ArrayList<String>();
			ArrayList<String> actionList = new ArrayList<String>();
			// add array for row
			String[] transitionString = new String[3];
			for (int j = 0; j < action.size(); j++) {
				transitionString[0] = "";
				transitionString[1] = "";
				transitionString[2] = "";
			}
			for (int j = 0; j < action.size(); j++) {
				transitionString[0] += read.get(j);
				transitionString[1] += write.get(j);
				transitionString[2] += action.get(j);
				readList.add("" + read.get(j));
				writeList.add("" + write.get(j));
				actionList.add("" + action.get(j));
				if (j < action.size()-1) {
					transitionString[0] += ", ";
					transitionString[1] += ", ";
					transitionString[2] += ", ";
				}
			}
			// store mapped data
			transition.add(readList);
			transition.add(writeList);
			transition.add(actionList);
			currentTransitions.add(transition);
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
		
		tableInitialized = true;
	}
	
	private void editTable(int row) {
		PropertiesEdgeEdit editWindow = new PropertiesEdgeEdit(numberTapes, currentTransitions.get(row));
		ArrayList<ArrayList<String>> editData = editWindow.showEdit();
		editWindow.setLocationRelativeTo(null);
		if (editData != null) {
			String[] rowData = new String[editData.get(0).size()];
			for (int i = 0; i < numberTapes; i++) {
				for (int j = 0; j < editData.get(i).size(); j++) {
					rowData[j] = "";
				}
			}
			for (int i = 0; i < numberTapes; i++) {
				for (int j = 0; j < editData.get(i).size(); j++) {
					if (i < numberTapes-1) {
						rowData[j] += editData.get(i).get(j) + ", ";
					}
					else {
						rowData[j] += editData.get(i).get(j);
					}
				}
			}
			model.updateRow(rowData, row);
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
			ArrayList<ArrayList<String>> editData = editWindow.showEdit();
			if (editData != null) {
				String[] rowData = new String[editData.get(0).size()];
				for (int i = 0; i < numberTapes; i++) {
					for (int j = 0; j < editData.get(i).size(); j++) {
						rowData[j] = "";
					}
				}
				for (int i = 0; i < numberTapes; i++) {
					for (int j = 0; j < editData.get(i).size(); j++) {
						if (i < numberTapes-1) {
							rowData[j] += editData.get(i).get(j) + ", ";
						}
						else {
							rowData[j] += editData.get(i).get(j);
						}
					}
				}
				model.addRow(rowData);
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