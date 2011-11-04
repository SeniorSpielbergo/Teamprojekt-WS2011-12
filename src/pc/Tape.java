/**
 * This abstract class is the common base for LEGO and Console/PC tapes. 
 * It contains no concrete methods.
 *
 * @author Nils Breyer
 */

public abstract class Tape {
	private char currentSymbol = 'n';
	boolean ready = false;
	
	public abstract void init() throws Exception;
	
	public abstract void shutdown();
	
	public abstract char read();
	
	public abstract void write(char c);
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
	
	public abstract void test();
}
