package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import tape.*;
import machine.Machine;

/** This class represents window for the run settings.
 * @author David Wille
 * @author Philipp Neumann
 */
public class RunWindow extends JDialog implements ActionListener, KeyListener {

	/**
	 * Represents the return values for this window.
	 */
	public enum ReturnValue {
		CANCEL, RUN
	}

	static final long serialVersionUID = -3667258249137827980L;
	private JPanel inputContainer;
	private JPanel tapeSettingsContainer;
	private JPanel robotSettingsContainer;
	private JScrollPane inputPane;
	private JScrollPane tapeSettingsPane;
	private JScrollPane robotSettingsPane;
	private JPanel runCancelContainer;
	private JTabbedPane tabbedPane;
	@SuppressWarnings({"rawtypes"})	// because of java7
	private JComboBox[] tapeCombo;
	@SuppressWarnings("rawtypes")	// because of java7
	private JComboBox[] robotCombo1;
	@SuppressWarnings("rawtypes")	// because of java7
	private JComboBox[] robotCombo2;
	private JLabel[] tapeLabel;
	private JLabel[] robotTapeLabel;
	private JTextField[] tapeName;
	private JTextField[] tapeInput;
	private JButton runButton;
	private JButton cancelButton;
	private PresimulationDialogue presimDialogue;
	private ArrayList<ArrayList<Object>> robots = new ArrayList<ArrayList<Object>>();
	private final String[] description = {"LEGO-Tape", "Console-Tape", "Graphic-Tape"};
	private Machine machine;
	private ReturnValue returnValue;

