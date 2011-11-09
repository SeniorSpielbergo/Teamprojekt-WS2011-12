import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;
import lejos.util.*;

/**
 * Representation of a Lego-Tape with controlling functions.
 * @author Sven Schuster
 *
 */
public class Line extends Thread {
	private int counter = 0;
	private int length = 0;
	private long time;
	private boolean grey = false;
	private boolean color = false;
	private boolean failed = false;
	private ColorSensor counterSensor = new ColorSensor(SensorPort.S3);
	
	private Timer timer = new Timer(2000, 
		new TimerListener(){ 
			public void timedOut() {
				LCD.drawString("Timer abgelaufen", 0, 1);
				timer.stop();
				Motor.A.stop();
				failed = true;
			}
		}
	);
	
	/**
	 * Creates a new Line with given length. The Line will initially move to the mostleft position.
	 * @param length Length of line
	 */
	public Line(int length) {
		this.length = length;
		Motor.A.setSpeed(Common.LINE_SPEED);
		initialize();
	}
	
	// Initializing the Line to the mostleft position.
	private void initialize() {
		if(counterSensor.getColorNumber() != 2)
			Motor.A.forward();
		while(counterSensor.getColorNumber() != 2) {}
		Motor.A.stop();
	}
	
	/**
	 * Thread counting at which position the Tape currently is, starting at the mostleft position 0.
	 */
	public void run() {
		while(true) {
			if ((counterSensor.getColorNumber() >= 5 && counterSensor.getColorNumber() <= 12) || counterSensor.getColorNumber() == 2 ) {
				if (grey && !color) {
					grey = false;
					color = true;
				}
				if (color && !grey) {
					if (Motor.A.isBackward()) {
						color = false;
						this.counter++;
						Motor.A.stop();
					}
					if (Motor.A.isForward()) {
						color = false;
						this.counter--;
						Motor.A.stop();
					}
				}
			}
			else if (counterSensor.getColorNumber() == 3)
				grey = true;
		}
	}

	/**
	 * Moving the head one position to the left.
	 * @return true - Repositioning successful<br />
	 * 		   false - Repositioning failed
	 */
	public boolean moveLeft() {
		if(this.counter == 0)
			return false;
		else{
			//timer.stop();
			timer.setDelay(2000);
			failed = false;
			Motor.A.forward();
			timer.start();
			while(!Motor.A.isStopped()){}
			if(failed)
				return false;
			timer.stop();
			return true;
		}
	}
	
	/**
	 * Moving the head one position to the right.
	 * @return true - Repositioning successful<br />
	 * 		   false - Repositioning failed
	 */
	public boolean moveRight() {
		if(this.counter == this.length)
			return false;
		else {
			failed = false;
			//timer.stop();
			timer.setDelay(2000);
			Motor.A.backward();
			timer.start();
			while(!Motor.A.isStopped()){}
			if(failed)
				return false;
			timer.stop();
			return true;
		}
	}

	/**
	 * Returns the ColorSensor responsible for detecting the current position.
	 * @return ColorSensor
	 */
	public ColorSensor getCounterSensor() {
		return this.counterSensor;
	}
	
	/**
	 * Returns the current position, starting with the mostleft position 0.
	 * @return int position
	 */
	public int getCount() {
		return this.counter;
	}
}