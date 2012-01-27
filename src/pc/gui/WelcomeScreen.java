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
	private JPanel header;
	private JLabel logo;
	private JLabel headerText;
	
	public WelcomeScreen() {
		initWelcomeScreen();
		this.setLayout(new BorderLayout());
		
		// header
		header.setLayout(new BorderLayout());

		header.add(logo, BorderLayout.LINE_START);
		this.logo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		header.add(headerText, BorderLayout.CENTER);
		this.headerText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// tabs
		tabbedPane.addTab("Turing Machine", turingPane);
		tabbedPane.addTab("Brainfuck", brainfuckPane);
		
		this.add(header, BorderLayout.PAGE_START);
		this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void initWelcomeScreen() {
		tabbedPane = new JTabbedPane();
		turingPane = new JScrollPane();
		brainfuckPane = new JScrollPane();
		logo = new JLabel("", new ImageIcon("gui/images/logo.png"), JLabel.CENTER);
		header = new JPanel();
		headerText = new JLabel("Welcome to " + Editor.APP_NAME + "!");
	}
	
}
