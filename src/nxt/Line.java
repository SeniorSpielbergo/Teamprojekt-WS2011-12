import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;
import lejos.util.TimerListener;

/**
 * Representation of a Lego-Tape with controlling functions.
 * @author Sven Schuster
 *
 */
public class Line extends Thread {
	private static final int TIMER_LENGTH = 800;
	private static final int LEFT_END_MARKER_COLOR = 2;
	private static final int MARKER_COLOR_MIN = 3;
	private static final int MARKER_COLOR_MAX = 10;
	
	private int counter = 0;
	private int length = 0;
	private long time;
	private boolean grey = false;
	private boolean color = false;
	private boolean failed = false;
	private ColorSensor counterSensor = new ColorSensor(SensorPort.S3);
	private Timer timer;
	private TimerListener tl = new TimerListener(){ 
		public void timedOut() {
			Motor.A.stop();
			failed = true;
		}
	};
	
	/**
	 * Creates a new Line with given length. The Line will initially move to the leftmost position.
	 * @param length Length of line
	 */
	public Line(int length) {
		this.length = length;
		Motor.A.setSpeed(Common.LINE_SPEED);
		initialize();
	}
	
	// Initializing the Line to the mostleft position.
	private void initialize() {
		if(counterSensor.getColorNumber() != Line.LEFT_END_MARKER_COLOR)
			Motor.A.forward();
		while(counterSensor.getColorNumber() != Line.LEFT_END_MARKER_COLOR) {}
		Motor.A.stop();
	}
	
	/**
	 * Thread counting at which position the Tape currently is, starting at the leftmost position 0.
	 */
	public void run() {
		while(true) {
			if ((counterSensor.getColorNumber() >= Line.MARKER_COLOR_MIN && counterSensor.getColorNumber() <= Line.MARKER_COLOR_MAX) || counterSensor.getColorNumber() == Line.LEFT_END_MARKER_COLOR){
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
			else
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
			failed = false;
			timer = new Timer(this.TIMER_LENGTH,tl);
			Motor.A.forward();
			timer.start();
			while(!Motor.A.isStopped()){}
			if(failed) {
				LCD.drawString("Security warning: Timer elapsed!", 0, 2);
				return false;
			}
			timer.stop();
			LCD.clear();
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
			timer = new Timer(this.TIMER_LENGTH,tl);
			Motor.A.backward();
			timer.start();
			while(!Motor.A.isStopped()){}
			if(failed) {
				LCD.drawString("Security warning: Timer elapsed!", 0, 2);
				return false;
			}
			timer.stop();
			LCD.clear();
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
	 * Returns the current position, starting with the leftmost position 0.
	 * @return int position
	 */
	public int getCount() {
		return this.counter;
	}
}