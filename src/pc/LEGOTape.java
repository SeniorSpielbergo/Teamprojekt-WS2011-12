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
	public void init() throws TapeException {
		System.out.println("Initializing tape...");
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");

		try {
			this.master.connect();
			this.slave.connect();
			ready = true;
			System.out.println("Tape ready.");

		}
		catch (Exception e) {
			ready = false;
			TapeException ex = new TapeException(this, "Tape has already been initialized.");
			ex.initCause(e);
			throw ex;
		}
	}
	
	/**
	 * Shuts the tape down by disconnecting the two robots
	 * @throws TapeException If the tape has not been initialized
	 * @see #init()
	 */
	public void shutdown() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");

		this.master.disconnect();
		this.slave.disconnect();
		ready = false;
	}
	
	/**
	 * Reads the symbol at the current tape position
	 * @throws TapeException If the tape has not been initialized
	 * @return #,0,1,2
	 * @see #write(char)
	 */
	public char read() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");

		currentSymbol = this.master.read();
		return currentSymbol;
	}

	/**
	 * Writes a symbol to the current tape position
	 * @throws TapeException If the tape has not been initialized
	 * @param c The symbol to write (#,0,1,2)
	 * @see #read()
	 */
	public void write(char c) throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");
		
		this.master.write(currentSymbol, c);
		this.slave.write(currentSymbol, c);
	}
	
	/**
	 * Moves the tape one field to the left
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveRight()
	 */
	public void moveLeft() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");
		
		this.master.moveLeft();
	}
	
	/**
	 * Moves the tape one field to the right
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveLeft()
	 */
	public void moveRight() throws TapeException{
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");
		
		this.master.moveRight();
	}
	
	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 * @throws TapeException If the tape has not been initialized
	 */
	public void test()  throws TapeException{ //TODO: remove
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");
		this.master.test();
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
		if (!this.ready) throw new TapeException(this, "Tape has already been initialized.");

		return this.position;
	}
}
