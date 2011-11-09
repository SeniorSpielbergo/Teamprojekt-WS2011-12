import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.util.*;

public class Timer implements Runnable {
	private int delay;
	private Line line;
	private TimerListener tl;
	private boolean c = false;

	public Timer(int delay, TimerListener tl){
		this.tl = tl;
		this.delay = delay;
	}

	public void run () {
		try {Thread.sleep(delay);}
		catch (InterruptedException e) {}
		if (!c)
			tl.timedOut();
	}

	public void start() {
		(new Thread(this)).start();
	}

	public void stop() {
		c = true;
	}
}

