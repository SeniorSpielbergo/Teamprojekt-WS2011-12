package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AboutDialog extends JDialog {
	JLabel jLabelIcon =  null;
	JPanel jPanelDetails = null;
	JLabel jLabelAppName = null;
	JPanel jPanelAppName = null;
	JLabel jLabelVersion = null;
	JPanel jPanelVersion = null;
	JTextPane jTextPaneText = null;
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
		this.jPanelDetails.add(Box.createRigidArea(new Dimension(0, 5)));

		
		this.jLabelVersion = new JLabel(Editor.APP_VERSION, JLabel.CENTER);
		this.jPanelVersion = new JPanel(new BorderLayout());
		this.jPanelVersion.add(this.jLabelVersion, BorderLayout.CENTER);
		this.jPanelDetails.add(jPanelVersion);
		this.jPanelDetails.add(Box.createRigidArea(new Dimension(0, 25)));

		
		this.jTextPaneText = new JTextPane();
		this.jTextPaneText.setText(Editor.APP_AUTHORS);
		StyledDocument doc = this.jTextPaneText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		this.jTextPaneText.setEditable(false);
		this.jTextPaneText.setOpaque(false);
		this.jPanelText = new JPanel(new BorderLayout());
		this.jPanelText.add(this.jTextPaneText, BorderLayout.CENTER);
		this.jPanelDetails.add(jPanelText);
		this.jPanelDetails.add(Box.createRigidArea(new Dimension(0, 20)));
	
		
		this.setSize(new Dimension(300,400));
		this.centerDialogOnTheScreen();
	}
	
	/**
	 * Center the dialog window on the screen
	 */
	private void centerDialogOnTheScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dialogSize = this.getSize();
		int centerPosX = (screenSize.width - dialogSize.width) / 2;
		int centerPosY = (screenSize.height - dialogSize.height) / 2;
		setLocation(centerPosX, centerPosY);
	}
}
