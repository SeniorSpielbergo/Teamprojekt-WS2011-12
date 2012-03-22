package gui;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;

import machine.Machine.MachineType;
import machine.brainfuck.BrainfuckMachine;
import machine.turing.TuringMachine;

import gui.WelcomeScreenLine.Type;

/** 
 * This class represents a group on the Welcome Screen.
 * @author David Wille
 */
public class WelcomeScreenGroup extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -7394463454996558171L;
	private JLabel title;
	private Editor editor;
	private Type type;
	private MachineType machineType;

	/**
	 * Constructs a group of welcome screen lines.
	 * @param editor The current editor window.
	 * @param titleText Text of the current group.
	 * @param type The type of the group.
	 * @param machineType The type of the machines displayed in this group.
	 */
	public WelcomeScreenGroup(Editor editor, String titleText, Type type, MachineType machineType) {
		this.machineType = machineType;
		this.type = type;
		this.editor = editor;
		this.setLayout(new GridBagLayout());
		File[] exampleFiles = AppData.examplesDirectory.listFiles();
		java.util.Arrays.sort(exampleFiles);
		title = new JLabel(titleText, JLabel.LEFT);
		title.setFont(title.getFont().deriveFont(16f));

		//this.setBackground(Color.WHITE);
		
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
				if (exampleFiles[i].toString().endsWith(TuringMachine.FILE_EXTENSION)) {
					currentFileType = MachineType.TuringMachine;
				}
				else if (exampleFiles[i].toString().endsWith(BrainfuckMachine.FILE_EXTENSION)) {
					currentFileType = MachineType.BrainfuckMachine;
				}
				if (currentFileType == this.machineType) {
					WelcomeScreenLine line = new WelcomeScreenLine(exampleFiles[i].getAbsolutePath(), this.machineType);
					line.addMouseListener(this);
					this.add(line, c);
				}
			}
		}
		else {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 0.1;
			c.insets = new Insets(5,10,5,5);
			WelcomeScreenLine line = new WelcomeScreenLine(this.type, this.machineType);
			line.addMouseListener(this);
			this.add(line, c);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		WelcomeScreenLine source = (WelcomeScreenLine) e.getSource();
		
		if (source.getPanelType() == Type.CREATE) {
			this.editor.newFile(this.machineType);
		}
		else if (source.getPanelType() == Type.OPEN) {
			this.editor.openFile();
		}
		else if (source.getPanelType() == Type.FILE) {
			this.editor.openFile(source.getFilePath());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
