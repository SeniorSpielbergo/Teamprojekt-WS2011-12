package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class WelcomeScreen extends JPanel {
	
	private static final long serialVersionUID = 882824632406556671L;
	private JTabbedPane tabbedPane;
	private JScrollPane turingPane;
	private JScrollPane brainfuckPane;
	private JPanel turingContainer;
	private JPanel brainfuckContainer;
	private JPanel header;
	private JLabel logo;
	private JLabel headerText;
	private JPanel turingOpen;
	private JPanel turingCreate;
	private JPanel turingExamples;
	private JPanel brainfuckOpen;
	private JPanel brainfuckCreate;
	private JPanel brainfuckExamples;
	
	public WelcomeScreen() {
		initWelcomeScreen();
		this.setLayout(new BorderLayout());
		
		// header
		header.setLayout(new BorderLayout());

		header.add(logo, BorderLayout.LINE_START);
		this.logo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		header.add(headerText, BorderLayout.CENTER);
		this.headerText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints c = new GridBagConstraints();
		
		// turingPane
		turingOpen = new WelcomeScreenGroup("Open");
		turingCreate = new WelcomeScreenGroup("Create");
		turingExamples = new WelcomeScreenGroup("Examples");
		turingContainer.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.insets = new Insets(5,5,5,5);
		turingContainer.add(turingOpen, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.insets = new Insets(5,5,5,5);
		turingContainer.add(turingCreate, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.insets = new Insets(5,5,5,5);
		turingContainer.add(turingExamples, c);
		
		// brainfuckPane
		brainfuckOpen = new WelcomeScreenGroup("Open");
		brainfuckCreate = new WelcomeScreenGroup("Create");
		brainfuckExamples = new WelcomeScreenGroup("Examples");
		brainfuckContainer.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.insets = new Insets(5,5,5,5);
		brainfuckContainer.add(brainfuckOpen, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.insets = new Insets(5,5,5,5);
		brainfuckContainer.add(brainfuckCreate, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.insets = new Insets(5,5,5,5);
		brainfuckContainer.add(brainfuckExamples, c);
		
		turingPane = new JScrollPane(turingContainer);
		brainfuckPane = new JScrollPane(brainfuckContainer);
		
		// tabs
		tabbedPane.addTab("Turing Machine", turingPane);
		tabbedPane.addTab("Brainfuck", brainfuckPane);
		
		this.add(header, BorderLayout.PAGE_START);
		this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void initWelcomeScreen() {
		tabbedPane = new JTabbedPane();
		turingContainer = new JPanel();
		brainfuckContainer = new JPanel();
		logo = new JLabel("", new ImageIcon("gui/images/logo.png"), JLabel.CENTER);
		header = new JPanel();
		headerText = new JLabel("Welcome to " + Editor.APP_NAME + "!");
	}
	
}
