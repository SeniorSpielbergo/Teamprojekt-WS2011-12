import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public abstract class Robot {
	private String name;
	private String mac_address;
	private NXTComm comm = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;

	public Robot(String name, String mac_address) {
		this.name = name;
		this.mac_address = mac_address;
	}
	
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
		
		//try {
			this.comm.open(info);
			
			InputStream is = comm.getInputStream();
			OutputStream os = comm.getOutputStream();

			this.input = new DataInputStream(is);
			this.output = new DataOutputStream(os);
			
			System.out.println("Connected to '" + this.name + "'.");
		//}
		//catch (IOException e) {
		//	System.out.println("Connecting to '" + this.name + "' failed: " + e.getMessage());
		//	throw e;
		//}
	}
	
	public void disconnect() {
		System.out.println("Disconnecting '" + this.name + "'...");
		this.sendCommand('q');
		try {
			this.output.close();
			this.comm.close();
		}
		catch (IOException e) {
			System.out.println("WARNING: Disconnecting '" + this.name + "' didn't work properly: " + e.getMessage());
		}

		System.out.println("Disconnected '" + this.name + "'.");
	}
	
	protected void sendCommand(char cmd) {
		System.out.println("Sending to '" + this.name + "' the command '" + cmd + "'...");
		try {
			this.output.writeChar(cmd);
			this.output.flush();
			System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' finished.");
		}
		catch (IOException e) {
			System.out.println("Sending to '" + this.name + "' the command '" + cmd + "' failed: " + e.getMessage());
		}
	}
	
	protected char receiveCommand() {
		System.out.println("Receiving from '" + this.name + "'...");
		try {
			char cmd = this.input.readChar();
			System.out.println("Received from '" + this.name + "' the command '" + cmd + "'.");
			return cmd;
		}
		catch (IOException e) {
			System.out.println("Receiving from '" + this.name + "' failed: " + e.getMessage());
			return ' '; //TODO: better solution?
		}
	}
}
