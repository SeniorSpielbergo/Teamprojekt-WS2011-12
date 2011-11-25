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
	
	
}
