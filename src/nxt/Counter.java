import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;

public class Counter extends Thread {
	protected int counter = 0;
	protected boolean grey = true;
	protected boolean color = false;
	protected ColorSensor counterSensor = new ColorSensor(SensorPort.S3);

	public void run() {
		while (!Button.ENTER.isPressed()) {
			if (counterSensor.getColorNumber() >= 5 && counterSensor.getColorNumber() <= 10) {
				if (grey && !color) {
					grey = false;
					color = true;
				}
				if (color && !grey) {
					if (Motor.A.isBackward()) {
						color = false;
						counter++;
						Motor.A.stop();
					}
					if (Motor.A.isForward()) {
						color = false;
						counter--;
						Motor.A.stop();
					}
					Main.counter = counter;
				}
			}
			else if (counterSensor.getColorNumber() < 5) {
				grey = true;
			}
		}
	}

	public ColorSensor getCounterSensor() {
		return this.counterSensor;
	}
}