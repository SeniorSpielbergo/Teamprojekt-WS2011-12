package machine;

import gui.MachineEditor;

import java.io.IOException;
import java.util.ArrayList;

import tape.Tape;
import tape.TapeException;

public abstract class Machine {
	public enum MachineType {
		TuringMachine, BrainfuckMachine
	}
	protected String name;

	protected ArrayList<Tape> tapes;
	
	protected MachineEditor editor;

	/**
	 * Constructs an untitled Turing machine
	 */
	public Machine() {
		this("Untitled machine");
	}

	/**
	 * Constructs an empty Turing machine with a name
	 * @param name Name for the Turing machine
	 */
	public Machine(String name) {
		this.name = name;
		this.tapes = new ArrayList<Tape>();
		this.editor = this.createEditor();

	}

	/**
	 * Initializes the tapes and writes the input words to the tape
	 * @throws TapeException Exception if any problems occur while initializing
	 */
	public void initTapes() throws TapeException {
		//init tapes
		for (Tape tape : this.tapes) {
			tape.init();
			tape.writeInputWord();
		}
	}

	/**
	 * Shuts down the tapes
	 * @throws TapeException Exception if any problems occur while shutting down
	 */
	public void shutdownTapes() throws TapeException {
		//init tapes
		for (Tape tape : this.tapes) {
			tape.shutdown();
		}
	}
	
	/**
	 * Returns the Turing machine's name
	 * @return The Turing machine's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the Turing machine's number of tapes
	 * @return Number of tapes
	 */
	public ArrayList<Tape> getTapes() {
		return this.tapes;
	}

	public int getNumberOfTapes() {
		return this.tapes.size();
	}
	
	public abstract void save(String filename) throws IOException;
	public abstract void load(String filename) throws IOException;
	
	public abstract Simulation createSimulation() throws TapeException;
	
	protected abstract MachineEditor createEditor();
	
	public MachineEditor getEditor() {
		return this.editor;
	}
	
	public abstract MachineType getType();
}
