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

	}
	
	public void connect() {
		System.out.println("Connecting to '" + this.name + "'...");
		
		try {
			this.comm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		}
		catch (Exception e) {
			System.out.println("Connecting to '" + this.name + "' failed while preparing bluetooth: " + e.getMessage());
			throw e;
		}

		NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, this.name, this.mac_address);
		
		try {
			this.comm.open(info);
			
			InputStream is = comm.getInputStream();
			OutputStream os = comm.getOutputStream();

			this.input = new DataInputStream(is);
			this.output = new DataOutputStream(os);
			
			System.out.println("Connected to '" + this.name + "'.");
		}
		catch (Exception e) {
			System.out.println("Connecting to '" + this.name + "' failed: " + e.getMessage());
			throw e;
		}
	}
	
	public void disconnect() {
		System.out.println("Disconnecting '" + this.name + "'...");
		this.output.writeChar('q');
		this.output.flush();
		this.output.close();
		this.comm.close();
		System.out.println("Disconnected '" + this.name + "'.");
	}
	
	protected void sendCommand(char cmd) {
		
	}
	
	protected char receiveCommand() {
		
	}
}
