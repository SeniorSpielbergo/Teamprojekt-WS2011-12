package tape;

import java.util.*;
import java.lang.String;

/**
 * Implements a virtual tape and shows it on the console.
 * @author Nils Breyer
 */

public class ConsoleTape extends Tape {
	/**
	 * Stores the current tape content.
	 */
	HashMap<Integer, Character> memory = new HashMap<Integer, Character>();

	/**
	 * Creates a new console tape.
	 */
	public ConsoleTape() {
		super("Default console tape");
	}
	
	/**
	 * Creates a new console tape.
	 * @param allowInput true/false if allowed
	 */
	public ConsoleTape(boolean allowInput) {
		super("Default console tape", allowInput);
	}

	/**
	 * Creates a new console tape with a specific name.
	 * @param name The tape name
	 */
	public ConsoleTape(String name) {
		super(name);
	}

	/**
	 * Creates a new console tape with a specific name.
	 * @param name The tape name
	 * @param allowInput true/false if allowed
	 */
	public ConsoleTape(String name, boolean allowInput) {
		super(name, allowInput);
	}
	
	@Override
	public void init() throws TapeException {
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");

		//set ready
		this.ready = true;
		System.out.println(this.name + ": Tape ready.");
	}

	@Override
	public Type getType() {
		return Type.CONSOLE;
	}

	@Override
	public void shutdown() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		//reset tape
		this.ready = false;
		this.position = 0;
		this.memory.clear();
	}

	@Override
	public boolean writeInputWord() throws TapeException {
		if (this.position != 0) {
			throw new TapeException(this, "Input word can only be written when at position 0");
		}
		//write input word to tape
		for (int i = 0; i < this.inputWord.length(); i++) {
			if(this.iWishToInterruptThisThread){
				System.out.println("thread wird gestoppt");
				super.setChanged();
				super.notifyObservers(Event.INPUTABORTED);
				break;
			}

			this.write(this.inputWord.charAt(i));
			this.moveRight();
		}
		for (int i = 0; i < this.inputWord.length(); i++) {
			if(this.iWishToInterruptThisThread){
				System.out.println("thread wird gestoppt");
				super.setChanged();
				super.notifyObservers(Event.INPUTABORTED);
				break;
			}
			this.moveLeft();
		}
		if(this.iWishToInterruptThisThread)
			return false;
		return true;
	}

	@Override
	public char read() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		if (this.memory.get(this.position) != null) {
			return this.memory.get(this.position);
		}
		else {
			return '#';
		}
	}

	@Override
	public void write(char c) throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		this.memory.put(this.position, c);
		this.printTape();
	}

	@Override
	public void moveLeft() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		position--;
		this.printTape();
	}

	@Override
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
	
	
	@Override
	public Object clone() {
		return this;
	}
}
