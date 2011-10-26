import lejos.nxt.*;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;
import java.io.*;
import javax.bluetooth.*;
public class Master {
  public static void main(String[] args) throws Exception {
	  RemoteNXT nxt = null;
	  try {
		  LCD.drawString("Connecting...",0,0);
		  //RemoteDevice btrd = Bluetooth.getKnownDevice("NXT_03");
		  //BTConnection btc = Bluetooth.connect(btrd);

		  nxt = new RemoteNXT("NXT_03",Bluetooth.getConnector());
	
		  LCD.clear();
		  LCD.drawString("Connected",0,0);
		} catch (IOException ioe) {
		  LCD.clear();
		  LCD.drawString("Conn Failed",0,0);
		  Button.waitForPress();
		  System.exit(1);
		}
	  nxt.A.forward();
	  try {
		  Thread.sleep(1000);
	  }
	  catch (Exception e) {
		  
	  }
  }
}

