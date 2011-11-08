package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame implements ActionListener {
	
	private String[] description = {"LEGO-Tape", "Console-Tape", "Graphic-Tape"};
	protected TuringMachine machine;
	private JButton expandWindowButton;
	private boolean isExpanded = false;
	private JPanel inputContainer = new JPanel();
	private JPanel comboContainer = new JPanel();
	private JPanel expandContainer = new JPanel();
	
	public RunWindow(TuringMachine machine) {
		expandWindowButton = new JButton("expand Options");
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		JLabel[] tapeLabel = new JLabel[machine.getTapes()];
		JTextField[] tapeName = new JTextField[machine.getTapes()];
		JPanel[] tapePanel = new JPanel[machine.getTapes()];
		setTitle("Run");
		setSize(600, 150);
		
		Container contentPane = this.getContentPane();
		
		JTextField input = new JTextField(50);
		input.setText(machine.getInitial().toString());
		inputContainer.add(input);
		expandContainer.add(expandWindowButton);
		comboContainer.setBorder(BorderFactory.createTitledBorder("Tapes"));
		inputContainer.setBorder(BorderFactory.createTitledBorder("Input"));
		expandWindowButton.addActionListener(this);
		BoxLayout layout = new BoxLayout(comboContainer, BoxLayout.Y_AXIS);
		comboContainer.setLayout(layout);
		
		// initialize combo boxes
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox();
			tapeLabel[i] = new JLabel("Tape" + i + ":");
			tapeName[i] = new JTextField(20);
			tapePanel[i] = new JPanel();
			
			for (int j = 0; j < description.length; j++) {
				combo[i].addItem(description[j]);
			}
			tapePanel[i].add(tapeLabel[i]);
			tapePanel[i].add(tapeName[i]);
			tapePanel[i].add(combo[i]);
			comboContainer.add(tapePanel[i]);
			
		}
		comboContainer.setVisible(false);
		// add to window and set layout
		contentPane.add(inputContainer, BorderLayout.BEFORE_FIRST_LINE);
		contentPane.add(expandContainer, BorderLayout.WEST);		
		contentPane.add(comboContainer, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == expandWindowButton) {
			if (!isExpanded){
				int comboHeight = comboContainer.getHeight();
				this.setSize(600, 175 + comboHeight);
				comboContainer.setVisible(true);
				isExpanded = true;
			} else {
				comboContainer.setVisible(false);
				this.setSize(600, 150);
				isExpanded = false;
			}
		}
	}
}