	/**
	 * Constructs the run window.
	 * @param currentMachine Turing machine needed to show the run settings.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })		// because of java7
	public RunWindow(Machine currentMachine) {
		this.setModal(true);
		this.machine = currentMachine;

		// window title and size
		this.setTitle("Run");
		this.setSize(700, 250);
		this.setResizable(false);

		// initialize
		initRunWindow(currentMachine.getNumberOfTapes());

		// set enter and escape button listener
		InputMap inputMap = runButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMap.put(enter, "ENTER");
		runButton.getActionMap().put("ENTER", new ClickAction(runButton));

		KeyStroke cancel = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		inputMap.put(cancel, "ESC");
		runButton.getActionMap().put("ESC", new ClickAction(cancelButton));
		cancelButton.addActionListener(this);
		runButton.addActionListener(this);
		this.getRootPane().setDefaultButton(runButton);

		// layout for run and cancel button
		Container contentPane = this.getContentPane();
		BoxLayout runCancel = new BoxLayout(runCancelContainer, BoxLayout.X_AXIS);
		runCancelContainer.setLayout(runCancel);
		runCancelContainer.add(cancelButton);
		runCancelContainer.add(Box.createHorizontalGlue());
		runCancelContainer.add(runButton);

		// show window content
		for (int i = 0; i < tapeCombo.length; i++) {
			Tape.Type tapeType = this.machine.getTapes().get(i).getType();
			GridBagConstraints c = new GridBagConstraints();

			// initialize the fields to display the content
			tapeCombo[i] = new JComboBox();
			robotCombo1[i] = new JComboBox();
			robotCombo2[i] = new JComboBox();
			tapeLabel[i] = new JLabel(this.machine.getTapes().get(i).getName());
			tapeName[i] = new JTextField(this.machine.getTapes().get(i).getName(), 20);
			tapeName[i].addKeyListener(this);
			tapeInput[i] = new JTextField(20);
			if (!this.machine.getTapes().get(i).getInputAllowed()) {
				tapeInput[i].setEditable(false);
			}

			robotCombo1[i].addActionListener(createItemListener(i));
			robotCombo2[i].addActionListener(createItemListener(i));

			// initialize the combo boxes
			for (int j = 0; j < description.length; j++) {
				tapeCombo[i].addItem(description[j]);
				tapeCombo[i].addActionListener(createItemListener(i));
				if (tapeType == Tape.Type.LEGO) {
					tapeCombo[i].setSelectedItem(description[0]);
				}
				else if (tapeType == Tape.Type.CONSOLE) {
					tapeCombo[i].setSelectedItem(description[1]);
				}
				else if (tapeType == Tape.Type.GRAPHIC) {
					tapeCombo[i].setSelectedItem(description[2]);
				}
			}
			// set input word for tape i
			tapeInput[i].setText(currentMachine.getTapes().get(i).getInputWord()); 

			// layout for tape settings tab
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			tapeSettingsContainer.add(tapeName[i], c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			tapeSettingsContainer.add(tapeCombo[i], c);
			// layout for input tab
			robotTapeLabel[i] = new JLabel();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			inputContainer.add(tapeLabel[i], c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			inputContainer.add(tapeInput[i], c);
			// layout for robots tab
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			robotSettingsContainer.add(robotTapeLabel[i], c);
			for (int j = 0; j < robots.size(); j++) {
				if ((Boolean) robots.get(j).get(2)) {
					robotCombo1[i].addItem(robots.get(j).get(0) + " - " + robots.get(j).get(1));
				}
				else {
					robotCombo2[i].addItem(robots.get(j).get(0) + " - " + robots.get(j).get(1));
				}
			}
			// initialize robots
			if (tapeType == Tape.Type.LEGO) {
				LEGOTape tape = (LEGOTape) this.machine.getTapes().get(i);
				String masterName = tape.getMaster().getName();
				String masterMac = tape.getMaster().getMacAddress();
				String slaveName = tape.getSlave().getName();
				String slaveMac = tape.getSlave().getMacAddress();
				robotCombo1[i].setSelectedItem(masterName + " - " + masterMac);
				robotCombo2[i].setSelectedItem(slaveName + " - " + slaveMac);
			}
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			robotSettingsContainer.add(robotCombo1[i], c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 2;
			c.gridy = i;
			c.insets = new Insets(5,5,5,5);
			robotSettingsContainer.add(robotCombo2[i], c);
		}
		// add scroll bars
		inputPane = new JScrollPane(inputContainer);
		tapeSettingsPane = new JScrollPane(tapeSettingsContainer);
		robotSettingsPane = new JScrollPane(robotSettingsContainer);

		// add run and cancel button at the end
		contentPane.add(runCancelContainer, BorderLayout.AFTER_LAST_LINE);

		// add tabs
		tabbedPane.addTab("Input", inputPane);
		tabbedPane.addTab("Tape settings", tapeSettingsPane);
		tabbedPane.addTab("Robot settings", robotSettingsPane);

		// add to window
		contentPane.add(tabbedPane);
		refreshRobotSettings();
	}

	private void refreshTapes(int tape, Tape.Type type) {
		if (type ==  Tape.Type.LEGO) {
			if (robots.size() > 0) {
				int robotNumber1 = 0;
				int robotNumber2 = 0;
				for (int i = 0; i < robots.size(); i++) {
					if (robotCombo1[tape].getSelectedItem().equals("" + robots.get(i).get(0) + " - " + robots.get(i).get(1))) {
						robotNumber1 = i;
					}
					if (robotCombo2[tape].getSelectedItem().equals("" + robots.get(i).get(0) + " - " + robots.get(i).get(1))) {
						robotNumber2 = i;
					}
				}
				MasterRobot master = new MasterRobot(robots.get(robotNumber1).get(0).toString(), robots.get(robotNumber1).get(1).toString());
				SlaveRobot slave = new SlaveRobot(robots.get(robotNumber2).get(0).toString(), robots.get(robotNumber2).get(1).toString());
				Tape tape_lego = new LEGOTape(machine.getTapes().get(tape).getName(), master, slave, machine.getTapes().get(tape).getInputAllowed());
				try {
					tape_lego.setInputWord(machine.getTapes().get(tape).getInputWord());
				} catch (TapeException e1) {
					ErrorDialog.showError("Error changing Tapetype", e1);
				}						
				machine.getTapes().remove(tape);
				machine.getTapes().add(tape, tape_lego);
			}
		}
		else if (type == Tape.Type.CONSOLE) {
			Tape tape_console = new ConsoleTape(machine.getTapes().get(tape).getName(), machine.getTapes().get(tape).getInputAllowed());

			try {
				tape_console.setInputWord(machine.getTapes().get(tape).getInputWord());
			} catch (TapeException e1) {
				ErrorDialog.showError("Error changing Tapetype", e1);
			}						
			machine.getTapes().remove(tape);						
			machine.getTapes().add(tape, tape_console);
		}
		else if (type == Tape.Type.GRAPHIC) {
			Tape tape_graphic = new GraphicTape(machine.getTapes().get(tape).getName(),
					machine.getTapes().get(tape).getInputAllowed());
			try {
				tape_graphic.setInputWord(machine.getTapes().get(tape).getInputWord());
			} catch (TapeException e1) {
				ErrorDialog.showError("Error changing Tapetype", e1);
			}						
			machine.getTapes().remove(tape);						
			machine.getTapes().add(tape, tape_graphic);
		}
	}

	private void refreshRobotSettings() {
		ArrayList<Tape> tapes = this.machine.getTapes();
		int counter = 0;
		// make labels and combo boxes visible
		for (int i = 0; i < tapes.size(); i++) {
			if (tapes.get(i).getType() == Tape.Type.LEGO) {
				robotTapeLabel[i].setText(tapeName[i].getText());
				robotTapeLabel[i].setVisible(true);
				robotCombo1[i].setVisible(true);
				robotCombo2[i].setVisible(true);
				counter++;
			}
			else if (tapes.get(i).getType() == Tape.Type.CONSOLE || tapes.get(i).getType() == Tape.Type.GRAPHIC) {
				robotTapeLabel[i].setVisible(false);
				robotCombo1[i].setVisible(false);
				robotCombo2[i].setVisible(false);
			}
		}
		// hide/unhide robots tab
		if (counter == 0) {
			tabbedPane.setEnabledAt(2, false);
		}
		else {
			tabbedPane.setEnabledAt(2, true);
		}
	}

	private void initRunWindow(int tapes) {
		try {
			robots = OrganizeRobots.loadRobotsFromXML();
		}
		catch (Exception e) {
			ErrorDialog.showError("Parsing the XML file failed.", e);
		}
		inputContainer  = new JPanel(new GridBagLayout());
		tapeSettingsContainer = new JPanel(new GridBagLayout());
		robotSettingsContainer = new JPanel(new GridBagLayout());
		runCancelContainer = new JPanel();
		runCancelContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		tabbedPane = new JTabbedPane();
		tapeCombo  = new JComboBox[tapes];
		robotCombo1 = new JComboBox[tapes];
		robotCombo2 = new JComboBox[tapes];
		robotTapeLabel = new JLabel[tapes];
		tapeLabel = new JLabel[tapes];
		tapeName = new JTextField[tapes];
		tapeInput = new JTextField[tapes];
		runButton = new JButton("Run");
		cancelButton = new JButton("Cancel");
	}

	/**
	 * Shows the window.
	 * @return RUN/CANCEL depending on the user decision
	 */
	public ReturnValue showDialog() {
		this.setVisible(true);

		return returnValue;
	}

