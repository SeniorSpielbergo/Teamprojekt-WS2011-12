package gui;

import machine.Machine.MachineType;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import machine.Machine;
import machine.brainfuck.BrainfuckMachine;
import machine.turing.TuringMachine;

public class WelcomeScreenLine extends JPanel implements ActionListener {
	
	public enum Type {
		OPEN, CREATE, FILE
	};
	
	private static final long serialVersionUID = -3539030092043453310L;
	private Type type;
	private JLabel logo;
	private JLabel author;
	private WelcomeScreenButton filename;
	private WelcomeScreenButton createNew;
	private WelcomeScreenButton open;
	private JLabel name;
	private JTextPane description = new JTextPane();
	private Machine machine;
	private MachineType machineType;
	private Editor editor;

	public WelcomeScreenLine(Editor editor, String file, MachineType machineType) {
		this.editor = editor;
		this.machineType = machineType;
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
			author = new JLabel(machine.getAuthor()); 
			filename = new WelcomeScreenButton(file);
			filename.addActionListener(this);
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
		c.insets = new Insets(0,10,0,5);
		this.add(filename, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 6.0;
		c.gridheight = 4;
		c.insets = new Insets(0,0,0,0);
		this.add(Box.createVerticalGlue(), c);
	}
	
	public WelcomeScreenLine(Editor editor, Type type, MachineType machineType) {
		this.editor = editor;
		this.type = type;
		this.machineType = machineType;
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		if (this.type == Type.OPEN) {
			logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/open.png")), JLabel.CENTER);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.1;
			c.gridheight = 4;
			c.insets = new Insets(5,5,5,5);
			this.add(logo, c);
			open = new WelcomeScreenButton("Open existing machine...");
			open.addActionListener(this);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.9;
			c.insets = new Insets(5,15,5,5);
			this.add(open, c);
		}
		else if (this.type == Type.CREATE) {
			if (this.machineType == MachineType.TuringMachine) {
				createNew = new WelcomeScreenButton("Create new Turing Machine...");
			}
			else if (this.machineType == MachineType.BrainfuckMachine) {
				createNew = new WelcomeScreenButton("Create new Brainfuck Machine...");
			}
			createNew.addActionListener(this);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.1;
			c.gridheight = 4;
			c.insets = new Insets(5,5,5,5);
			this.add(createNew, c);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == filename) {
			JOptionPane.showMessageDialog(null, "Not implemented yet!");
		}
		else if (e.getSource() == open) {
			this.editor.openFile();
		}
		else if (e.getSource() == createNew) {
			this.editor.newFile(machineType);
		}
	}
	
}
