package machine;

import java.io.IOException;
import java.util.ArrayList;

import Tape.Tape;
import Tape.TapeException;

public abstract class Machine {
	protected String name;

	protected ArrayList<Tape> tapes;

	/**
	 * Constructs an untitled Turing machine
	 */
	public Machine() {
		this.name = "Untitled machine";
	}

	/**
	 * Constructs an empty Turing machine with a name
	 * @param name Name for the Turing machine
	 */
	public Machine(String name) {
		this.name = name;
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
	
}
