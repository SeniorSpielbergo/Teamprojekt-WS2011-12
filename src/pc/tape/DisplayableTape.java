package tape;

import javax.swing.JPanel;

/**
 * This class is for all the tapes that can be shown in the simulation window.
 * @author Nils Breyer //TODO javadoc
 *
 */
public abstract class DisplayableTape extends Tape{
	public DisplayableTape() {
		super();
	}

	public DisplayableTape(boolean allowInput) {
		super(allowInput);
	}

	public DisplayableTape(String name, boolean allowInput) {
		super(name, allowInput);
	}

	public DisplayableTape(String name) {
		super(name);
	}

	public abstract String getStyle();

	public abstract void setStyle(String style);
	
	public abstract JPanel getTapePanel();
	
	public abstract int getHeight();
}