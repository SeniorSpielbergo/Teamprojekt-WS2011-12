package gui;

import gui.turing.PropertiesEdge;

import javax.swing.JFrame;

public class TestFramePropertiesEdge extends JFrame {
	
	public TestFramePropertiesEdge() {
		setTitle("Test");
		setSize(300, 500);
		PropertiesEdge test = new PropertiesEdge(3);
		
		this.getContentPane().add(test);
	}
	
	public static void main(String[] args) {
		TestFramePropertiesEdge test = new TestFramePropertiesEdge();
		test.setVisible(true);
	}
	
}