package gui;

import java.util.ArrayList;

import javax.swing.*;

import tape.TapeException;

import java.awt.*;
import java.awt.event.*;

import machine.*;

public class SimulationWindow extends JFrame{

	JScrollPane scrollpaneRight;
	JPanel panelToolbar;
	SimulationToolbar toolbar;
	ArrayList<tape.GraphicTape> graphicTapes = new ArrayList<tape.GraphicTape>();
	Machine currentMachine;
	boolean simulationPaused;
	Simulation sim;



	public SimulationWindow(Machine machine){
		this.simulationPaused = true;
		this.currentMachine = machine;
		for(int i = 0; i< currentMachine.getTapes().size(); i++){
			if(currentMachine.getTapes().get(i).getType() == tape.Tape.Type.GRAPHIC){
				graphicTapes.add((tape.GraphicTape)machine.getTapes().get(i)); 
			}
		}

		setTitle("Simulation of " +this.currentMachine.getName());

		setBounds(200,200,600,700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.panelToolbar = new SimulationToolbar();

		try {
			this.currentMachine.initTapes();
		}
		catch (TapeException e){
			try {
				this.currentMachine.shutdownTapes();
			} catch (TapeException e1) {
				System.out.println("Warning: The tapes couldn't be shutdown correctly.");
				e1.printStackTrace();
			}
			ErrorDialog.showError("The initialization of the tapes failed because of a tape exception.", e);
			return;
		}
		catch (RuntimeException e){
			try {
				this.currentMachine.shutdownTapes();
			} catch (TapeException e1) {
				System.out.println("Warning: The tapes couldn't be shutdown correctly.");
				e1.printStackTrace();
			}
			ErrorDialog.showError("The initialization of the tapes failed because of an undefined exception.", e);

			return;

		}
		setVisible(true);
		this.init();
	}

	public void init(){

		this.add(panelToolbar);
		for(int i = 0; i< graphicTapes.size(); i++){
			this.add(this.graphicTapes.get(i).getTapePanel());
		}


		try {
			this.currentMachine.writeInputWords();
			sim = this.currentMachine.createSimulation();
		}
		catch (TapeException e){
			try {
				this.currentMachine.shutdownTapes();
			} catch (TapeException e1) {
				System.out.println("Warning: The tapes couldn't be shutdown correctly.");
				e1.printStackTrace();
			}
			ErrorDialog.showError("The initialization of the tapes failed because of a tape exception.", e);
			return;
		}
		catch (RuntimeException e){
			try {
				this.currentMachine.shutdownTapes();
			} catch (TapeException e1) {
				System.out.println("Warning: The tapes couldn't be shutdown correctly.");
				e1.printStackTrace();
			}
			ErrorDialog.showError("The initialization of the tapes failed because of an undefined exception.", e);

			return;
		}

	}
	
//TODO shutdown tapes, if simwindow closed without finishing simulation
//	public void dispose(){
//		this.sim.getSimulationThread().stop();
//		
//		try{
//			this.currentMachine.shutdownTapes();
//		}
//		catch (TapeException e) {
//			System.out.println("Warning: The tapes couldn't be shutdown correctly.");
//			e.printStackTrace();
//			ErrorDialog.showError("The initialization of the tapes failed because of an undefined exception.", e);
//
//			return;
//
//		}
//	}
	
	public class SimulationToolbar extends JPanel implements ActionListener {
		JButton buttonPlay, buttonForward;
		JToolBar toolbar;
		public SimulationToolbar() {
			super(new BorderLayout());

			toolbar = new JToolBar("Functions");
			buttonPlay = new JButton("play");
			buttonForward = new JButton("forward");
			buttonPlay.addActionListener(this);
			buttonForward.addActionListener(this);
			toolbar.add(buttonPlay);
			toolbar.add(buttonForward);
			setPreferredSize(new Dimension(600, 130));
			this.add(toolbar, BorderLayout.NORTH);

		}

		public void actionPerformed( ActionEvent event){
			if(event.getSource().equals(buttonForward)){
				sim.resume();
				try{
					Thread.sleep(500);
				}
				catch(InterruptedException e){}
				sim.pause();
			} //not implemented yet

			else if(event.getSource().equals(buttonPlay)&& !sim.isSimulationAlreadyStarted()){
				try {
					simulationPaused = false;
					sim.start();
				}
				catch (RuntimeException e){
					ErrorDialog.showError("The simulation failed because of an undefined exception.", e);
				}
			}

			else if(event.getSource().equals(buttonPlay)&& sim.isSimulationAlreadyStarted()){

				if(simulationPaused){
					sim.resume();
				}
				else{
					sim.pause();
				}
				simulationPaused = !simulationPaused;
			}
		}
	}
}
