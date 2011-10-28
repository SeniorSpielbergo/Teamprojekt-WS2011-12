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

public class Main {
	static int counter = 0;
	static ColorSensor counterSensor;
	static RemoteNXT nxt_03;

	public static void main(String[] args) {
		try {
			nxt_03 = new RemoteNXT("NXT_03", Bluetooth.getConnector());
		}
		catch (IOException e) {
			Main.playTune("CDECAAHCDEDCHH",200);
		}
		Main.playTune("HHCDEDCHAACEDC",200);
		final int PUSH_SPEED = 150;
		final int LINE_SPEED = 250;
		final int PUSH_ANGLE = -155;
		final int PUSH_ANGLE_REMOTE = -165;
		String sensor1, sensor2, sensor3, counterString;
		ColorSensor cs1 = new ColorSensor(SensorPort.S1);
		ColorSensor cs2 = new ColorSensor(SensorPort.S2);
		TouchSensor ts1 = new TouchSensor(SensorPort.S4);
		// initialize speeds
		Motor.A.setSpeed(LINE_SPEED);
		Motor.B.setSpeed(PUSH_SPEED);
		Motor.C.setSpeed(PUSH_SPEED);
		nxt_03.B.setSpeed(PUSH_SPEED);
		nxt_03.C.setSpeed(PUSH_SPEED);
		//initialize counter
		Counter c = new Counter();
		c.start();
		counterSensor = c.getCounterSensor();
		
		// sensor listener for emergency stop
		SensorPort.S4.addSensorPortListener(new SensorPortListener() {
			public void stateChanged(SensorPort port, int oldValue, int newValue) {
				if (oldValue > 500 && newValue < 500) {
					Motor.A.stop();
					Motor.B.stop();
					Motor.C.stop();
					nxt_03.A.stop();
					nxt_03.B.stop();
					nxt_03.C.stop();
					Main.playTune("CDECAAHCDEDCHH",200);
				}
			}
		});

		while(!Button.ESCAPE.isPressed()) {
			if(ts1.isPressed()) {
				cs1.initWhiteBalance();
				cs2.initWhiteBalance();
				counterSensor.initWhiteBalance();
			}
		}

		while(true){             
			LCD.clearDisplay();
			sensor1 = "ColorSensor1:" + cs1.getColorNumber();            
			sensor2 = "ColorSensor2:" + cs2.getColorNumber();            
			sensor3 = "ColorSensor3:" + counterSensor.getColorNumber();
			counterString = "Counter:" + counter;
			LCD.drawString(sensor1,0,0);
			LCD.drawString(sensor2,0,1);
			LCD.drawString(sensor3,0,2);
			LCD.drawString(counterString,0,4);
			try {
				Thread.sleep(250);
			}
			catch (InterruptedException e) {
			}
		}
	}
	
	public static void playTune(String m, int t) {
		char[] charArray = m.toCharArray();
		for(char c : charArray) {
			if(c == 'C') {
				Sound.playNote(Sound.PIANO,261,t);
			}
			if(c == 'D') {
				Sound.playNote(Sound.PIANO,293,t);
			}
			if(c == 'E') {
				Sound.playNote(Sound.PIANO,329,t);
			}
			if(c == 'F') {
				Sound.playNote(Sound.PIANO,349,t);
			}
			if(c == 'G') {
				Sound.playNote(Sound.PIANO,396,t);
			}
			if(c == 'A') {
				Sound.playNote(Sound.PIANO,440,t);
			}
			if(c == 'B') {
				Sound.playNote(Sound.PIANO,475,t);
			}
			if(c == 'H') {
				Sound.playNote(Sound.PIANO,495,t);
			}
			if(c == 'X') {
				Sound.playNote(Sound.PIANO,528,t);
			}
		}
	}
}
