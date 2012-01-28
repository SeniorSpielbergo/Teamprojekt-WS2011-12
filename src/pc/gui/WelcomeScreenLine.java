package gui;

import machine.Machine.MachineType;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

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
	private JLabel createNew;
	private JLabel open;
	private JLabel name;
	private JTextPane description = new JTextPane();
	private Machine machine;
	private MachineType machineType;
	private String file;

	public WelcomeScreenLine(String file, MachineType machineType) {
		this.machineType = machineType;
		this.file = file;
		this.type = Type.FILE;
		this.setBackground(Color.WHITE);
		if (file.endsWith(TuringMachine.FILE_EXTENSION)) {
			machine = new TuringMachine();
		}
		else if (file.endsWith(BrainfuckMachine.FILE_EXTENSION)) {
			machine = new BrainfuckMachine();
		}
		try {
			machine.load(file);
			if (this.machine.getType() == MachineType.BrainfuckMachine) {
				int index = file.lastIndexOf("/");
				name = new JLabel(file.substring(index+1), JLabel.LEFT);
			}
			else if (this.machine.getType() == MachineType.TuringMachine) {
				name = new JLabel(machine.getName(), JLabel.LEFT);
			}
			author = new JLabel(machine.getAuthor());
			description.setText(machine.getDescription());
			author.setForeground(new Color(120,120,120));
		}
		catch(Exception e) {
			int index = file.lastIndexOf("/");
			name = new JLabel(file.substring(index+1), JLabel.LEFT);
		}
		name.setFont(name.getFont().deriveFont(Font.BOLD));

		filename = new JLabel(file, JLabel.LEFT);
		filename.setForeground(new Color(120,120,120));
		filename.setFont(description.getFont().deriveFont(9f));
		if (file.endsWith(TuringMachine.FILE_EXTENSION)) {
			logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/filetype_tm.png")), JLabel.CENTER);
		}
		else if (file.endsWith(BrainfuckMachine.FILE_EXTENSION)) {
			logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/filetype_bf.png")), JLabel.CENTER);
		}
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.05;
		c.gridheight = 4;
		c.insets = new Insets(5,5,5,5);
		this.add(logo, c);
		c.gridheight = 1;
		
		int gridy = 1;
		
		if (machine.getName() != "") {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = gridy++;
			c.weightx = 0.95;
			c.insets = new Insets(5,15,5,5);
			this.add(name, c);
		}
		if (machine.getAuthor() != "") {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = gridy++;
			c.weightx = 0.95;
			c.insets = new Insets(5,15,5,5);
			this.add(author, c);
		}
		if (machine.getDescription() != "") {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = gridy++;
			c.weightx = 0.95;
			c.insets = new Insets(5,15,5,5);
			description.setEditable(false);
			this.add(description, c);
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = gridy++;
		c.weightx = 0.95;
		c.insets = new Insets(0,10,0,5);
		this.add(filename, c);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	public WelcomeScreenLine(Type type, MachineType machineType) {
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
			c.weightx = 0.05;
			c.gridheight = 4;
			c.insets = new Insets(5,5,5,5);
			this.add(logo, c);
			open = new JLabel("Open existing machine...", JLabel.LEFT);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.95;
			c.insets = new Insets(5,15,5,5);
			this.add(open, c);
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		}
		else if (this.type == Type.CREATE) {
			if (this.machineType == MachineType.TuringMachine) {
				logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/filetype_tm_new.png")), JLabel.CENTER);
				createNew = new JLabel("Create new Turing Machine...", JLabel.LEFT);
			}
			else if (this.machineType == MachineType.BrainfuckMachine) {
				logo = new JLabel("", new ImageIcon(this.getClass().getResource("images/filetype_bf_new.png")), JLabel.CENTER);
				createNew = new JLabel("Create new Brainfuck Machine...", JLabel.LEFT);
			}
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.05;
			c.gridheight = 4;
			c.insets = new Insets(5,5,5,5);
			this.add(logo, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.95;
			c.insets = new Insets(5,5,5,5);
			this.add(createNew, c);
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		}
	}
	
	public Type getPanelType() {
		return this.type;
	}
	
	public String getFilePath() {
		return this.file;
	}
	
}
