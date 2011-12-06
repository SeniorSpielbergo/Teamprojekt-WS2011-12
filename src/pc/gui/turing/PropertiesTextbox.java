package gui.turing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.microedition.lcdui.TextBox;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import machine.turing.TuringMachine;

public class PropertiesTextbox extends JPanel implements DocumentListener {

	private static final long serialVersionUID = -4793677956080787446L;
	private Textbox textbox;
	private JPanel content;
	private JLabel textLabel = new JLabel("Text:");
	private JTextField textField;

	public PropertiesTextbox(TextBox textbox) {
		this.textbox = textbox;
		
		this.setMaximumSize(new Dimension(1000, 120));
		this.setPreferredSize(new Dimension(250, 180));
		this.setBorder(BorderFactory.createTitledBorder("Properties"));
		this.setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		content = new JPanel(new GridBagLayout());
		textField = new JTextField(10);
		textField.getDocument().addDocumentListener(this);
		textField.setText(textbox.getText());
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,10,10);
		content.add(textLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,10,10);
		content.add(textField, c);
		
		this.add(content);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.textbox.setText(textfield.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.textbox.setText(textfield.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
}
