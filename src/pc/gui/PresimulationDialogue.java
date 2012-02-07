package gui;

import javax.swing.*;

import tape.ConsoleTape;
import tape.TapeException;

import machine.Machine;
import machine.Simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The dialogue for presimulating within presimulation.
 * @author Nessa Baier
 *
 */
public class PresimulationDialogue extends JDialog implements ActionListener{
	private static final long serialVersionUID = 5423418738498074784L;

	/**
	 * Represents the return values for this window
	 */
	public enum ReturnValue {
		CANCEL, SIMULATE
	}

	/**
	 * Panels for text and buttons.
	 */
	JPanel panelText, panelButtons;

	/**
	 * Labels for text.
	 */
	JLabel labelText, labelDuration;

	/**
	 * Buttons for cancelling and simulating.
	 */
	JButton buttonCancel, buttonSimulate;

	/**
	 * The current machine (copy).
	 */
	Machine machine;

	/**
	 * The simulation object for presimulating.
	 */
	Simulation sim;

	/**
	 * Predicted duration of the simulation represented as string.
	 */
	String duration;

	/**
	 * The predicted duration of the simulation.
	 */
	double simulationTime;

	/**
	 * Stores the value returned by the dialog
	 */
	private ReturnValue returnValue;

	/**
	 * Creates a presimulation dialogue.
	 * @param clonedMachine The current machine as copy.
	 */
	PresimulationDialogue(Machine clonedMachine){
		this.setModal(true);
		duration = new String();
		this.panelText = new JPanel();
		this.panelButtons = new JPanel();
		this.labelText = new JLabel();
		this.labelDuration = new JLabel();
		this.buttonCancel = new JButton("Cancel");
		this.buttonSimulate = new JButton("Simulate");
		this.machine = clonedMachine;
		this.init();

	}

	/*
	 * Initializes the dialogue with its machine, its new tapes(console tapes only) and the presimulation.
	 */
	private void init(){

		//dialogue
		this.setTitle("Presimulation");
		this.setLayout(new GridBagLayout());
		this.setSize(350, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.centerDialogOnTheScreen();


		this.panelText.setLayout(new GridLayout(3,0));
		this.panelText.add(this.labelText);
		this.panelText.add(this.labelDuration);


		//presimulation

		int numberOfTapes = this.machine.getNumberOfTapes();
		for(int i = 0; i < numberOfTapes; i++){
			ConsoleTape t = new ConsoleTape();
			try {
				t.setInputWord(machine.getTapes().get(i).getInputWord());
			} catch (TapeException e1) {
				ErrorDialog.showError("Error changing Tapetype", e1);
			}	
			this.machine.getTapes().set(i,t);
		}

		try {
			this.machine.initTapes();
			this.machine.writeInputWords();
			this.machine.getTapes().get(0).getWriteInputWordThread().join();
			this.sim = this.machine.createSimulation();
			this.sim.getSimulationThread().start();
			sim.getSimulationThread().join();
		} 

		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (TapeException e) {
			e.printStackTrace();
		}

		//computing time and put it to stringformat
		if(this.sim.getNumberOfSteps() < 1001){
			this.labelText.setText("The simulation needs " +Integer.toString(this.sim.getNumberOfSteps()) +" Steps." );
		}
		else{
			this.labelText.setText("The simulation needs many many Steps." );
			this.labelDuration.setText("It maybe needs much more longer then you want!");
			this.setTitle("Warning!!!");
		}
		if(this.simulationTime()/60 < 1){
			this.labelDuration.setText("This will last about " +Integer.valueOf((int)this.simulationTime) +" seconds.");
		}
		else if(this.simulationTime < 1001){
			String minutes;
			String seconds;
			if((int)this.simulationTime/60 < 10)
				minutes = "0" +Integer.valueOf((int)this.simulationTime/60);

			else{
				minutes = ""+Integer.valueOf((int)this.simulationTime/60);
			}
			if((int)this.simulationTime%60 < 10)
				seconds = "0" +Integer.valueOf((int)this.simulationTime%60);

			else{
				seconds = ""+Integer.valueOf((int)this.simulationTime%60);
			}
			this.labelDuration.setText("This will last about "+minutes +":" 
					+ seconds +" minutes.");
		}

		//buttons
		panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelButtons.add(buttonCancel);
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		panelButtons.add(this.buttonSimulate);

		this.buttonCancel.addActionListener(this);
		this.buttonSimulate.addActionListener(this);

		getRootPane().setDefaultButton(this.buttonSimulate);

		GridBagConstraints windowConstraints = new GridBagConstraints();
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 0;
		windowConstraints.weightx = 1.0;
		windowConstraints.weighty = 0.05;
		this.add(this.panelText);
		windowConstraints.gridy = 1;

		this.add(this.panelButtons, windowConstraints);
	}

	/**
	 * Shows the dialogue.
	 * @return RUN/CANCEL depending on the user decision.
	 */
	public ReturnValue showDialogue() {
		this.setVisible(true);

		return returnValue;
	}

	/*
	 * Computes the simulation time.
	 * @return simulationTime simulation time as double in seconds.
	 */
	private double simulationTime(){
		if(this.machine.getType() == Machine.MachineType.BrainfuckMachine){
			this.simulationTime = this.sim.getNumberOfSteps();
		}
		else if(this.machine.getType() == Machine.MachineType.TuringMachine){
			this.simulationTime= (this.sim.getNumberOfMovements() + this.sim.getNumberOfWritings());
		}
		return this.simulationTime;
	}

	/**
	 * Handles the button events.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonCancel){
			dispose();
			this.returnValue = ReturnValue.CANCEL;
		}
		else if(e.getSource() == this.buttonSimulate){
			dispose();
			this.returnValue = ReturnValue.SIMULATE;
		}
	}

	/*
	 * Center the dialog on the screen.
	 */
	private void centerDialogOnTheScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dialogSize = this.getSize();
		int centerPosX = (screenSize.width - dialogSize.width) / 2;
		int centerPosY = (screenSize.height - dialogSize.height) / 2;
		setLocation(centerPosX, centerPosY);
	}

}
