import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class Common {
	static final int TAPE_SIZE = 11;
	static final int PUSH_SPEED = 180;
	static final int LINE_SPEED = 270;
	static final int PUSH_ANGLE_MASTER = -160;
	static final int PUSH_ANGLE_SLAVE = -153;

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
	public static void pushBits(final boolean push1,final boolean push2, boolean master) {
		final int angle;
		if(master) {
			angle = Common.PUSH_ANGLE_MASTER;
		} else {
			angle = Common.PUSH_ANGLE_SLAVE;
		}

		Thread t1 = new Thread(new Runnable() {
			public void run(){
				if (push1){
					bugfixedRotate(Motor.B,angle);
					bugfixedRotate(Motor.B,angle*(-1)+1);
				}
			};
		});
		Thread t2 = new Thread(new Runnable() {
			public void run(){
				if (push2){
					bugfixedRotate(Motor.C,angle);
					bugfixedRotate(Motor.C,angle*(-1)+1);
				}
			};
		});
		try {
			Thread.sleep(1000);
		}
		catch (Exception e) {

		}
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();	
		} catch(InterruptedException e) {
			Common.playTune("CXCXCXCXCXCXCXCXC",100);

		}
	}

	public static void bugfixedRotate(Motor motor, int angle) {
		motor.stop();
		motor.resetTachoCount();
		int tacho = motor.getTachoCount();
		boolean slowSpeed = false;
		if (angle >= 0) {
			motor.forward();
			while (tacho < angle) {
				if (!slowSpeed && angle - tacho < 30) {
					motor.setSpeed(PUSH_SPEED/2);
					slowSpeed = true;
				}
				try {
					Thread.sleep(50);
				}
				catch (Exception e) {

				}
				tacho = motor.getTachoCount();
			}
		}
		else {
			motor.backward();
			while (tacho > angle) {
				if (!slowSpeed && tacho - angle < 30) {
					motor.setSpeed(PUSH_SPEED/2);
					slowSpeed = true;
				}
				try {
					Thread.sleep(50);
				}
				catch (Exception e) {

				}
				tacho = motor.getTachoCount();
			}
		}

		motor.stop();
		motor.setSpeed(PUSH_SPEED);
	}


}