	private void updateTapeWords() {
		for (int i = 0; i < tapeInput.length; i++) {
			Tape tempTape = this.machine.getTapes().get(i);
			try {
				tempTape.setInputWord(tapeInput[i].getText());
			}
			catch (TapeException te) {
				ErrorDialog.showError("Error while setting new input word!", te);
			}
		}
	}

	// Checks if robots are assigned to more than one tape
	private boolean checkRobots() {
		int[] counter = new int[robots.size()];
		// initialize counter
		for (int i = 0; i < robots.size(); i++) {
			counter[i] = 0;
		}
		// count for every robot
		for (int i = 0; i < tapeCombo.length; i++) {
			Tape tempTape = this.machine.getTapes().get(i);
			if (tempTape.getType() == Tape.Type.LEGO) {
				for (int j = 0; j < robots.size(); j++) {
					if (robotCombo1[i].getSelectedItem().equals("" + robots.get(j).get(0) + " - " + robots.get(j).get(1))) {
						counter[j]++;
					}
					if (robotCombo2[i].getSelectedItem().equals("" + robots.get(j).get(0) + " - " + robots.get(j).get(1))) {
						counter[j]++;
					}
				}
			}
		}
		// check if robots are assigned to > 1 tape
		for (int i = 0; i < robots.size(); i++) {
			if (counter[i] > 1) {
				return false;
			}
		}
		return true;
	}

	private ActionListener createItemListener(final int index) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getTabCount() == 3) {
					if (e.getSource().equals(tapeCombo[index])) {
						if (tapeCombo[index].getSelectedItem().toString() == description[0]) {
							refreshTapes(index, Tape.Type.LEGO);
							refreshRobotSettings();
						}
						else if (tapeCombo[index].getSelectedItem().toString() == description[1]) {
							refreshTapes(index, Tape.Type.CONSOLE);
							refreshRobotSettings();
						}
						else if (tapeCombo[index].getSelectedItem().toString() == description[2]) {
							refreshTapes(index, Tape.Type.GRAPHIC);
							refreshRobotSettings();
						}
					}
					else if (e.getSource().equals(robotCombo1[index]) || e.getSource().equals(robotCombo2[index])) {
						refreshTapes(index, Tape.Type.LEGO);
					}
				}
			}
		};
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			updateTapeWords();
			this.setVisible(false);
			dispose();
			returnValue = ReturnValue.CANCEL;
		}
		else if(e.getSource() == runButton) {
			updateTapeWords();
			if (checkRobots()) {
				this.presimDialogue = new PresimulationDialogue((Machine)this.machine.clone());
				if(this.presimDialogue.showDialogue() == PresimulationDialogue.ReturnValue.SIMULATE){
					this.setVisible(false);
					dispose();
					returnValue = ReturnValue.RUN;}
			}
			else {
				ErrorDialog.showError("Each roboter can just be assigned to one tape at a time!");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < tapeName.length; i++) {
			tapeLabel[i].setText(tapeName[i].getText());
			this.machine.getTapes().get(i).setName(tapeName[i].getText());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Used for the window focused actions.
	 */
	public class ClickAction extends AbstractAction {
		static final long serialVersionUID = -3667258249137827980L;
		private JButton button;

		/**
		 * Constructs a new ClickAction.
		 * @param button Button that should get a new ClickAction
		 */
		public ClickAction(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			button.doClick();
		}
	}
}