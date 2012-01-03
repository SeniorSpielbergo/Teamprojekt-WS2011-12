package gui.turing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

import java.awt.event.*;

@SuppressWarnings("serial")
/**
 * Dialogue to create a new turingmachine.
 * @author Nessa Baier
 *
 */
public class NewTMDialogue extends JDialog implements ActionListener, ChangeListener, KeyListener {
	/**
	 * Enum for the buttonevents.
	 *
	 */
	public enum ReturnValue {
		CANCEL, CREATE
	}

	/**
	 * Panels for the dialogue.
	 */
	private JPanel panelName, panelTapes, panelButtons;
	
	/**
	 * Labels for the panels.
	 */
	private JLabel name, tapes;
	/**
	 * Textfield for the name of the turingmachine.
	 */
	private	JTextField fieldName;
	/**
	 * Spinner for the number of tapes.
	 */
	private JSpinner spinnerNumberOfTapes;
	/**
	 * Buttons "create" and "cancel"
	 */
	private JButton buttonCancel, buttonCreate;
	/**
	 * Return value for the pressed button.
	 */
	private  ReturnValue returnValue;
	/**
	 * Stores the numberOfTapes
	 */
	private int numberOfTapes = 1;
	
	private JTextField spinnerTextField;

	/**
	 * Creates a new dialogue.
	 */
	public NewTMDialogue(){

		//machine's name
		name = new JLabel();
		panelName = new JPanel();
		name.setText("Name");
		panelName.add(name);

		fieldName = new JTextField(10);
		panelName.add(fieldName);

		//number of tapes
		tapes = new JLabel();
		panelTapes = new JPanel();
		tapes.setText("Tapes");
		panelTapes.add(tapes);
		
		SpinnerModel tapemodel = new SpinnerNumberModel(1,1,10,1);
		spinnerNumberOfTapes = new JSpinner(tapemodel);
		spinnerNumberOfTapes.addChangeListener(this);
		spinnerTextField = ((JSpinner.DefaultEditor) spinnerNumberOfTapes.getEditor()).getTextField();
		spinnerTextField.addKeyListener(this);
		panelTapes.add(spinnerNumberOfTapes);

		//Buttons
		buttonCreate = new JButton("Create");
		buttonCreate.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);

		panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelButtons.add(buttonCancel);
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		panelButtons.add(buttonCreate);
		
		getRootPane().setDefaultButton(buttonCreate);

		//dialogue window
		setTitle("New TuringMachine");
		this.setModal(true);
		this.setBounds(500,250,300,150);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(panelName, BorderLayout.NORTH);
		this.add(panelTapes, BorderLayout.CENTER);
		this.add(panelButtons, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource().equals(buttonCancel)){
			returnValue = ReturnValue.CANCEL;
			this.setVisible(false);
			dispose();

		}

		else if(e.getSource().equals(buttonCreate)){
			returnValue = ReturnValue.CREATE;
			this.setVisible(false);
			dispose();
		}

	}
	
	/**
	 * Returns the typed name for the new machine.
	 * @return name Name of the machine
	 */
	public String getMachinesName(){
		return fieldName.getText();
	}
	
	/**
	 * Returns the chosen number of tapes.
	 * @return numberOfTapes Number of tapes
	 */
	public int getNumberOfTapes(){
		return numberOfTapes;
	}
	
	/**
	 * Returns which button was pressed, cancel or create.
	 * @return returnValue Returns the user decision (cancel / create)
	 */
	public ReturnValue showDialogue(){
		this.setVisible(true);
		return returnValue;
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == spinnerNumberOfTapes) {
			numberOfTapes = (Integer) this.spinnerNumberOfTapes.getValue();
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyReleased(KeyEvent e) {
		numberOfTapes = Integer.parseInt(spinnerTextField.getText());
		if (numberOfTapes > 10) {
			numberOfTapes = 10;
		}
		spinnerNumberOfTapes.setValue(numberOfTapes);
	}
}

