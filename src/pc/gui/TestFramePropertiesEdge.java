package gui;

import gui.turing.PropertiesEdge;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class TestFramePropertiesEdge extends JFrame {
	
	public TestFramePropertiesEdge() {
		setTitle("Test");
		setSize(300, 500);
		PropertiesEdge test = new PropertiesEdge(3);
		
		this.getContentPane().add(test);
	}
	
	public static void main(String[] args) {
		// try to set look for Linux and Mac OS X
		try {
			if (System.getProperties().getProperty("os.name").equals("Linux")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}
			else if (System.getProperties().getProperty("os.name").equals("Mac OS X")) {
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", Editor.appName);
				System.setProperty("apple.laf.useScreenMenuBar", "true");
			}
		} 
		catch (Exception e) {
		}
		TestFramePropertiesEdge test = new TestFramePropertiesEdge();
		test.setVisible(true);
	}
	
}