import lejos.nxt.Sound;

/**
 * "Tetris sound"
 * @author Nessa Baier
 *
 */
public class TetrisSound implements Runnable{

	/**
	 * The thread which plays the sound.
	 */
	Thread soundThread = new Thread(this);

	/**
	 * Possible values of notes.
	 */
	static final int QUARTER = 500;
	static final int EIGHTER = 250;

	/**
	 * Possible pitches.
	 */
	static final int Bb = 233;
	static final int C = 261;
	static final int CIS = 277;
	static final int DES = 277;
	static final int D = 293;
	static final int ES = 311;
	static final int DIS = ES;
	static final int E = 329;
	static final int F = 349;
	static final int FIS = 370;
	static final int GES = FIS;
	static final int G = 392;
	static final int GIS = 415;
	static final int AS = GIS;
	static final int Bb1 = 466;
	static final int B = 494;


	/**
	 * Speed of sound.
	 */
	static final double speed = 1;

	/**
	 * True if sound is/should be off.
	 */
	private boolean soundOff = false;

	/**
	 * Starts the sound thread.
	 */
	public void start(){
		this.soundThread.start();
	}

	/**
	 * Plays the sound.
	 */
	public void run(){
		this.playSound();
	}


	//Plays the tetris sound.
	private void playSound(){
		while(!this.soundOff){
		Sound.playNote(Sound.PIANO,F, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,C, (int)(EIGHTER * speed));
		Sound.playNote(Sound.PIANO,DES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,ES, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,DES,(int) (EIGHTER * speed));
		Sound.playNote(Sound.PIANO,C, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,Bb, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,Bb,(int) (EIGHTER * speed));
		Sound.playNote(Sound.PIANO,DES,(int) (EIGHTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,F, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,ES, (int)(EIGHTER * speed));
		Sound.playNote(Sound.PIANO,DES,(int) (EIGHTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,C, (int)(QUARTER * speed + EIGHTER * speed));
		Sound.playNote(Sound.PIANO,DES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,ES,(int) (QUARTER * speed));
		Sound.playNote(Sound.PIANO,F,(int) (QUARTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,DES,(int) (QUARTER * speed));
		Sound.playNote(Sound.PIANO,Bb, (int)(QUARTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,Bb, (int)(QUARTER * speed));
		if(this.soundOff)
			break;

		Sound.pause((int) ((QUARTER+EIGHTER) *speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,ES, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,GES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;

		Sound.playNote(Sound.PIANO,Bb1, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,AS, (int)(EIGHTER * speed));
		Sound.playNote(Sound.PIANO,GES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;
		Sound.playNote(Sound.PIANO,F, (int)((QUARTER+EIGHTER) * speed));
		Sound.playNote(Sound.PIANO,DES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;
		Sound.playNote(Sound.PIANO,F, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,ES, (int)(EIGHTER * speed));
		Sound.playNote(Sound.PIANO,DES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;
		Sound.playNote(Sound.PIANO,C, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,C, (int)(EIGHTER * speed));
		Sound.playNote(Sound.PIANO,DES, (int)(EIGHTER * speed));
		if(this.soundOff)
			break;
		Sound.playNote(Sound.PIANO,ES, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,F, (int)(QUARTER * speed));
		if(this.soundOff)
			break;
		Sound.playNote(Sound.PIANO,DES, (int)(QUARTER * speed));
		Sound.playNote(Sound.PIANO,Bb, (int)(QUARTER * speed));
		if(this.soundOff)
			break;
		Sound.playNote(Sound.PIANO,Bb, (int)(QUARTER * speed));
		Sound.pause((int) (QUARTER *speed));
		}

	}

	/**
	 * Muting sound.
	 * @param enabled
	 */
	public void enableSound(boolean enabled){

		if(enabled)
			Sound.setVolume(20);
		else{
			Sound.setVolume(0);
		}
	}
	
	/**
	 * Disables sound.
	 */
	public void setSoundOff(){
		this.soundOff = true;
	}
}


