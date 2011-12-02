package gui;

import java.util.ArrayList;

import javax.swing.*;

import tape.TapeException;

import java.awt.*;
import java.awt.event.*;

import machine.*;

/**
 * This class is the window in which runs the simulation with the tapes.
 * @author Nessa Baier
 *
 */
@SuppressWarnings("serial")
public class SimulationWindow extends JFrame{
	/**
	 * ScrollPane
	 */
	JScrollPane scrollpaneRight;
	/**
	 * panel for toolbar, panel for tapes
	 */
	JPanel panelall, panelToolbar;
	/**
	 * toolbar
	 */
	SimulationToolbar toolbar;
	/**
	 * the simulation's graphic tapes
	 */
	ArrayList<tape.GraphicTape> graphicTapes = new ArrayList<tape.GraphicTape>();
	/**
	 * Current machine
	 */
	Machine currentMachine;
	/**
	 * true if simulation is/should be paused
	 */
	boolean simulationPaused;
	/**
	 * current simulation
	 */
	Simulation sim;

	/**
	 * Creates a new window for the simulation.
	 * @param machine
	 */
	public SimulationWindow(Machine machine){
		this.simulationPaused = true;
		this.currentMachine = machine;
		for(int i = 0; i< currentMachine.getTapes().size(); i++){
			if(currentMachine.getTapes().get(i).getType() == tape.Tape.Type.GRAPHIC){
				graphicTapes.add((tape.GraphicTape)machine.getTapes().get(i)); 
			}
		}

		this.setTitle("Simulation of " +this.currentMachine.getName());
		this.setLayout(new GridBagLayout());
		this.setBounds(200,200,600,700);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.panelall = new JPanel(new GridBagLayout());
		this.scrollpaneRight = new JScrollPane(panelall);
		this.panelToolbar = new SimulationToolbar();

		//initialize tapes
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

	/**
	 * Initializes window's contents.
	 */
	private void init(){
		//adding toolbar to window
		GridBagConstraints windowConstraints = new GridBagConstraints();
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 0;
		windowConstraints.weightx = 1.0;
		windowConstraints.weighty = 0.05;
		this.add(panelToolbar, windowConstraints);
		
		//adding tapes to panel
		GridBagConstraints panelallConstraints = new GridBagConstraints();
		for(int i = 0; i< graphicTapes.size(); i++){
			panelallConstraints.gridx = 0;
			panelallConstraints.gridy = i;
			panelallConstraints.weightx = 1.0;
			panelallConstraints.fill = GridBagConstraints.HORIZONTAL;
			this.panelall.add(this.graphicTapes.get(i).getTapePanel(),panelallConstraints);
		}
		JPanel gap = new JPanel();
		panelallConstraints.fill = GridBagConstraints.BOTH;
		panelallConstraints.weighty = 1.0;
		panelallConstraints.gridy = graphicTapes.size();
		panelallConstraints.gridx = 0;
		this.panelall.add(gap,panelallConstraints);

		//writing input words
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
		
		//adding scrollpane to window
		windowConstraints.gridx = 0;
		windowConstraints.gridy = 1;
		windowConstraints.weightx = 1.0;
		windowConstraints.weighty = 0.95;
		windowConstraints.fill = GridBagConstraints.BOTH;
		this.add(scrollpaneRight, windowConstraints);
	}

	//TODO shutdown tapes, if simwindow closed without finishing simulation
	public void dispose(){
		if(this.sim.isSimulationAlreadyStarted()){

		}
		try{
			this.currentMachine.shutdownTapes();
		}
		catch (TapeException e) {
			System.out.println("Warning: The tapes couldn't be shutdown correctly.");
			e.printStackTrace();
			ErrorDialog.showError("The initialization of the tapes failed because of an undefined exception.", e);
			return;
		}
	}


	/**
	 * This class is the toolbar panel.
	 * @author Nessa Baier
	 *
	 */
	public class SimulationToolbar extends JPanel implements ActionListener {
		/**
		 * Buttons.
		 */
		JButton buttonPlay, buttonForward;
		/**
		 * Toolbar.
		 */
		JToolBar toolbar;
		/**
		 * Creates a new toolbar. 
		 */
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

		/**
		 * This method handles button events.
		 */
		public void actionPerformed( ActionEvent event){ //TODO inactivate buttons while writing input word
			//forward button
			if(event.getSource().equals(buttonForward)){
				sim.resume();
				try{
					Thread.sleep(500);
				}
				catch(InterruptedException e){}
				sim.pause();
			}
			//play/pause/resume button
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
