package tape;

import java.io.*;
import java.lang.String;

/** Represents a physical Turing machine tape and provides methods to interact with the tape
 * 
 * @author Nils Breyer
 * 
 */

public class LEGOTape extends Tape {
	/**
	 * The minimal position of the head allowed on the LEGO tape
	 */
	public static final int MIN_POSITION = 0;
	/**
	 * The maximal position of the head allowed on the LEGO tape
	 */
	public static final int MAX_POSITION = 8;	
	/**
	 * The master robot of the tape
	 */
	private MasterRobot master = null;
	/**
	 * The slave robot of the tape
	 */
	private SlaveRobot slave = null;
	/**
	 * The symbol at the current position
	 */
	private char currentSymbol = 'n';

	/**
	 * Constructs a new LEGO tape based on two NXT robots with the name "Default LEGO tape"
	 * @param master The robot that can move the tape, read and write symbols
	 * @param slave The robot that can only write
	 * @see #LEGOTape(String, MasterRobot, SlaveRobot)
	 */
	public LEGOTape(MasterRobot master, SlaveRobot slave) {
		this.master = master;
		this.slave = slave;
		this.name = "Default LEGO tape";
	}

	/**
	 * Constructs a new LEGO tape based on two NXT robots with a specific name
	 * @param name Tape name string
	 * @param master The robot that can move the tape, read and write symbols
	 * @param slave The robot that can only write
	 */
	public LEGOTape(String name, MasterRobot master, SlaveRobot slave) {
		this.master = master;
		this.slave = slave;
		this.name = name;
	}

	/**
	 * Initializes the LEGO tape by connecting to the two robots
	 * @throws TapeException Thrown, when the tape failed to initialize
	 * @see #shutdown()
	 */
	public void init() throws TapeException {
		System.out.println(this.name + ": Initializing tape...");
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");

		try {
			this.master.connect();
			this.slave.connect();
			this.currentSymbol = 'n';

			ready = true;
			System.out.println(this.name + ": Tape ready.");

		}
		catch (Exception e) {
			ready = false;
			throw new TapeException(this, "Tape couldn't be initialized.", e);
		}

	}

	/**
	 * Returns the tape type
	 * @return Returns "LEGO"
	 */
	public Type getType() {
		return Type.LEGO;
	}

	/**
	 * Shuts the tape down by disconnecting the two robots
	 * @throws TapeException If the tape has not been initialized
	 * @see #init()
	 */
	public void shutdown() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		try {
			this.master.disconnect();
			this.slave.disconnect();
			//reset tape
			this.currentSymbol = 'n';
			this.position = 0;
		}
		catch (IOException e) {
			throw new TapeException(this, "Tape did not shutdown properly.", e);
		}

		ready = false;
	}

	public void writeInputWord() throws TapeException {
		if (this.position != 0) {
			throw new TapeException(this, "Input word can only be written when at position 0");
		}
		//write input word to tape
		for (int i = 0; i <= LEGOTape.MAX_POSITION; i++) {
			if (i < this.inputWord.length()) {
				this.write(this.inputWord.charAt(i));
			}
			else {
				this.write('#'); //fill the rest of the tape with #
			}
			if (i < LEGOTape.MAX_POSITION) {
				this.moveRight();
			}
		}

		for (int i = LEGOTape.MAX_POSITION; i > 0; i--) {
			this.moveLeft();
		}
	}

	/**
	 * Gets the master robot of the tape
	 * @return The master robot of the tape
	 */
	public MasterRobot getMaster() {
		return master;
	}

	/**
	 * Gets the slave robot of the tape
	 * @return The slave robot of the tape
	 */
	public SlaveRobot getSlave() {
		return slave;
	}

	/**
	 * Reads the symbol at the current tape position
	 * @throws TapeException If the tape has not been initialized
	 * @return #,0,1,2
	 * @see #write(char)
	 */
	public char read() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		try {
			currentSymbol = this.master.read();
			return currentSymbol;
		}
		catch (IOException e) {
			this.currentSymbol = 'n';
			throw new TapeException(this, "Reading failure.", e);
		}
	}

	/**
	 * Writes a symbol to the current tape position
	 * @param c The symbol to write (#,0,1,2)
	 * @throws TapeException If the tape has not been initialized
	 * @see #read()
	 */
	public void write(char c) throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		if (this.currentSymbol == 'n') {
			this.read();
		}
		try {
			this.master.write(currentSymbol, c);
			this.slave.write(currentSymbol, c);
		}
		catch (IOException e) {
			throw new TapeException(this, "Writing failure.", e);
		}

	}

	/**
	 * Moves the tape one field to the left
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveRight()
	 */
	public void moveLeft() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");

		try {
			this.master.moveLeft();
			this.position--;
			System.out.println(this.name + "@pos " + this.position);
			this.currentSymbol = 'n';
		}
		catch (IOException e) {
			this.currentSymbol = 'n';
			throw new TapeException(this, "Moving failure.", e);
		}


	}

	/**
	 * Moves the tape one field to the right
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveLeft()
	 */
	public void moveRight() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");

		try {
			this.master.moveRight();
			this.position++;
			System.out.println(this.name + "@pos " + this.position);
			this.currentSymbol = 'n';
		}
		catch (IOException e) {
			this.currentSymbol = 'n';
			throw new TapeException(this, "Moving failure.", e);
		}

	}

	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 * @throws TapeException If the tape has not been initialized
	 */
	public void test()  throws TapeException{ //TODO: remove
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");
		try {
			this.master.test();
		}
		catch (IOException e) {
			throw new TapeException(this, "Test failure.", e);
		}
	}

	/**
	 * Gives a string representation of the tape state, including the name, head position and the current symbol
	 */
	@Override
	public String toString() {
		String text;
		if (ready) {
			text =  " " + this.getName() + "@pos "  + this.getPosition() + ", current symbol: " + this.currentSymbol;

		}
		else {
			text = " " + this.getName() + " (tape not initialized), input word: " + this.inputWord;
		}
		return text;
	}
}
