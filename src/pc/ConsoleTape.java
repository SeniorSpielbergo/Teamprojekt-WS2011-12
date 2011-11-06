import java.util.*;

/**
 * Implements a virtual tape and shows it on the console
 * @author Nils Breyer
 */

public class ConsoleTape extends Tape {
	HashMap<Integer, Character> memory = new HashMap<Integer, Character>();
	
	/** 
     * This method initializes the tape
     * @throws TapeException If the tape has already been initialized
     */
	public void init() throws TapeException {
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");
		this.ready = true;
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
	 * {@inheritDoc}
	 */
	public boolean isReady() {
		return this.ready;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getPosition() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		return this.position;
	}
	
	/**
	 * Prints the tape to the console (from position -20 to +20)
	 */
	public void printTape() {
		System.out.print("-");
		for (int i=-20; i<=20; i++) {
			System.out.print("--");
		}
		System.out.println("");

		System.out.print("|");
		for (int i=-20; i<=20; i++) {
			if (this.memory.get(i) != null) {
				System.out.print(this.memory.get(i));
			}
			else {
				System.out.print("#");
			}
			System.out.print("|");
		}
		System.out.println("");
		
		for (int i=-20; i<this.position; i++) {
			System.out.print("  ");
		}
		System.out.print(" ^");
		System.out.println("");
	
		System.out.print("-");
		for (int i=-20; i<=20; i++) {
			System.out.print("--");
		}
		System.out.println("");
	}
}
