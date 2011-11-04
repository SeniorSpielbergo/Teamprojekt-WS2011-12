/** Contains special methods for the Master Robot
 * 
 * @author Nils Breyer
 * 
 */
public class MasterRobot extends Robot {
	
	/**
	 * Constructor for a MasterRobot
	 * @param name Name of the Robot
	 * @param mac_address MAC address of the Robot
	 */
	public MasterRobot(String name, String mac_address) {
		super(name,mac_address);
	}
	/**
	 * Implementation to send the MasterRobot the command to read a symbol
	 * @return Returns the symbol that have been read on the tape
	 */
	public char read() {
		this.sendCommand('r');
		return this.receiveCommand();
	}
	
	/**
	 * Send the MasterRobot the command to move to the next position right of the current
	 */
	public void moveLeft() {
		System.out.println(this.name + ": Moving left...");
		this.sendCommand('L');
		char received = this.receiveCommand();
		if (received == '.') {
			System.out.println(this.name + ": Success");
		} else if (received == '!') {
			System.out.println(this.name + ": Moving left failed.");
		} else {
			System.out.println(this.name + ": Common Fail, read from Robot: " + received);
		}
	}
	
	/**
	 * Send the MasterRobot the command to move to the next position left of the current
	 */
	public void moveRight() {
		System.out.println(this.name + ": Moving right...");
		this.sendCommand('R');
		char received = this.receiveCommand();
		if (received == '.') {
			System.out.println(this.name + ": Success");
		} else if (received == '!') {
			System.out.println(this.name + ": Moving right failed.");
		} else {
			System.out.println(this.name + ": Common Fail, read from Robot: " + received);
		}
	}
	
	/**
	 * Test the tape
	 */
	public void test() { //TODO: remove
		this.sendCommand('t');
	}
}
