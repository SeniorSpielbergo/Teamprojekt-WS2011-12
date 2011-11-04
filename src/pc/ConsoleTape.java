/**
 * This class implements a virtual tape and shows it on the console.
 *
 * @author Nils Breyer
 */

import java.util.*;

public class ConsoleTape extends Tape {
	HashMap<Integer, Character> memory = new HashMap<Integer, Character>();
	int position = 0;
	
	/** 
     * This method initializes the tape
     *
     */
	public void init() throws Exception {
		this.ready = true;
	}
	
	/**
	 * This method shuts the tape down.
	 */
	public void shutdown()  {
		this.ready = false;
	}
		
	/** 
     * This method returns the char at the current tape position
     *
     * @return Character at the current position
     * @see #write(char)
     */
	public char read() {
		if (this.memory.get(this.position) != null) {
			return this.memory.get(this.position);
		}
		else {
			return '#';
		}
	}
	
	/** 
     * This method writes a symbol at the current tape position
     *
     * @param c Character to write (allowed characters are #, 0, 1, 2)
     * @see #read()
     */
	public void write(char c) {
		this.memory.put(this.position, c);
		this.printTape();
	}
	
	/** 
     * This method moves the tape one field to the left
     * 
     * @see #moveRight()
     */
	public void moveLeft() {
		position--;
		this.printTape();
	}
	
	/** 
     * This method moves the tape one field to the right
     *
     * @see #moveLeft()
     */
	public void moveRight() {
		position++;
		this.printTape();
	}
	
	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 */
	public void test() {//TODO: remove
		System.out.println("Test"); 
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
