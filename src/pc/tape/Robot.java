package tape;
import lejos.pc.comm.*;
import java.io.*;
import java.util.Observable;
import java.util.concurrent.locks.ReentrantLock;

/** Contains common functions for Master and Slave Robots.
 * 
 * @author Nils Breyer, Phillipp Neumann
 * 
 */
public abstract class Robot extends Observable {
	/**
	 * The name of the robot
	 */
	protected String name;
	
	/**
	 *  Lock for writing.
	 */
	protected final ReentrantLock writeLock = new ReentrantLock();


	private String mac_address;
	private NXTComm comm = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;

	/**
	 * Constructor for a Robot.
	 * @param name Name of the Robot
	 * @param mac_address MAC address of the Robot
	 */

	public Robot(String name, String mac_address) {
		this.name = name;
		this.mac_address = mac_address;
	}

	/**
	 * Method to connect to the Robot.
	 * @throws NXTCommException Thrown, if the connecting fails
	 * @throws IOException Thrown if the connection couldn't be opened
	 */

	public void connect() throws NXTCommException, IOException {
		System.out.println("Connecting to '" + this.name + "'...");

		try {
			this.comm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		}
		catch (NXTCommException e) {
			System.out.println("Connecting to '" + this.name + "' failed while preparing bluetooth: " + e.getMessage());
			throw e;
		}

		NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, this.name, this.mac_address);

		this.comm.open(info);

		InputStream is = comm.getInputStream();
		OutputStream os = comm.getOutputStream();

		this.input = new DataInputStream(is);
		this.output = new DataOutputStream(os);

		System.out.println("Connected to '" + this.name + "'.");
		super.setChanged();
		super.notifyObservers(this.name);
	}

	/**
	 * Method to disconnect from the Robot.
	 * @throws IOException Thrown if the disconnecting fails
	 */
	public void disconnect() throws IOException {
		System.out.println("Disconnecting '" + this.name + "'...");
		this.sendCommand('q');
		try {
			this.output.close();
			this.comm.close();
		}
		catch (IOException e) {
			System.out.println("WARNING: Disconnecting '" + this.name + "' didn't work properly: " + e.getMessage());
			throw e;
		}

		System.out.println("Disconnected '" + this.name + "'.");
	}

	/**
	 * Method to send a character to the robot.
	 * @param current symbol that is currently on the position to write
	 * @param write symbol that is send to the robot
	 * @throws IOException Thrown if the sending or receiving of commands fails or the robot returns an error
	 */
	public void write(char current, char write) throws IOException{
		System.out.println(this.name + ": Write from "+ current + " to " + write + " ...");
		this.sendCommands("w" + current + write);

		char received = this.receiveCommand();		
		if (received == '.') {
			System.out.println(this.name + ": Writing finished successfully.");
		} else if (received == '!') {
			System.out.println(this.name + ": Write Failed");
			throw new IOException("Received error from robot '" + this.name + ": Writing failed.");
		} else {
			System.out.println(this.name + ": Common Fail, read from Robot: " + received);
			throw new IOException("Received unexpected symbol from robot '" + this.name + "'.");
		}
	}

	/**
	 * Send a command to the robot.
	 * @param cmd character that is send to the robot
	 * @throws IOException Thrown if the sending fails
	 */
	protected void sendCommand(char cmd) throws IOException {
		this.writeLock.lock();
		try {
			System.out.println("Sending to '" + this.name + "' the command '" + cmd + "'...");
			try {
				this.output.writeChar(cmd);
				this.output.flush();
				System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' finished.");
			}
			catch (IOException e) {
				System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' failed: " + e.getMessage());
				throw e;
			}
		}
		finally {
			this.writeLock.unlock();
		}
	}
	
	/**
	 * Sends a command to the robot.
	 * @param cmd string for the commands
	 * @throws IOException Thrown if sending failed.
	 */
	protected void sendCommands(String cmd) throws IOException {
		this.writeLock.lock();
		try {
			System.out.println("Sending to '" + this.name + "' the command '" + cmd + "'...");
			for (char c : cmd.toCharArray()) {
				try {
					this.output.writeChar(c);
				}
				catch (IOException e) {
					System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' failed: " + e.getMessage());
					throw e;
				}
			}
			try {
				this.output.flush();
			}
			catch (IOException e) {
				System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' failed: " + e.getMessage());
				throw e;
			}
			System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' finished.");
		}
		finally {
			this.writeLock.unlock();
		}
	}

	/**
	 * Receives command.
	 * @return a character that have been send from the robot
	 * @throws IOException Thrown if the receiving fails
	 */
	protected char receiveCommand() throws IOException {
		System.out.println("Receiving from '" + this.name + "'...");
		try {
			char cmd = this.input.readChar();
			System.out.println("Received from '" + this.name + "' the command '" + cmd + "'.");
			return cmd;
		}
		catch (IOException e) {
			System.out.println("Receiving from '" + this.name + "' failed: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Returns the name of the robot.
	 * @return Robot name string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the MAC address of the robot.
	 * @return Robot MAC address string
	 */
	public String getMacAddress() {
		return mac_address;
	}
}
