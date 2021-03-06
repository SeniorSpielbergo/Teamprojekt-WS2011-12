package gui;
import gui.turing.TuringMachineEditor;

import java.util.*;
import javax.swing.*;

import tape.DisplayableTape;
import tape.LEGOTape;
import tape.Tape;
import tape.TapeException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import machine.*;

@SuppressWarnings("serial")
/**
 * This class is the window in which runs the simulation with the tapes.
 * @author Nessa Baier
 */
public class SimulationWindow extends JFrame implements Observer, ActionListener{
	/**
	 * The ScrollPane.
	 */
	JScrollPane scrollpaneRight;
	
	/**
	 * Panels for toolbar tapes.
	 */
	JPanel panelall, panelToolbar;

	/**
	 * The label for the result.
	 */
	JLabel resultLabel;
	/**
	 * The toolbar.
	 */
	JToolBar toolbar;

	/**
	 * The buttons play, forward and sound on/off.
	 */
	JButton buttonPlay, buttonForward;
	JButton buttonSound;
	/**
	 * The simulation's graphic tapes.
	 */
	ArrayList<tape.DisplayableTape> graphicTapes = new ArrayList<tape.DisplayableTape>();
	/**
	 * Current machine.
	 */
	Machine currentMachine;
	/**
	 * true if simulation is/should be paused
	 */
	boolean simulationPaused, soundEnabled;
	/**
	 * current simulation
	 */
	Simulation sim;

	/**
	 * The first LEGOtape of the machine.
	 */
	tape.LEGOTape myFirstLEGOTape;
	
	/**
	 * All LEGOTapes of the machine.
	 */
	ArrayList<tape.LEGOTape> legoTapes = new ArrayList<tape.LEGOTape>();

	private boolean delay = true;

	private String style = "default";
	private Editor editor;
	private Integer counter = 0; 

	private ImageIcon iconPlay = new ImageIcon(SimulationWindow.class.getResource("images/play.png"));
	private ImageIcon iconPause = new ImageIcon(SimulationWindow.class.getResource("images/pause.png"));
	private ImageIcon iconStepForward = new ImageIcon(SimulationWindow.class.getResource("images/forward.png"));
	private ImageIcon iconSoundOn = new ImageIcon(SimulationWindow.class.getResource("images/sound_on.png"));
	private ImageIcon iconSoundOff = new ImageIcon(SimulationWindow.class.getResource("images/sound_off.png"));


	/**
	 * Creates a simulation window without editor.
	 * @param machine The current machine.
	 */
	public SimulationWindow(Machine machine) {
		this(machine,null);
	}

