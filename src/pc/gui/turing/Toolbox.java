package gui.turing;

import javax.swing.*;

public class Toolbox extends JPanel {

	private static final long serialVersionUID = -5130578684131390225L;
	
	public Toolbox() {
		Palette palette = new Palette();
		this.add(palette);
	}
	
	public static void main(String[] a) {
		Toolbox t = new Toolbox();
		JFrame f = new JFrame();
		f.add(t);
		f.setVisible(true);
		f.setSize(200,200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
	}

}
