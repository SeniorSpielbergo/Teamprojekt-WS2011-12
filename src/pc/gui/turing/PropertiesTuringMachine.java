package gui.turing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import machine.turing.TuringMachine;

public class PropertiesTuringMachine extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = 4596217473460632217L;
	private TuringMachine machine;
	private JPanel content;
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel numberTapesLabel;
	private JLabel numberStates;
	private JLabel numberEdges;
	private JTextField turingMachineName;

	/**
	 * Constructs a panel, that shows the information about a Turing Machine
	 * @param machine The Turing Machine whose information should be displayed
	 */
	public PropertiesTuringMachine(TuringMachine machine) {
		this.machine = machine;
		
		this.setMaximumSize(new Dimension(1000, 120));
		this.setPreferredSize(new Dimension(250, 180));
		this.setBorder(BorderFactory.createTitledBorder("Properties"));
		this.setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		content = new JPanel(new GridBagLayout());
		turingMachineName = new JTextField(10);
		turingMachineName.getDocument().addDocumentListener(this);
		turingMachineName.setText(this.machine.getName());
		numberTapesLabel = new JLabel("Number of tapes: " + this.machine.getNumberOfTapes());
		numberStates = new JLabel("Number of states: " + this.machine.getStates().size());
		numberEdges = new JLabel("Number of edges: " + this.machine.getEdges().size());
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,10,10);
		content.add(nameLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,10,10);
		content.add(turingMachineName, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,5,10);
		content.add(numberTapesLabel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(numberStates, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(numberEdges, c);
		
		this.add(content);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.machine.setName(this.turingMachineName.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.machine.setName(this.turingMachineName.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
}
