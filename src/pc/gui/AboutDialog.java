package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutDialog extends JDialog {
	JLabel jLabelIcon =  null;
	JPanel jPanelDetails = null;
	JLabel jLabelAppName = null;
	JPanel jPanelAppName = null;
	JLabel jLabelVersion = null;
	JPanel jPanelVersion = null;
	JLabel jLabelText = null;
	JPanel jPanelText = null;


	public AboutDialog() {
		super();
		
		this.setTitle("About " + Editor.APP_NAME);
		
		this.setLayout(new BorderLayout());
		
		this.jLabelIcon = new JLabel("", new ImageIcon("gui/images/logo.png"), JLabel.CENTER);
		this.add(jLabelIcon, BorderLayout.CENTER);
		
		this.jPanelDetails = new JPanel();
		this.add(this.jPanelDetails, BorderLayout.SOUTH);
		
		this.jPanelDetails.setLayout(new BoxLayout(this.jPanelDetails, BoxLayout.PAGE_AXIS));
		this.jPanelDetails.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.jPanelDetails.add(Box.createHorizontalGlue());
		
		this.jLabelAppName = new JLabel(Editor.APP_NAME, JLabel.CENTER);
		this.jLabelAppName.setFont(this.jLabelAppName.getFont().deriveFont(this.jLabelAppName.getFont().getStyle() | Font.BOLD, this.jLabelAppName.getFont().getSize() + 2));
		this.jPanelAppName = new JPanel(new BorderLayout());
		this.jPanelAppName.add(this.jLabelAppName, BorderLayout.CENTER);
		this.jPanelDetails.add(jPanelAppName);
		this.jPanelDetails.add(Box.createRigidArea(new Dimension(0, 10)));

		
		this.jLabelVersion = new JLabel(Editor.APP_VERSION, JLabel.CENTER);
		this.jPanelVersion = new JPanel(new BorderLayout());
		this.jPanelVersion.add(this.jLabelVersion, BorderLayout.CENTER);
		this.jPanelDetails.add(jPanelVersion);
		this.jPanelDetails.add(Box.createRigidArea(new Dimension(0, 20)));

		
		this.jLabelText = new JLabel(Editor.APP_AUTHORS, JLabel.CENTER);
		this.jPanelText = new JPanel(new BorderLayout());
		this.jPanelText.add(this.jLabelText, BorderLayout.CENTER);
		this.jPanelDetails.add(jPanelText);
		this.jPanelDetails.add(Box.createRigidArea(new Dimension(0, 20)));

		
		
		this.setSize(new Dimension(300,400));
	}
	
}
