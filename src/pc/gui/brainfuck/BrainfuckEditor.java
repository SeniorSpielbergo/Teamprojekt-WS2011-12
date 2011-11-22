package gui.brainfuck;

import gui.MachineEditor;

import javax.swing.*;
import java.awt.*;

public class BrainfuckEditor extends MachineEditor{
	private static final long serialVersionUID = -6379014025769077968L;
	
	private JTextArea codeTextArea;
	private JScrollPane codePane;
	
	public BrainfuckEditor() {
		codeTextArea = new JTextArea("Type your brainfuck code here");
		codeTextArea.setFont(new Font("Courier", Font.PLAIN, 14));
		codeTextArea.setLineWrap(true);
		codeTextArea.setWrapStyleWord(true);
		codePane = new JScrollPane(codeTextArea);
		codePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		codePane.setPreferredSize(new Dimension(100,100));

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
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
	
	public String getCode() {
		return this.codeTextArea.getText();
	}
}