	/**
	 * Creates a new window for the simulation.
	 * @param machine The machine to simulate.
	 * @param editor The editor window.
	 */
	public SimulationWindow(Machine machine, Editor editor){
		this.simulationPaused = true;
		this.currentMachine = machine;
		this.editor = editor;

		this.soundEnabled = true;

		for(Tape t: this.currentMachine.getTapes()){
			if(t instanceof tape.LEGOTape){
				this.legoTapes.add((tape.LEGOTape) t);
				this.myFirstLEGOTape = ((tape.LEGOTape) t);
			}
		}

			this.toFront();
			try {
				this.setAlwaysOnTop(true);
			}
			catch (SecurityException e) {
				ErrorDialog.showError("Error bringing the simulation window to the foreground.");
			}

			for(int i = 0; i< currentMachine.getTapes().size(); i++){
				this.currentMachine.getTapes().get(i).addObserver(this);
				if(this.currentMachine.getType() == Machine.MachineType.TuringMachine){
					this.currentMachine.getTapes().get(i).addObserver((TuringMachineEditor)(currentMachine.getEditor()));
				}
				if(currentMachine.getTapes().get(i) instanceof DisplayableTape){
					graphicTapes.add((tape.DisplayableTape)machine.getTapes().get(i));
				}
			}

			if (editor != null) {
				this.editor.setEditable(false);
			}

			this.setTitle("Simulation of " +this.currentMachine.getName());
			this.setLayout(new GridBagLayout());
			this.setMinimumSize(new Dimension(300,75));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			this.panelall = new JPanel(new GridBagLayout());
			this.scrollpaneRight = new JScrollPane(panelall);
			this.resultLabel = new JLabel("");


			toolbar = new JToolBar("Functions");
			buttonPlay = new JButton(this.iconPlay);
			buttonPlay.setEnabled(false);
			buttonForward = new JButton(this.iconStepForward);
			buttonForward.setEnabled(false);
			buttonSound = new JButton(this.iconSoundOn);

			this.buttonSound.setEnabled(false);
			this.buttonSound.setActionCommand("disabled");
			buttonSound.addActionListener(this);
			buttonPlay.addActionListener(this);
			buttonForward.addActionListener(this);
			toolbar.add(buttonPlay);
			toolbar.add(buttonForward);
			toolbar.add(buttonSound);

			this.panelToolbar = new JPanel();
			this.panelToolbar.add(toolbar);


			//initialize tapes
			try {
				for(Tape tape : currentMachine.getTapes()){
					tape.setDelay(false);
				}
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
		

		this.setLocation(200, 200);
		this.computeSize();
		setVisible(true);
		this.init();
	}

	private void computeSize() {
		int visibleTapes = (this.graphicTapes.size() <= 5 ? this.graphicTapes.size() : 5); //show up to 5 tapes per default
		int height = 120;
		if (visibleTapes > 0) {
			height += visibleTapes * ((int)this.graphicTapes.get(0).getHeight()+3);
		}
		this.setSize(600,1);
		this.repaint();
		this.validate();
		this.setSize(600,height);
		this.repaint();
		this.validate();
	}

	/*
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
		panelallConstraints.gridx = 0;
		panelallConstraints.gridy = 0;
		this.panelall.add(this.resultLabel,panelallConstraints);
		for(int i = 0; i< graphicTapes.size(); i++){
			panelallConstraints.gridx = 0;
			panelallConstraints.gridy = i+1;
			panelallConstraints.weightx = 1.0;
			panelallConstraints.fill = GridBagConstraints.HORIZONTAL;
			this.panelall.add(this.graphicTapes.get(i).getTapePanel(),panelallConstraints);
		}
		JPanel gap = new JPanel();
		panelallConstraints.fill = GridBagConstraints.BOTH;
		panelallConstraints.weighty = 1.0;
		panelallConstraints.gridy = graphicTapes.size()+1;
		panelallConstraints.gridx = 0;
		this.panelall.add(gap,panelallConstraints);

		//writing input words
		try {
			this.currentMachine.writeInputWords();
			sim = this.currentMachine.createSimulation();
			sim.addObserver(this);
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

		this.validate();
		this.repaint();
	}

	/**
	 * Disposes the window and aborts the simulation.
	 */
	public void dispose(){
		if(!this.sim.isSimulationAlreadyStarted()){
			for (Tape t : this.graphicTapes){
				t.setiWishToInterruptThisThread(true);
				try {
					t.getWriteInputWordThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else if(this.sim.isSimulationAlreadyStarted()){ 
			this.sim.setAbortSimulation();

			try {
				System.out.println("vorher");
				this.sim.getSimulationThread().join();
				System.out.println("vorher");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try{
			System.out.println(" i shut down the tapes");
			this.currentMachine.shutdownTapes();
		}
		catch (TapeException e) {
			System.out.println("Warning: The tapes couldn't be shutdown correctly.");
			e.printStackTrace();
			ErrorDialog.showError("The initialization of the tapes failed because of an undefined exception.", e);
			return;
		}

		this.sim.setAbortSimulation(); //TODO is this ok?
		for(Tape tape : currentMachine.getTapes()){
			tape.deleteObserver(this);
		}


		this.setVisible(false);

		if (editor != null) {
			this.editor.setEditable(true);
		}
	}

	@Override 
	public void update(Observable observable, Object obj) {
		if (observable instanceof tape.Tape 
				&& obj instanceof tape.Tape.Event
				&& (tape.Tape.Event)obj == tape.Tape.Event.INPUTFINISHED){

			synchronized(counter){counter++;}
			System.out.println("Counter: " + counter);
			if(counter >= this.currentMachine.getNumberOfTapes()){

				for(Tape tape : currentMachine.getTapes()){
					tape.setDelay(this.delay);
				}
				this.buttonPlay.setEnabled(true);
				this.buttonForward.setEnabled(true);
				System.out.println("Writing input word finished: notified");
				//send machine name to robot
				for ( LEGOTape t : this.legoTapes){
					try {
						t.getSlave().sendMachineName(this.currentMachine.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		else if(this.sim.isSimulationAlreadyStarted()
				&& observable instanceof Simulation 
				&& obj instanceof Simulation.simulationState 
				&&((Simulation.simulationState)obj)==Simulation.simulationState.FINISHED){

			if (this.myFirstLEGOTape != null) {
				this.myFirstLEGOTape.getSlave().soundOff();
			}
			this.buttonForward.setEnabled(false);
			this.buttonPlay.setEnabled(false);
			this.buttonSound.setEnabled(false);
			this.resultLabel.setText("Input word was accepted.");
		}
		else if(this.sim.isSimulationAlreadyStarted()
				&& observable instanceof Simulation 
				&& obj instanceof Simulation.simulationState 
				&&((Simulation.simulationState)obj)==Simulation.simulationState.FAILED){

			if (this.myFirstLEGOTape != null) {
				this.myFirstLEGOTape.getSlave().soundOff();
			}
			this.buttonForward.setEnabled(false);
			this.buttonPlay.setEnabled(false);
			this.buttonSound.setEnabled(false);
			this.resultLabel.setText("Input word was not accepted.");
		}
	}


	@Override
	public void actionPerformed( ActionEvent event){
		//forward button
		if(event.getSource().equals(buttonForward) && sim.isSimulationAlreadyStarted()){
			sim.resume();
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){}
			sim.pause();
		}
		else if(event.getSource().equals(buttonForward)&& !sim.isSimulationAlreadyStarted()){
			try {
				simulationPaused = false;
				this.buttonPlay.setEnabled(false);
				if(this.myFirstLEGOTape != null){
					this.myFirstLEGOTape.getSlave().startSound();
					this.buttonSound.setEnabled(true);
				}
				sim.start();
				sim.pause();
				this.buttonPlay.setEnabled(true);
			}

			catch (RuntimeException e){
				ErrorDialog.showError("The simulation failed because of an undefined exception.", e);
			}
		}

		//play/pause/resume button
		else if(event.getSource().equals(buttonPlay)&& !sim.isSimulationAlreadyStarted()){
			try {
				simulationPaused = false;
				if(this.myFirstLEGOTape != null){
					this.myFirstLEGOTape.getSlave().startSound();
					this.buttonSound.setEnabled(true);
				}
				sim.start();
				this.buttonPlay.setIcon(this.iconPause);
			}
			catch (RuntimeException e){
				ErrorDialog.showError("The simulation failed because of an undefined exception.", e);
			}
		}

		else if(event.getSource().equals(buttonPlay)&& sim.isSimulationAlreadyStarted()){
			if(simulationPaused){
				sim.resume();
				buttonForward.setEnabled(false);
				this.buttonPlay.setIcon(this.iconPause);
			}
			else{
				sim.pause();
				buttonForward.setEnabled(true);
				this.buttonPlay.setIcon(this.iconPlay);
			}
			simulationPaused = !simulationPaused;
		}

		else if(event.getSource().equals(buttonSound)){
			this.soundEnabled = !this.soundEnabled;
			
			if (this.soundEnabled) {
				this.buttonSound.setIcon(this.iconSoundOn);
			}
			else {
				this.buttonSound.setIcon(this.iconSoundOff);
			}

			if(this.myFirstLEGOTape != null){
				if(this.soundEnabled){
					this.myFirstLEGOTape.getSlave().playSound();
				}
				else{
					this.myFirstLEGOTape.getSlave().muteSound();
				}
			}
		}
	}

	/**
	 * Enable/disable delay for simulation.
	 * @param enabled True if delay should be enabled.
	 */
	public void setDelay(boolean enabled) {
		this.delay = enabled;

		for (DisplayableTape tape : this.graphicTapes) {
			tape.setDelay(enabled);
		}
	}

	/**
	 * Returns if the delay is enabled.
	 * @return True if delay is enabled.
	 */
	public boolean getDelay() {
		return this.delay;
	}

	/**
	 * Sets tape's style.
	 * @param style
	 */
	public void setTapeStyle(String style) {
		this.style = style;

		for (DisplayableTape tape : this.graphicTapes) {
			tape.setStyle(style);
		}
		this.computeSize();

	}

	/**
	 * Returns the tape's style.
	 * @return style
	 */
	public String getTapeStyle() {
		return this.style;
	}
}
