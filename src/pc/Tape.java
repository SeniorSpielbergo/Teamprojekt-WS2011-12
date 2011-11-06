/**
 * This abstract class is the common base class for LEGO and Console/PC tapes.
 * It contains no concrete methods.
 *
 * @author Nils Breyer
 */

public abstract class Tape {
	boolean ready = false;
	
	/**
	 * Initializes the tape
	 * @throws Exception
	 * @see #shutdown()
	 */
	public abstract void init() throws TapeException;
	
	/**
	 * Shutdown the tape
	 * @see #init()
	 */
	public abstract void shutdown() throws TapeException;
	
	/**
	 * Reads the symbol at the current tape position
	 * 
	 * @return Symbol at the current tape position (#,0,1,2)
	 */
	public abstract char read() throws TapeException;
	
	/**
	 * Writes a symbol to the current tape position
	 * @param c Symbol to write (#,0,1,2)
	 */
	public abstract void write(char c) throws TapeException;
	
	/**
	 * Move the tape one field to the left
	 */
	public abstract void moveLeft() throws TapeException;
	
	/**
	 * Move the tape one field to the right
	 */
	public abstract void moveRight() throws TapeException;
	
	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 */
	public abstract void test() throws TapeException;
}
