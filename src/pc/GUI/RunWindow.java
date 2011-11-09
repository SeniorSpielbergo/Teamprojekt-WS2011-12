package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import TuringMachine.*;

public class RunWindow extends JFrame {
	
	private String[] description = {"LEGO-Tape", "Console-Tape", "Graphic-Tape"};
	protected TuringMachine machine;
	private JPanel inputContainer = new JPanel();
	private JPanel comboContainer = new JPanel();
	private JPanel runCancelContainer = new JPanel();
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	public RunWindow(TuringMachine machine) {
		this.machine = machine;
		JComboBox[] combo = new JComboBox[machine.getTapes()];
		JLabel[] tapeLabel = new JLabel[machine.getTapes()];
		JTextField[] tapeName = new JTextField[machine.getTapes()];
		JPanel[] tapePanel = new JPanel[machine.getTapes()];
		JTextField[] input = new JTextField[machine.getTapes()];
		JButton runButton = new JButton("run");
		InputMap inputMap = runButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMap.put(enter, "ENTER");
		runButton.getActionMap().put("ENTER", new ClickAction(runButton));
		JButton cancelButton = new JButton("cancel");
		KeyStroke cancel = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		inputMap.put(cancel, "ESC");
		runButton.getActionMap().put("ESC", new ClickAction(cancelButton));
		setTitle("Run");
		setSize(600, 250);
		setResizable(false);
		
		Container contentPane = this.getContentPane();
		
		BoxLayout layoutCombo = new BoxLayout(comboContainer, BoxLayout.Y_AXIS);
		comboContainer.setLayout(layoutCombo);
		BoxLayout runCancelCombo = new BoxLayout(runCancelContainer, BoxLayout.X_AXIS);
		runCancelContainer.setLayout(runCancelCombo);
		runCancelContainer.add(cancelButton);
		runCancelContainer.add(Box.createRigidArea(new Dimension(510,20)));
		runCancelContainer.add(runButton);
		
		// initialize combo boxes
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox();
			tapeLabel[i] = new JLabel("Tape" + i + ":");
			tapeName[i] = new JTextField(20);
			tapePanel[i] = new JPanel();
			input[i] = new JTextField(50);
			
			for (int j = 0; j < description.length; j++) {
				combo[i].addItem(description[j]);
			}
			tapePanel[i].add(tapeLabel[i]);
			tapePanel[i].add(tapeName[i]);
			tapePanel[i].add(combo[i]);
			comboContainer.add(tapePanel[i]);
			inputContainer.add(input[i]);
		}
		contentPane.add(runCancelContainer, BorderLayout.AFTER_LAST_LINE);
		// add to window and set layout
		tabbedPane.addTab("Input", inputContainer);
		tabbedPane.addTab("Tape settings", comboContainer);
		contentPane.add(tabbedPane);
	}
	
	public class ClickAction extends AbstractAction {
		private JButton button;
		
		public ClickAction(JButton button) {
			this.button = button;
		}
		
		public void actionPerformed(ActionEvent e) {
			button.doClick();
		}
	}
}