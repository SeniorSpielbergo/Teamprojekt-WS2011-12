package gui;

import gui.RunWindow.ReturnValue;

import javax.swing.*;

import tape.ConsoleTape;
import tape.TapeException;

import machine.Machine;
import machine.Simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresimulationDialogue extends JDialog implements ActionListener{

	/**
	 * Represents the return values for this window
	 */
	public enum ReturnValue {
		CANCEL, SIMULATE
	}

	JPanel panelText, panelButtons;
	JLabel labelText, labelDuration;
	JButton buttonCancel, buttonSimulate;
	Machine machine;
	Simulation sim;
	String duration;
	/**
	 * Stores the value returned by the dialog
	 */
	private ReturnValue returnValue;

	PresimulationDialogue(Machine clonedMachine){
		this.setModal(true);
		duration = new String();
		this.panelText = new JPanel();
		this.panelButtons = new JPanel();
		this.labelText = new JLabel("The expected duration will be about ");
		this.labelDuration = new JLabel();
		this.buttonCancel = new JButton("Cancel");
		this.buttonSimulate = new JButton("Simulate");
		this.machine = clonedMachine;
		this.init();

	}

	private void init(){
		this.setTitle("Presimulation");
		this.setLayout(new FlowLayout());
		this.panelText.setLayout(new FlowLayout());
		this.panelText.add(this.labelText);
		this.panelText.add(this.labelDuration);

		this.buttonCancel.addActionListener(this);
		this.buttonSimulate.addActionListener(this);
		panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		panelButtons.add(buttonCancel);
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		panelButtons.add(this.buttonSimulate);

		getRootPane().setDefaultButton(this.buttonSimulate);
		
		

		this.add(this.panelText);
		this.add(this.panelButtons);
		this.setSize(250, 150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		int numberOfTapes = this.machine.getNumberOfTapes();

		for(int i = 0; i < numberOfTapes; i++){
			ConsoleTape t = new ConsoleTape();
			try {
				t.setInputWord(machine.getTapes().get(i).getInputWord()); //FIXME: does not work
			} catch (TapeException e1) {
				ErrorDialog.showError("Error changing Tapetype", e1);
			}	
			this.machine.getTapes().set(i,t);
		}

		try {
			this.machine.initTapes();
			this.machine.writeInputWords();
			this.sim = this.machine.createSimulation();
			this.sim.getSimulationThread().start();
			sim.getSimulationThread().join();
		} 

		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (TapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		duration = Integer.toString(this.sim.getNumberOfSteps());
		//duration = String.valueOf(this.sim.getNumberOfSteps());
		this.labelDuration.setText(duration +"Steps.");

	}

	/**
	 * Shows the window
	 * @return RUN/CANCEL depending on the user decision
	 */
	public ReturnValue showDialogue() {
		this.setVisible(true);

		return returnValue;
	}

	@Override
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

}
