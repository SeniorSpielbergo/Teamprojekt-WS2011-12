public class MasterRobot extends Robot {
	public MasterRobot(String name, String mac_address) {
		super(name,mac_address);
	}
	
	public char read() {
		this.sendCommand('r');
		return this.receiveCommand();
	}
	
	public void write(char c) {
		//TODO: implement
	}
	
	public void moveLeft() {
		//TODO: implement
	}
	
	public void moveRight() {
		//TODO: implement
	}
	
	public void test() { //TODO: remove

	}
}
