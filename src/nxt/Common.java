import lejos.nxt.Sound;

public class Common {
	static final int TAPE_SIZE = 9;
	static final int PUSH_SPEED = 150;
	static final int LINE_SPEED = 250;
	static final int PUSH_ANGLE_MASTER = -173;
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
	
}