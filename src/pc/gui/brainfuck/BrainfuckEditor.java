package gui.brainfuck;

import javax.swing.*;

import gui.MachineEditor;

import java.awt.*;

public class BrainfuckEditor extends MachineEditor{
	private static final long serialVersionUID = -6379014025769077968L;
	
	private JTextArea codeTextArea;
	private JScrollPane codePane;
	private JLabel outputLabel;
	private JLabel outputTextLabel;
	
	public BrainfuckEditor() {
		codeTextArea = new JTextArea("Type your brainfuck code here");
		codeTextArea.setFont(new Font("Courier", Font.PLAIN, 14));
		codeTextArea.setLineWrap(true);
		codeTextArea.setWrapStyleWord(true);
		codePane = new JScrollPane(codeTextArea);
		codePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		codePane.setPreferredSize(new Dimension(100,100));

		outputTextLabel = new JLabel("Output: ", SwingConstants.LEFT);
		outputLabel = new JLabel("", SwingConstants.LEFT);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(outputTextLabel,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(outputLabel,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 2;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		add(codePane,c);
	}
	
	public void setCode(String code) {
		this.codeTextArea.setText(code);
	}
	
	public void setOutput(String text) {
		this.outputTextLabel.setText(text);
	}
	
	public void appendTextToOutputText(String text) {
		String currentText = this.outputLabel.getText();
		this.outputLabel.setText(currentText + text);
	}

	public String getCode() {
		return this.codeTextArea.getText();
	}
	
	public JLabel getOutputLabel() {
		return this.outputLabel;
	}
	
	public void reset() {
		setCode("Type your brainfuck code here");
		setOutput("");
	}
}