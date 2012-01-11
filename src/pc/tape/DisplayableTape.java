package tape;

import javax.swing.JPanel;

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
}