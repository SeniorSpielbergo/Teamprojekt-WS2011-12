package GUI;
import java.awt.event.*;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame implements ActionListener {

	
	protected TuringMachine machine;
	public JButton expandWindowButton;
	
	public RunWindow(TuringMachine machine) {
		expandWindowButton = new JButton("expand Options");
		add(expandWindowButton);
		expandWindowButton.addActionListener(this);
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		setTitle("Run");
		setSize(600, 200);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == expandWindowButton) {
			this.setSize(600, 500);
		}
	}
}