package GUI;

import java.util.ArrayList;

import javax.swing.table.*;

public class OrganizeRobotsTable extends AbstractTableModel {
	
	static final long serialVersionUID = -3667258249137827980L;
	private String[] columnsNames = {"Name", "MAC-Address"};
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

	public int getColumnCount() {
		return columnsNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getValueAt(int i, int j) {
		return data.get(i).get(j);
	}
	
	public String getColumnName(int i) {
		return columnsNames[i];
	}
	
	public void setValueAt(Object value, int row, int col) {
		data.get(row).set(col, (String) value);
		fireTableCellUpdated(row, col);
	}
	
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	public void addRow(String name, String mac) {
		ArrayList<String> row = new ArrayList<String>();
		row.add(name);
		row.add(mac);
		data.add(row);
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	public void deleteRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}