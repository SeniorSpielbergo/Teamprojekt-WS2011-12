/* This class represents a physical turing machine tape and provides methods to interact with the tape
 * 
 * Responsible: Nils Breyer
 * 
 */

public class Tape {
	private MasterRobot master = null;
	private SlaveRobot slave = null;
	
	boolean ready = false;

	public Tape(MasterRobot master, SlaveRobot slave) {
		this.master = master;
		this.slave = slave;
	}
	
	public void init() {
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
		}
	}
	
	public void shutdown() {
		this.master.disconnect();
		this.slave.disconnect();
		ready = false;
	}

}
