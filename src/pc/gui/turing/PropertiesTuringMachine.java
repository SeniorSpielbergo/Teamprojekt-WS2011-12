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

/**
 * This class implements the Turingmachine's properties panel.
 * @author David Wille
 */
public class PropertiesTuringMachine extends JPanel implements DocumentListener {
	private static final long serialVersionUID = 4596217473460632217L;
	
	private TuringMachine machine;
	private JPanel content;
	private JLabel nameLabel = new JLabel("Name:");
	private JLabel authorLabel = new JLabel("Author:");
	private JLabel descriptionLabel = new JLabel("Description:");
	private JLabel numberTapesLabel;
	private JLabel numberStates;
	private JLabel numberEdges;
	private JTextField turingMachineName;
	private JTextField turingMachineAuthor;
	private JTextArea descriptionArea;
	private JScrollPane scrollPane;

	/**
	 * Constructs a panel that shows the information about a Turing Machine.
	 * @param machine The Turing Machine of which information should be displayed
	 */
	public PropertiesTuringMachine(TuringMachine machine) {
		this.machine = machine;
		
		this.setMaximumSize(new Dimension(1000, 120));
		this.setPreferredSize(new Dimension(250, 400));
		this.setBorder(BorderFactory.createTitledBorder("Properties"));
		this.setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		content = new JPanel(new GridBagLayout());
		turingMachineName = new JTextField(10);
		turingMachineName.getDocument().addDocumentListener(this);
		turingMachineName.setText(this.machine.getName());
		turingMachineAuthor = new JTextField(10);
		turingMachineAuthor.getDocument().addDocumentListener(this);
		turingMachineAuthor.setText(this.machine.getAuthor());
		numberTapesLabel = new JLabel("Number of tapes: " + this.machine.getNumberOfTapes());
		numberStates = new JLabel("Number of states: " + this.machine.getStates().size());
		numberEdges = new JLabel("Number of edges: " + this.machine.getEdges().size());
		descriptionArea = new JTextArea(10,5);
		descriptionArea.setBorder(BorderFactory.createLoweredBevelBorder());
		descriptionArea.getDocument().addDocumentListener(this);
		descriptionArea.setText(this.machine.getDescription());
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(descriptionArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(200, 250));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,5,10);
		content.add(nameLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(turingMachineName, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.2;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(authorLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.8;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(turingMachineAuthor, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,10,0,10);
		content.add(descriptionLabel, c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,10,5,10);
		content.add(scrollPane, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(numberTapesLabel, c);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(numberStates, c);
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,10,5,10);
		content.add(numberEdges, c);
		
		this.add(content);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (e.getDocument() == turingMachineName.getDocument()) {
			this.machine.setName(this.turingMachineName.getText());
		}
		else if (e.getDocument() == turingMachineAuthor.getDocument()) {
			this.machine.setAuthor(this.turingMachineAuthor.getText());
		}
		else if (e.getDocument() == descriptionArea.getDocument()) {
			this.machine.setDescription(this.descriptionArea.getText());
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (e.getDocument() == turingMachineName.getDocument()) {
			this.machine.setName(this.turingMachineName.getText());
		}
		else if (e.getDocument() == turingMachineAuthor.getDocument()) {
			this.machine.setAuthor(this.turingMachineAuthor.getText());
		}
		else if (e.getDocument() == descriptionArea.getDocument()) {
			this.machine.setDescription(this.descriptionArea.getText());
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
}