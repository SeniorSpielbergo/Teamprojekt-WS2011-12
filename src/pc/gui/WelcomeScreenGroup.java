package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.io.File;

import machine.Machine.MachineType;

import gui.WelcomeScreenLine.Type;

public class WelcomeScreenGroup extends JPanel {
	
	private static final long serialVersionUID = -7394463454996558171L;
	private JLabel title;
	private Editor editor;
	private Type type;
	private MachineType machineType;

	public WelcomeScreenGroup(Editor editor, String titleText, Type type, MachineType machineType) {
		this.machineType = machineType;
		this.type = type;
		this.editor = editor;
		this.setLayout(new GridBagLayout());
		File[] exampleFiles = AppData.examplesDirectory.listFiles();
		title = new JLabel(titleText, JLabel.LEFT);
		this.setBackground(Color.WHITE);
		
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.insets = new Insets(5,10,5,5);
		this.add(title, c);
		if (this.type == Type.FILE) {
			for (int i = 0; i < exampleFiles.length; i++) {
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = i+1;
				c.weightx = 1.0;
				c.insets = new Insets(5,10,5,5);
				MachineType currentFileType = null;
				if (exampleFiles[i].toString().endsWith(".tm")) {
					currentFileType = MachineType.TuringMachine;
				}
				else if (exampleFiles[i].toString().endsWith(".bf")) {
					currentFileType = MachineType.BrainfuckMachine;
				}
				if (currentFileType == this.machineType) {
					if (i != 0) {
						this.add(new JSeparator(SwingConstants.HORIZONTAL));
					}
					this.add(new WelcomeScreenLine(this.editor, exampleFiles[i].getAbsolutePath(), this.machineType), c);
				}
			}
		}
		else {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0.1;
			c.insets = new Insets(5,10,5,5);
			this.add(new WelcomeScreenLine(this.editor, this.type, this.machineType), c);
		}
	}
}
