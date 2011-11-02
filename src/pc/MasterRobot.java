/* This class contains special methods for the Master Robot
 * 
 * Responsible: Nils Breyer
 * 
 */
public class MasterRobot extends Robot {
	public MasterRobot(String name, String mac_address) {
		super(name,mac_address);
	}
	
	public char read() {
		this.sendCommand('r');
		return this.receiveCommand();
	}
	
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
	
	public void test() { //TODO: remove
		this.sendCommand('t');
	}
}
