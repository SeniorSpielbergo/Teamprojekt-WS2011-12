package Tape;

import java.util.*;

/**
 * Implements a virtual tape and shows it on the console
 * @author Nils Breyer
 */

public class ConsoleTape extends Tape {
	HashMap<Integer, Character> memory = new HashMap<Integer, Character>();

	/**
	 * Creates a new console tape
	 */
	public ConsoleTape() {
		super("Default console tape");
	}

	/**
	 * Creates a new console tape with a specific name
	 * @param name The tape name
	 */
	public ConsoleTape(String name) {
		super(name);
	}

	/** 
	 * This method initializes the tape
	 * @throws TapeException If the tape has already been initialized
	 */
	public void init() throws TapeException {
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");
		
		//write input word to tape
		for (int i = 0; i < this.inputWord.length(); i++) {
			this.write(this.inputWord.charAt(i));
			this.moveRight();
		}
		for (int i = 0; i < this.inputWord.length(); i++) {
			this.moveLeft();
		}
		
		//set ready
		this.ready = true;
		System.out.println(this.name + ": Tape ready.");
	}

	/**
	 * This method shuts the tape down.
	 * @throws TapeException If the tape has not been initialized
	 */
	public void shutdown() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");
		this.ready = false;
	}

	/** 
	 * This method returns the char at the current tape position
	 * @return Character at the current position
	 * @throws TapeException If the tape has not been initialized
	 * @see #write(char)
	 */
	public char read() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		if (this.memory.get(this.position) != null) {
			return this.memory.get(this.position);
		}
		else {
			return '#';
		}
	}

	/** 
	 * This method writes a symbol at the current tape position
	 * @param c Character to write (allowed characters are #, 0, 1, 2)
	 * @throws TapeException If the tape has not been initialized
	 * @see #read()
	 */
	public void write(char c) throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		this.memory.put(this.position, c);
		this.printTape();
	}

	/** 
	 * This method moves the tape one field to the left
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveRight()
	 */
	public void moveLeft() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		position--;
		this.printTape();
	}

	/** 
	 * This method moves the tape one field to the right
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveLeft()
	 */
	public void moveRight() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		position++;
		this.printTape();
	}

	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 * @throws TapeException If the tape has not been initialized
	 */
	public void test() throws TapeException { //TODO: remove
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		System.out.println("Test"); 
	}

	/**
	 * Prints the tape to the console (from position -20 to +20)
	 */
	public void printTape() {
		System.out.print(this.toString());
	}

	/**
	 * Returns a formatted string representing the current tape state.
	 * It includes the tape name, relevant parts of the memory and the head position.
	 */
	@Override
	public String toString() {
		String text;
		if (ready) {
			text = " " + this.getName() + "@pos "  + this.getPosition() + ": \n";
			int offset = this.getPosition() - (this.getPosition() % 10) - 20 ;
			text += this.getMemoryAsFormattedString(offset, 40);
		}
		else {
			text = " " + this.getName() + " (tape not initialized), input word: " + this.inputWord;
		}
		
		return text;
	}

	/**
	 * Returns a formatted string representing the current memory state.
	 * It includes specified part of the memory and the head position.
	 * @param offset First memory position to be included
	 * @param length Number of memory fields to be included
	 * @return The formatted string with the memory content
	 */
	public String getMemoryAsFormattedString(int offset, int length) {
		if (length < 1) return "";

		//print top horizontal line
		String text = "-";
		for (int i=offset; i<=offset+length; i++) {
			text += "--";
		}
		text += "\n";

		//print memory
		text += "|";
		for (int i=offset; i<=offset+length; i++) {
			if (this.memory.get(i) != null) {
				text += this.memory.get(i);
			}
			else {
				text += "#";
			}
			text += "|";
		}
		text += "\n";

		//print head position
		if (this.position >= offset && this.position <= offset+length) {
			for (int i=offset; i<this.position; i++) {
				text += "  ";
			}
			text += " ^";
		}
		text += "\n";

		//print bottom horizontal line
		text += "-";
		for (int i=offset; i<=offset+length; i++) {
			text += "--";
		}
		text += "\n";

		return text;
	}
}
