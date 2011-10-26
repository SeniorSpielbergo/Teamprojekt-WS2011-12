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
		ColorSensor cs3 = new ColorSensor(SensorPort.S3);
		NXTConnection connection = null;
		LCD.drawString("waiting for bt connection", 1, 1);
		connection = Bluetooth.waitForConnection();
		LCD.drawString("Connected", 0, 2);
		DataOutputStream dataOut = connection.openDataOutputStream();
		int cs1Val, cs2Val, cs3Val;
		LCD.drawString("start", 0, 3);
		try {
			while(!Button.ENTER.isPressed()){
				LCD.drawString("while", 0, 4);
				cs1Val = cs1.getColorNumber();
				cs2Val = cs2.getColorNumber();
				cs3Val = cs3.getColorNumber();
				LCD.clear();
				LCD.drawInt(cs1Val, 0,0);
				LCD.drawInt(cs2Val, 0, 1);
				LCD.drawInt(cs3Val, 0, 2);
				dataOut.writeInt(cs1Val);
				dataOut.writeInt(cs2Val);
				dataOut.writeInt(cs3Val);
				dataOut.flush();
				Thread.sleep(2000);
			}
		} catch(Exception e) {
			System.out.print(e);
		}

	}
}

