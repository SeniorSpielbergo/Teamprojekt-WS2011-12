import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.util.*;

/**
 * Represents a threaded Timer using a given delay, after which a timedOut-Message will be sent to given TimerListener.
 * @author Vanessa Baier
 * @author Sven Schuster
 *
 */
public class Timer implements Runnable {
	private int delay;
	private TimerListener tl;
	private boolean c = false;

	/**
	 * Initialize Timer with given delay and TimerListener
	 * @param delay Delay in milliseconds
	 * @param tl TimerListener
	 */
	public Timer(int delay, TimerListener tl){
		this.tl = tl;
		this.delay = delay;
	}

	/**
	 * Running the Timer.
	 */
	public void run () {
		try {Thread.sleep(delay);}
		catch (InterruptedException e) {}
		if (!c)
			tl.timedOut();
	}

	/**
	 * Start the Timer.
	 */
	public void start() {
		(new Thread(this)).start();
	}

	/**
	 * Stop the Timer causing it to not sending a timedOut-Message to TimerListener.
	 */
	public void stop() {
		c = true;
	}
}

