package GUI.Brainfuck;

import javax.swing.*;
import java.awt.*;

public class BrainfuckEditor extends JPanel{
	private static final long serialVersionUID = -6379014025769077968L;
	
	private JTextArea codeTextArea;
	private JScrollPane codePane;
	private JTextField inputField;
	private JLabel inputLabel;
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

		inputLabel = new JLabel("Inputstring: ");
		inputField = new JTextField("Type your inputstring here",10);
		inputField.setFont(new Font("Courier", Font.PLAIN, 14));
		
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
		add(inputLabel,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(inputField,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(outputTextLabel,c);
		
		c.gridx = 1;
		c.gridy = 1;
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
	
	public void setOutputText(String text) {
		this.outputLabel.setText(text);
	}
	
	public void appendTextToOutputText(String text) {
		String currentText = this.outputLabel.getText();
		this.outputLabel.setText(currentText + text);
	}
	
	public void setInputText(String text) {
		this.inputField.setText(text);
	}
	
	public String getOutput() {
		return this.outputTextLabel.getText();
	}
	
	public String getInput() {
		return this.inputField.getText();
	}
	
	public String getCode() {
		return this.codeTextArea.getText();
	}
}
