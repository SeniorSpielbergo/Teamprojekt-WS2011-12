package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import machine.Machine;
import machine.brainfuck.BrainfuckMachine;
import machine.turing.TuringMachine;

public class WelcomeScreenLine extends JPanel {
	
	public enum Type {
		OPEN, CREATE, FILE
	};
	
	private static final long serialVersionUID = -3539030092043453310L;
	private Type type;
	private JLabel logo;
	private JLabel author;
	private JLabel filename;
	private JLabel name;
	private JTextPane description = new JTextPane();
	private Machine machine;

	public WelcomeScreenLine(Editor editor, String file) {
		this.type = Type.FILE;
		this.setBackground(Color.WHITE);
		if (file.endsWith(".tm")) {
			machine = new TuringMachine();
		}
		else if (file.endsWith(".bf")) {
			machine = new BrainfuckMachine();
		}
		try {
			machine.load(file);
			name = new JLabel(machine.getName(), JLabel.LEFT);
			author = new JLabel(machine.getAuthor(), JLabel.LEFT);
			filename = new JLabel(file, JLabel.LEFT);
			description.setText(machine.getDescription());
			if (file.endsWith(".tm")) {
				logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/filetype_tm.png")), JLabel.CENTER);
			}
			else if (file.endsWith(".bf")) {
				logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/filetype_bf.png")), JLabel.CENTER);
			}
		}
		catch(Exception e) {
			name = new JLabel(file);
		}
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.gridheight = 4;
		c.insets = new Insets(5,5,5,5);
		this.add(logo, c);
		c.gridheight = 1;
		if (machine.getName() != "") {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.9;
			c.insets = new Insets(5,15,5,5);
			this.add(name, c);
		}
		if (machine.getAuthor() != "") {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0.9;
			c.insets = new Insets(5,15,5,5);
			this.add(author, c);
		}
		if (machine.getDescription() != "") {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = 2;
			c.weightx = 0.9;
			c.insets = new Insets(5,15,5,5);
			description.setEditable(false);
			this.add(description, c);
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.9;
		c.insets = new Insets(5,15,5,5);
		this.add(filename, c);
	}
	
	public WelcomeScreenLine(Editor editor, Type type) {
		this.type = type;
		this.setBackground(Color.WHITE);
		if (this.type == Type.OPEN) {
			
		}
		else if (this.type == Type.CREATE) {
			
		}
	}
	
}
