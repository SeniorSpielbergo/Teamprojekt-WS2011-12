import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class Common {
	static final int TAPE_SIZE = 11;
	static final int PUSH_SPEED = 150;
	static final int LINE_SPEED = 250;
	static final int PUSH_ANGLE_MASTER = -180;
	static final int PUSH_ANGLE_SLAVE = -173;

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
					
					Motor.B.rotate(angle);
					Motor.B.rotate(angle*(-1)+1);
				}
			};
		});
		Thread t2 = new Thread(new Runnable() {
			public void run(){
				if (push2){
					Motor.C.rotate(angle);
					Motor.C.rotate(angle*(-1)+1);
				}
			};
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();	
		} catch(InterruptedException e) {

		}
	}


}