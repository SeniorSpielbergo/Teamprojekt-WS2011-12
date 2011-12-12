package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutDialog extends JDialog {
	JLabel jLabelIcon =  null;

	public AboutDialog() {
		super();
		
		this.setTitle("About " + Editor.APP_NAME);
		this.jLabelIcon = new JLabel("", new ImageIcon("gui/images/logo.png"), JLabel.CENTER);
		this.add(jLabelIcon);
		
		this.setSize(new Dimension(300,400));
	}
	
}
