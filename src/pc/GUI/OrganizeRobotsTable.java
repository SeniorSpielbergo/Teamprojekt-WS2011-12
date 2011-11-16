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
	
	public void setValueAt(String value, int row, int col) {
		ArrayList<String> tempList = data.get(row);
		tempList.set(col, value);
		data.set(row, tempList);
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
	}
}