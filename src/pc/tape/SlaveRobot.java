package tape;

import java.io.IOException;

/**
 * Contains special methods for the Slave Robot
 *
 * @author Nils Breyer
 */
public class SlaveRobot extends Robot {
	/**
	 * Constructor for a SlaveRobot
	 * @param name Name of the SlaveRobot
	 * @param mac_address MAC address of the SlaveRobot
	 */
	public SlaveRobot(String name, String mac_address) {
		super(name,mac_address);
	}
	
	/**
	 * Sends the command 'S' for playing sound while simulation.
	 */
	public void startSound(){
		try {
			this.sendCommand('S');
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}
	}
	/**
	 * Sends the command 'M' for unmuting sound.
	 */
	public void playSound(){
		try {
			this.sendCommand('M');
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	/**
	 * Sends the command 'm' for muting sound.
	 */
	public void muteSound(){
		try {
			this.sendCommand('m');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	/**
	 * Sends the command 's' for setting sound off.
	 */
	public void soundOff(){
		try {
			this.sendCommand('s');
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	/**
	 * Send the machine name
	 * @throws IOException Thrown if the sending failed
	 */
	public void sendMachineName(String name) throws IOException {
		this.sendCommand('n');
		for (char c : name.toCharArray()) {
			this.sendCommand(c);
		}
		this.sendCommand('\n');
	}
	
	
	/**
	 * Send the state name
	 * @throws IOException Thrown if the sending failed
	 */
	public void sendStateName(String name) throws IOException {
		this.sendCommand('x');
		for (char c : name.toCharArray()) {
			this.sendCommand(c);
		}
		this.sendCommand('\n');
	}
}