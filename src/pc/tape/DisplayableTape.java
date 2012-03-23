package tape;

import javax.swing.JPanel;

/**
 * This class is for all the tapes that can be shown in the simulation window.
 * @author Nils Breyer
 *
 */
public abstract class DisplayableTape extends Tape{
	/**
	 * Constructs a new tape
	 */
	public DisplayableTape() {
		super();
	}

	/**
	 * Constructs a new tape
	 * @param allowInput If true, the user can enter an input word for the tape
	 */
	public DisplayableTape(boolean allowInput) {
		super(allowInput);
	}

	/**
	 * Constructs a new tape
	 * @param name The tape name
	 * @param allowInput If true, the user can enter an input word for the tape
	 */
	public DisplayableTape(String name, boolean allowInput) {
		super(name, allowInput);
	}

	/**
	 * Constructs a new tape
	 * @param name The tape name
	 */
	public DisplayableTape(String name) {
		super(name);
	}

	/**
	 * Returns the current tape style
	 * @return The name of the tape style
	 */
	public abstract String getStyle();

	/**
	 * Sets the current tape style
	 * @param style The name of the tape style
	 */
	public abstract void setStyle(String style);
	
	/**
	 * Returns a graphical panel displaying the tape content
	 * @return the panel
	 */
	public abstract JPanel getTapePanel();
	
	/**
	 * Returns the height of the tape panel
	 * @return height in pixels
	 */
	public abstract int getHeight();
}