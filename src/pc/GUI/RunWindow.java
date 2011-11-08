package GUI;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame {
	
	protected TuringMachine machine;
	
	public RunWindow(TuringMachine machine) {
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		setTitle("Run");
		setSize(600, 200);
	}
}