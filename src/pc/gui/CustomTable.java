package gui;

import java.util.ArrayList;

import javax.swing.table.*;

/** This class represents a table model
 * 
 * @author David Wille
 * 
 */

public class CustomTable extends AbstractTableModel {

	static final long serialVersionUID = -3667258249137827980L;
	/**
	 * Stores the columns names
	 */
	private String[] columnNames;
	/**
	 * Stores the data
	 */
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
	/**
	 * Stores which columns are editable
	 */
	private boolean[] editable;

	/**
	 * Constructs a custom table with column names and sets if columns are editable
	 * @param head Names for the columns
	 * @param editable Editable columns
	 */
	public CustomTable(String[] head, boolean[] editable) {
		this.editable = editable;
		columnNames = head;
	}

	/**
	 * Returns the number of columns
	 * @return Number of columns
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Returns the number of rows
	 * @return Number of rows
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/**
	 * Returns the value of the cell
	 * @param i Row number
	 * @param j Column number
	 * @return Value of the cell
	 */
	@Override
	public String getValueAt(int i, int j) {
		return data.get(i).get(j);
	}

	/**
	 * Returns the column name
	 * @param i Column number
	 * @return Column name
	 */
	@Override
	public String getColumnName(int i) {
		return columnNames[i];
	}

	/**
	 * Sets the value in the specified cell
	 * @param value Value which should be set
	 * @param row Row number
	 * @param col Column number
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {
		data.get(row).set(col, (String) value);
		fireTableCellUpdated(row, col);
	}

	/**
	 * Returns if the cell is editable
	 * @param row Row number
	 * @param col Column number
	 * @return true/false If cell editable
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		if (editable[col]) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Add row to table
	 * @param values Values to be inserted
	 */
	public void addRow(String[] values) {
		ArrayList<String> row = new ArrayList<String>();
		for (int i = 0; i < values.length; i++) {
			row.add(values[i]);
		}
		data.add(row);
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	/**
	 * Updates a row with the given values
	 * @param values Values to set
	 * @param row Row that should be updated
	 */
	public void updateRow(String[] values, int row) {
		for (int i = 0; i < values.length; i++) {
			setValueAt(values[i], row, i);
			fireTableCellUpdated(row, i);
		}
	}

	/**
	 * Delete row
	 * @param row Row which should be deleted
	 */
	public void deleteRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}