package gui;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JPanel;

/**
 * Abstract base class for all graphical machine editors 
 * @author Nils Breyer
 *
 */
@SuppressWarnings("serial")
public abstract class MachineEditor extends JPanel {
	private ArrayList<JMenu> menus = new ArrayList<JMenu>();

	public ArrayList<JMenu> getMenus() {
		return menus;
	}
	
	/** 
	 * Enable or disable the Editor. This method is to be used when the simulation is started, finished or aborted.
	 * @param editable Whether the Editor should be editable or not.
	 */
	public abstract void setEditable(boolean editable);
	
	/**
	 * Enable or disable the menu items of the edit menu.
	 * @param selectable Whether the items are selectable or not.
	 */
	public abstract void setEditMenuItemsSelectable(boolean selectable);
	
	
}
