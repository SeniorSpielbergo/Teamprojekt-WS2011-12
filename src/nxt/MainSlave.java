import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;
import javax.bluetooth.*;;
import java.io.*;

/*
 * black = 0
 * brown = 0
 * blue = 2
 * dark green = 4
 * light green = 6
 * yellow = 7
 * red = 9
 * orange = 9
 * grey = 14
 * white = 14
 */

public class MainSlave {
	static int counter = 0;
	static ColorSensor counterSensor;
	static DataInputStream in;
	static DataOutputStream out;

	public static void main(String[] args) {
		Common.playTune("HAHA",200);
		// initialize speeds
		Motor.B.setSpeed(Common.PUSH_SPEED);
		Motor.C.setSpeed(Common.PUSH_SPEED);
		
		// setup connection
		LCD.drawString("Waiting...", 0, 0);
		NXTConnection connection = Bluetooth.waitForConnection();           
		LCD.clearDisplay();
		LCD.drawString("Connecting...", 0, 0);
		in = connection.openDataInputStream();
		out = connection.openDataOutputStream();           
		LCD.clearDisplay();
		LCD.drawString("Connected", 0, 0);
		
		char ch = ' ';

		while (true) {
			try {
				ch = in.readChar();
			}
			catch (IOException e) {
			}
	           
			LCD.clearDisplay();
			switch (ch) {
				case 't':
					LCD.drawString("Pushing...", 0, 0);
					Motor.B.rotate(Common.PUSH_ANGLE_SLAVE);
					Motor.B.rotate(Common.PUSH_ANGLE_SLAVE*(-1)+1);
					Motor.C.rotate(Common.PUSH_ANGLE_SLAVE);
					Motor.C.rotate(Common.PUSH_ANGLE_SLAVE*(-1)+1);
					LCD.clearDisplay();
					break;
			}
		}
	}
}
