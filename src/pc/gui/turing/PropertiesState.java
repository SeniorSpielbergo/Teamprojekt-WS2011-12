package gui.turing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PropertiesState extends JPanel implements ItemListener, TextListener {
	
	private static final long serialVersionUID = -5483231033293338765L;

	private JLabel name;
	private JTextField inputName;
	private JCheckBox startState;
	private JCheckBox finalState;
	private JPanel propertiesPanel;
	
	public PropertiesState() {
		name = new JLabel("Name");
		inputName = new JTextField(10);
		startState = new JCheckBox("Start");
		startState.addItemListener(this);
		finalState = new JCheckBox("Final");
		finalState.addItemListener(this);
		propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new GridBagLayout());
		
		this.setBorder(BorderFactory.createTitledBorder("Properties"));
		this.setLayout(new BorderLayout());
		
		// add / delete container
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.insets = new Insets(5,5,5,5);
		propertiesPanel.add(name, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.insets = new Insets(5,5,5,5);
		propertiesPanel.add(inputName, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		propertiesPanel.add(startState, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		propertiesPanel.add(finalState, c);
		
		this.add(propertiesPanel, BorderLayout.PAGE_START);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO stateProperties (final, start) have to change
	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO stateName has to change
	}
}