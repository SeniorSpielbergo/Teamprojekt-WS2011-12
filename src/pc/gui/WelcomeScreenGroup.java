package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreenGroup extends JPanel {
	
	private static final long serialVersionUID = -7394463454996558171L;
	private JLabel title;
	private Editor editor;

	public WelcomeScreenGroup(Editor editor, String titleText) {
		this.editor = editor;
		this.setLayout(new GridBagLayout());
		title = new JLabel(titleText, JLabel.LEFT);
		this.setBackground(Color.WHITE);
		
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.insets = new Insets(5,5,5,5);
		this.add(title, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.insets = new Insets(5,5,5,5);
		this.add(new WelcomeScreenLine(this.editor, "Binary_Addition.tm"), c);
	}
}
