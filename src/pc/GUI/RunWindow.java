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
		JLabel[] tapeLabel = new JLabel[machine.getTapes()];
		JTextField[] tapeName = new JTextField[machine.getTapes()];
		setTitle("Run");
		setSize(600, 200);
		
		Container contentPane = this.getContentPane();
		JPanel inputContainer = new JPanel();
		JPanel comboContainer = new JPanel();
		JPanel expandContainer = new JPanel();
		JTextField input = new JTextField(50);
		inputContainer.add(input);
		expandContainer.add(expandWindowButton);
		comboContainer.setBorder(BorderFactory.createTitledBorder("Tapes"));
		inputContainer.setBorder(BorderFactory.createTitledBorder("Input"));
		expandWindowButton.addActionListener(this);
		
		// initialize combo boxes
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox();
			tapeLabel[i] = new JLabel("Tape" + i + ":");
			tapeName[i] = new JTextField(20);
			
			for (int j = 0; j < description.length; j++) {
				combo[i].addItem(description[j]);
			}
			comboContainer.add(tapeLabel[i]);
			comboContainer.add(tapeName[i]);
			comboContainer.add(combo[i]);
		}
		
		// add to window and set layout
		contentPane.add(inputContainer, BorderLayout.BEFORE_FIRST_LINE);
		contentPane.add(comboContainer, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == expandWindowButton) {
			this.setSize(600, 500);
		}
	}
}