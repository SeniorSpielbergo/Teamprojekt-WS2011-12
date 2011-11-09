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
		c = false;
		LCD.drawString("timed out",0,3);
		LCD.drawString(""+c,0,2);
		try {Thread.sleep(delay);}
		catch (InterruptedException e) {}
		LCD.drawString(""+c,5,2);
		if (!c){
			LCD.drawString("timed out",0,6);
			tl.timedOut();
		}
	}

	public void start() {
		(new Thread(this)).start();
	}

	public void stop() {
		LCD.drawString("ich bin in stop()",0,4);
		c = true;
	}

}

