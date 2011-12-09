package gui.turing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mxgraph.view.mxGraph;

import machine.turing.Textbox;

public class PropertiesTextbox extends JPanel implements DocumentListener {
//	TODO: scrollpane
//  TODO: xml
	private static final long serialVersionUID = -4793677956080787446L;
	private Textbox textbox;
	private JPanel content;
	private JLabel textLabel = new JLabel("Text:");
	private JTextArea textArea;
	private mxGraph graph;

	public PropertiesTextbox(Textbox textbox, mxGraph graph) {
		this.graph = graph;
		this.textbox = textbox;
		
		this.setMaximumSize(new Dimension(1000, 120));
		this.setPreferredSize(new Dimension(250, 180));
		this.setBorder(BorderFactory.createTitledBorder("Properties"));
		this.setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		content = new JPanel(new GridBagLayout());
		textArea = new JTextArea(10,5);
		textArea.setBorder(BorderFactory.createLoweredBevelBorder());
		textArea.getDocument().addDocumentListener(this);
		textArea.setText(textbox.getText());
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
		content.add(textArea, c);
		
		this.add(content);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.textbox.setText(textArea.getText());
		this.graph.refresh();
		this.graph.repaint();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.textbox.setText(textArea.getText());
		this.graph.refresh();
		this.graph.repaint();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	
}
