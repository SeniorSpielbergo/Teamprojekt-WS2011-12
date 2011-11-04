/**
 * This abstract class is the common base for LEGO and Console/PC tapes. 
 * It contains no concrete methods.
 *
 * @author Nils Breyer
 */

public abstract class Tape {
	boolean ready = false;
	
	/**
	 * Initializes the tape
	 * @throws Exception
	 */
	public abstract void init() throws Exception;
	
	/**
	 * Shutdown the tape
	 */
	public abstract void shutdown();
	
	/**
	 * Reads the symbol at the current tape position
	 * @return
	 */
	public abstract char read();
	
	/**
	 * Initializes the tape
	 * @throws Exception
	 */
	public abstract void write(char c);
	
	/**
	 * Move the tape one field to the left
	 */
	public abstract void moveLeft();
	
	/**
	 * Move the tape one field to the right
	 */
	public abstract void moveRight();
	
	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 */
	public abstract void test();
}
