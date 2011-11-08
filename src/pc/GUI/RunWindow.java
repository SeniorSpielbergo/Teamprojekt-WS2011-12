package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame implements ActionListener {
	
	private String[] description = { "LEGO-Tape", "Console-Tape", "Graphic-Tape"};
	protected TuringMachine machine;
	
	public RunWindow(TuringMachine machine) {
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		setTitle("Run");
		setSize(600, 200);
		
		Container comboContainer = getContentPane();
		
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox();
			for (int j = 0; j < description.length; j++) {
				combo[i].addItem(description[j]);
			}
			comboContainer.add(combo[i]);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}