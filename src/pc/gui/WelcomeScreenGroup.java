package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.WelcomeScreenLine.Type;

public class WelcomeScreenGroup extends JPanel {
	
	private static final long serialVersionUID = -7394463454996558171L;
	private JLabel title;
	private Editor editor;
	private Type type;

	public WelcomeScreenGroup(Editor editor, String titleText, Type type) {
		this.type = type;
		this.editor = editor;
		this.setLayout(new GridBagLayout());
		title = new JLabel(titleText, JLabel.LEFT);
		this.setBackground(Color.WHITE);
		
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.insets = new Insets(5,5,5,5);
		this.add(title, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.insets = new Insets(5,5,5,5);
		if (this.type == Type.FILE) {
			this.add(new WelcomeScreenLine(this.editor, "machine/examples/Binary_Addition.tm"), c);
		}
		else if (this.type == Type.OPEN) {
			
		}
		else if (this.type == Type.CREATE) {
			
		}
	}
}
