import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.remote.RemoteNXT;

public class Slave extends Thread{
	private RemoteNXT nxt = null;
	private String name;
	public Slave(String name){
		this.name = name;
	}
	
	public void run() {
		try {
			  LCD.drawString("Connecting...",0,6);
			  //RemoteDevice btrd = Bluetooth.getKnownDevice("NXT_03");
			  //BTConnection btc = Bluetooth.connect(btrd);

			  nxt = new RemoteNXT(name, Bluetooth.getConnector());

			  LCD.clear();
			  LCD.drawString("Connected",0,6);
			} catch (IOException ioe) {
				LCD.clear();
				LCD.drawString("Conn Failed",0,6);
				Button.waitForPress();
				System.exit(1);
			}
		  nxt.A.forward();
		  try {
			  Thread.sleep(50000);
		  }
		  catch (Exception e) {

		  }
		  nxt.A.stop();
	}
	
	
}
