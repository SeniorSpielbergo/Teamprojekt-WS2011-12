package gui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreenGroup extends JPanel {
	
	private static final long serialVersionUID = -7394463454996558171L;
	private JLabel title;

	public WelcomeScreenGroup(String titleText) {
		title = new JLabel(titleText);
		this.setBackground(Color.WHITE);
		this.add(title);
	}
}
