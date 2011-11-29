package gui.turing;

import java.awt.Dimension;

import javax.swing.*;

public class ToolBox extends JPanel {

	private static final long serialVersionUID = -5130578684131390225L;
	private Palette palette;
	
	public ToolBox() {
		palette = new Palette();
		this.add(palette);
		this.setBorder(BorderFactory.createTitledBorder("Toolbox"));
		this.setMaximumSize(new Dimension(250, 125));
		this.setPreferredSize(new Dimension(250, 125));
	}
	
	public String getClicked() {
		return this.palette.getClicked();
	}

}
