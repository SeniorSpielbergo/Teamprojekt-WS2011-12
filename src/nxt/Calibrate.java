import lejos.nxt.Button;
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
	}
}