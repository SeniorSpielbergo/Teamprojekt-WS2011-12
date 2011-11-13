package GUI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import TuringMachine.*;

public class RunWindow extends JFrame implements ActionListener, KeyListener {
	
	static final long serialVersionUID = -3667258249137827980L;
	private String[] description = {"LEGO-Tape", "Console-Tape", "Graphic-Tape"};
	protected TuringMachine machine;
	private JPanel inputContainer;
	private JPanel comboContainer;
	private JScrollPane inputPane;
	private JScrollPane comboPane; 
	private JPanel runCancelContainer;
	private JTabbedPane tabbedPane;
	@SuppressWarnings("rawtypes")	// because of java7
	private JComboBox[] combo;
	private JLabel[] tapeLabel;
	private JTextField[] tapeName;
	private JPanel[] tapePanel;
	private JPanel[] inputPanel;
	private JTextField[] input;
	private JButton runButton;
	private JButton cancelButton;
	
	/**
	 * Constructs the run window
	 * @param machine Turing machine needed to show the run settings
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RunWindow(TuringMachine machine) {
		this.machine = machine;
		initRunWindow(machine.getNumberOfTapes());
		
		// set enter and escape button listener
		InputMap inputMap = runButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMap.put(enter, "ENTER");
		runButton.getActionMap().put("ENTER", new ClickAction(runButton));
		
		KeyStroke cancel = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		inputMap.put(cancel, "ESC");
		runButton.getActionMap().put("ESC", new ClickAction(cancelButton));
		cancelButton.addActionListener(this);
		
		// window title and size
		setTitle("Run");
		setSize(600, 250);
		this.setResizable(false);
		
		// layout
		Container contentPane = this.getContentPane();
		BoxLayout inputLayout = new BoxLayout(inputContainer, BoxLayout.Y_AXIS);
		inputContainer.setLayout(inputLayout);
		BoxLayout layoutCombo = new BoxLayout(comboContainer, BoxLayout.Y_AXIS);
		comboContainer.setLayout(layoutCombo);
		BoxLayout runCancel = new BoxLayout(runCancelContainer, BoxLayout.X_AXIS);
		runCancelContainer.setLayout(runCancel);
		runCancelContainer.add(cancelButton);
		runCancelContainer.add(Box.createHorizontalGlue());
		runCancelContainer.add(runButton);
		
		// initialize tape settings
		for (int i = 0; i < combo.length; i++) {
			String tapeType = this.machine.getTapes().get(i).getType();
			combo[i] = new JComboBox();
			tapeLabel[i] = new JLabel(this.machine.getTapes().get(i).getName());
			tapeName[i] = new JTextField(this.machine.getTapes().get(i).getName(), 20);
			tapeName[i].addKeyListener(this);
			tapePanel[i] = new JPanel();
			inputPanel[i] = new JPanel();
			input[i] = new JTextField(20);
			
			for (int j = 0; j < description.length; j++) {
				combo[i].addItem(description[j]);
				if (tapeType.equals("LEGO")) {
					combo[i].setSelectedItem(description[0]);
				}
				else if (tapeType.equals("console")) {
					combo[i].setSelectedItem(description[1]);
				}
				else if (tapeType.equals("gui")) {
					combo[i].setSelectedItem(description[2]);
				}
			}
			tapePanel[i].add(tapeName[i]);
			tapePanel[i].add(combo[i]);
			inputPanel[i].add(tapeLabel[i]);
			inputPanel[i].add(input[i]);
			comboContainer.add(tapePanel[i]);
			input[i].setText(machine.getTapes().get(i).getInputWord());
			inputContainer.add(inputPanel[i]);
		}
		inputPane = new JScrollPane(inputContainer);
		comboPane = new JScrollPane(comboContainer);
		contentPane.add(runCancelContainer, BorderLayout.AFTER_LAST_LINE);
		
		tabbedPane.addTab("Input", inputPane);
		tabbedPane.addTab("Tape settings", comboPane);
		contentPane.add(tabbedPane);
	}
	
	/**
	 * Initializes the window
	 * @param tapes Number of tapes
	 */
	public void initRunWindow(int tapes) {
		inputContainer  = new JPanel();
		comboContainer = new JPanel();
		runCancelContainer = new JPanel();
		tabbedPane = new JTabbedPane();
		combo  = new JComboBox[tapes];
		tapeLabel = new JLabel[tapes];
		tapeName = new JTextField[tapes];
		tapePanel = new JPanel[tapes];
		inputPanel = new JPanel[tapes];
		input = new JTextField[tapes];
		runButton = new JButton("run");
		cancelButton = new JButton("cancel");
	}
	
	/**
	 * ActionListener for the buttons
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			this.setVisible(false);
		}
		else if(e.getSource() == runButton) {
			JOptionPane.showMessageDialog(null, "Not implemented yet!");
		}
	}
	
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < tapeName.length; i++) {
			tapeLabel[i].setText(tapeName[i].getText());
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * Used for the window focused actions
	 */
	public class ClickAction extends AbstractAction {
		static final long serialVersionUID = -3667258249137827980L;
		private JButton button;
		
		public ClickAction(JButton button) {
			this.button = button;
		}
		
		public void actionPerformed(ActionEvent e) {
			button.doClick();
		}
	}
}