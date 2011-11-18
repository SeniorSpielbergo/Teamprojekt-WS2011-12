package gui;

import java.util.ArrayList;

import javax.swing.table.*;

/** This class represents the table model used to organize the robots
 * 
 * @author David Wille
 * 
 */

public class OrganizeRobotsTable extends AbstractTableModel {
	
	static final long serialVersionUID = -3667258249137827980L;
	private String[] columnsNames = {"Name", "MAC-Address"};
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

	/**
	 * Returns the number of columns
	 * @return Number of columns
	 */
	public int getColumnCount() {
		return columnsNames.length;
	}

	/**
	 * Returns the number of rows
	 * @return Number of rows
	 */
	public int getRowCount() {
		return data.size();
	}

	/**
	 * Returns the value of the cell
	 * @param i Row number
	 * @param j Column number
	 * @return Value of the cell
	 */
	public String getValueAt(int i, int j) {
		return data.get(i).get(j);
	}
	
	/**
	 * Returns the column name
	 * @param i Column number
	 * @return Column name
	 */
	public String getColumnName(int i) {
		return columnsNames[i];
	}
	
	/**
	 * Sets the value in the specified cell
	 * @param value Value which should be set
	 * @param row Row number
	 * @param col Column number
	 */
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
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	/**
	 * Add row to table
	 * @param name Name of the robot
	 * @param mac MAC-Address of the robot
	 */
	public void addRow(String name, String mac) {
		ArrayList<String> row = new ArrayList<String>();
		row.add(name);
		row.add(mac);
		data.add(row);
		fireTableRowsInserted(data.size()-1, data.size()-1);
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