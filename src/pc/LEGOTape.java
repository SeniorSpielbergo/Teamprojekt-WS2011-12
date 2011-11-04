/* This class represents a physical turing machine tape and provides methods to interact with the tape
 * 
 * Responsible: Nils Breyer
 * 
 */

public class LEGOTape extends Tape {
	private MasterRobot master = null;
	private SlaveRobot slave = null;
	private char currentSymbol = 'n';
	
	boolean ready = false;

	public LEGOTape(MasterRobot master, SlaveRobot slave) {
		this.master = master;
		this.slave = slave;
	}
	
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
	
	public void shutdown() {
		this.master.disconnect();
		this.slave.disconnect();
		ready = false;
	}
	
	public char read() {
		currentSymbol = this.master.read();
		return currentSymbol;
	}
	
	public void write(char c) {
		this.master.write(currentSymbol, c);
		this.slave.write(currentSymbol, c);
	}
	
	public void moveLeft() {
		this.master.moveLeft();
	}
	
	public void moveRight() {
		this.master.moveRight();
	}
	
	public void test() { //TODO: remove
		this.master.test();
	}
}
