package GUI;
import java.awt.event.*;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame implements ActionListener {

	
	protected TuringMachine machine;
	
	public RunWindow(TuringMachine machine) {
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		setTitle("Run");
		setSize(600, 200);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}