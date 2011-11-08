package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame implements ActionListener {
	
	private String[] description = {"LEGO-Tape", "Console-Tape", "Graphic-Tape"};
	protected TuringMachine machine;
	public JButton expandWindowButton;
	
	public RunWindow(TuringMachine machine) {
		expandWindowButton = new JButton("expand Options");
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		setTitle("Run");
		setSize(600, 200);
		
		// set layout
		Container contentPane = this.getContentPane();
		JPanel comboContainer = new JPanel();
		comboContainer.setBorder(BorderFactory.createTitledBorder("Tapes"));
		expandWindowButton.addActionListener(this);
		
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox();
			for (int j = 0; j < description.length; j++) {
				combo[i].addItem(description[j]);
			}
			comboContainer.add(combo[i]);
		}
		
		/*layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addComponent(combo[0])
		      .addComponent(expandWindowButton)
		);*/
		contentPane.add(comboContainer, BorderLayout.BEFORE_FIRST_LINE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == expandWindowButton) {
			this.setSize(600, 500);
		}
	}
}