/** Represents a physical turing machine tape and provides methods to interact with the tape
 * 
 * @author Nils Breyer
 * 
 */

public class LEGOTape extends Tape {
	private MasterRobot master = null;
	private SlaveRobot slave = null;
	private char currentSymbol = 'n';
	
	boolean ready = false;

	/**
	 * Constructs a new LEGO tape based on two NXT robots
	 * @param master The robot that can move the tape, read and write symbols
	 * @param slave The robot that can only write
	 */
	public LEGOTape(MasterRobot master, SlaveRobot slave) {
		this.master = master;
		this.slave = slave;
	}
	
	/**
	 * Initializes the LEGO tape by connecting to the two robots
	 * @throws Exception Thrown, when the tape failed to initialize
	 * @see #shutdown()
	 */
	public void init() throws Exception {
		System.out.println("Initializing tape...");
		try {
			this.master.connect();
			this.slave.connect();
			ready = true;
			System.out.println("Tape ready.");

		}
		catch (Exception e) {
			ready = false;
			System.out.println("Initializing tape failed.");
			throw e;
		}
	}
	
	/**
	 * Shuts the tape down by disconnecting the two robots
	 * 
	 * @see #init()
	 */
	public void shutdown() {
		this.master.disconnect();
		this.slave.disconnect();
		ready = false;
	}
	
	/**
	 * Reads the symbol at the current tape position
	 * 
	 * @return #,0,1,2
	 * @see #write(char)
	 */
	public char read() {
		currentSymbol = this.master.read();
		return currentSymbol;
	}

	/**
	 * Writes a symbol to the current tape position
	 * 
	 * @param c The symbol to write (#,0,1,2)
	 * @see #read()
	 */
	public void write(char c) {
		this.master.write(currentSymbol, c);
		this.slave.write(currentSymbol, c);
	}
	
	/**
	 * Moves the tape one field to the left
	 * 
	 * @see #moveRight()
	 */
	public void moveLeft() {
		this.master.moveLeft();
	}
	
	/**
	 * Moves the tape one field to the right
	 * 
	 * @see #moveLeft()
	 */
	public void moveRight() {
		this.master.moveRight();
	}
	
	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 */
	public void test() { //TODO: remove
		this.master.test();
	}
}
