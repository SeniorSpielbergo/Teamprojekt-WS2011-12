import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.ColorSensor;

public class Calibrate {
	public static void main(String[] args) {
		ColorSensor cs1 = new ColorSensor(SensorPort.S1);
		ColorSensor cs2 = new ColorSensor(SensorPort.S2);
		ColorSensor counterSensor = new ColorSensor(SensorPort.S3);
		
		while(!Button.ENTER.isPressed()) {}
		cs1.initWhiteBalance();
		cs2.initWhiteBalance();
		counterSensor.initWhiteBalance();
		
		while(!Button.ESCAPE.isPressed()) {}
		cs1.initBlackLevel();
		cs2.initBlackLevel();
		counterSensor.initBlackLevel();
		
		while(!Button.ENTER.isPressed()){
			String sensor1, sensor2, sensor3;
			LCD.clearDisplay();
			sensor1 = "ColorSensor1:" + cs1.getColorNumber();            
			sensor2 = "ColorSensor2:" + cs2.getColorNumber();            
			sensor3 = "ColorSensor3:" + counterSensor.getColorNumber();
			LCD.drawString(sensor1,0,0);
			LCD.drawString(sensor2,0,1);
			LCD.drawString(sensor3,0,2);
			try {
				Thread.sleep(250);
			}
			catch (InterruptedException e) {
			}
		}
	}
} 