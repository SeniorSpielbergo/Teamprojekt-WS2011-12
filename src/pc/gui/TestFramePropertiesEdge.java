package gui;

import java.awt.Dimension;

import gui.turing.PropertiesEdge;

import javax.swing.JFrame;

public class TestFramePropertiesEdge extends JFrame {
	
	public TestFramePropertiesEdge() {
		setTitle("Test");
		setSize(300, 500);
		PropertiesEdge test = new PropertiesEdge();
		
		this.getContentPane().add(test);
	}
	
	public static void main(String[] args) {
		TestFramePropertiesEdge test = new TestFramePropertiesEdge();
		test.setVisible(true);
	}
	
}