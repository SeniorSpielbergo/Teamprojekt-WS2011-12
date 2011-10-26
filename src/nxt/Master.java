import lejos.nxt.*;
import lejos.nxt.remote.*;
import lejos.nxt.addon.ColorSensor;
import lejos.nxt.comm.*;

import java.io.*;

import javax.bluetooth.*;
public class Master {
	public static void main(String[] args) throws Exception {
		/*RemoteNXT nxt = null;
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
		 */
		ColorSensor cs1 = new ColorSensor(SensorPort.S1);
		ColorSensor cs2 = new ColorSensor(SensorPort.S2);
		NXTConnection connection = null;
		LCD.drawString("waiting for bt connection", 1, 1);
		connection = Bluetooth.waitForConnection();
		DataOutputStream dataOut = connection.openDataOutputStream();

		try {
			while(!Button.ENTER.isPressed());{
				dataOut.write(cs1.getColorNumber());
				dataOut.write(cs2.getColorNumber());
				dataOut.flush();
				Thread.sleep(2000);
			}
		} catch(Exception e) {
			System.out.print(e);
		}

	}
}

