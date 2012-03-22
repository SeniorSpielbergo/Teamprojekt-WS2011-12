package tape;

import gui.ErrorDialog;

import java.lang.String;
import java.util.Observable;

/**
 * This abstract class is the common base class for LEGO and Console/PC tapes.
 * It contains only few concrete methods.
 *
 * @author Nils Breyer, Nessa Baier
 */

public abstract class Tape extends Observable implements Runnable{
	/**
	 * True if the tape has been initialized.
	 */
	protected boolean ready = false;
	/**
	 * The current head position.
	 */
	protected int position = 0;
	/**
	 * The name of the tape.
	 */
	protected String name = "";
	/**
	 * The initial input word for the tape.
	 */
	protected String inputWord = "";
	/**
	 * Allow input on the tape (default true).
	 */
	protected boolean allowInput = true;
	/**
	 * True if the thread should stop.
	 */
	protected boolean iWishToInterruptThisThread;
	/**
	 * Thread for writing the input word.
	 */
	protected Thread writeInputWordThread;
	/**
	 * delay enabled/disabled
	 */
	private boolean delay = true;

	/**
	 * Enum representing the tape types
	 */
	public enum Type {
		/**
		 * LEGO tape
		 */
		LEGO, 
		/**
		 * Virtual tape on the console
		 */
		CONSOLE, 
		/**
		 * Virtual tape in the tapes window
		 */
		GRAPHIC
	}
	
	public enum Event{
		INPUTFINISHED,
		INPUTABORTED
	}

	/**
	 * Creates a new tape.
	 */
	public Tape() {
		this.name = "Default tape";
		this.writeInputWordThread = new Thread(this);
		this.iWishToInterruptThisThread = false;
		
	}

	/**
	 * Creates a new tape.
	 * @param allowInput true/false if allowed
	 */
	public Tape(boolean allowInput) {
		this.name = "Default tape";
		this.allowInput = allowInput;
		this.iWishToInterruptThisThread = false;
		this.writeInputWordThread = new Thread(this);
	}

	/**
	 * Creates a new tape with a specific name.
	 * @param name The tape name
	 */
	public Tape(String name) {
		this.name = name;
		this.iWishToInterruptThisThread = false;
		this.writeInputWordThread = new Thread(this);
	}

	/**
	 * Creates a new tape with a specific name.
	 * @param name The tape name
	 * @param allowInput true/false if allowed
	 */
	public Tape(String name, boolean allowInput) {
		this.name = name;
		this.allowInput = allowInput;
		this.iWishToInterruptThisThread = false;
		this.writeInputWordThread = new Thread(this);
	}

	/**
	 * @return the iWishToInterruptThisThread
	 */
	public boolean isiWishToInterruptThisThread() {
		return iWishToInterruptThisThread;
	}

	/**
	 * @param iWishToInterruptThisThread the iWishToInterruptThisThread to set
	 */
	public void setiWishToInterruptThisThread(boolean iWishToInterruptThisThread) {
		this.iWishToInterruptThisThread = iWishToInterruptThisThread;
	}

	/**
	 * Initializes the tape.
	 * This method has to be called before any moving or read/write command can be executed.
	 * @throws TapeException Thrown if the initialization process fails
	 * @see #shutdown()
	 */
	public abstract void init() throws TapeException;

	/**
	 * Shutdown the tape.
	 * @throws TapeException Thrown if the shutdown process fails
	 * @see #init()
	 */
	public abstract void shutdown() throws TapeException;

	/** 
	 * This method returns the char at the current tape position
	 * @return Character at the current position
	 * @throws TapeException If the tape has not been initialized
	 * @see #write(char)
	 */
	public abstract char read() throws TapeException;

	/** 
	 * This method writes a symbol at the current tape position
	 * @param c Character to write (allowed characters are #, 0, 1, 2)
	 * @throws TapeException If the tape has not been initialized
	 * @see #read()
	 */
	public abstract void write(char c) throws TapeException;

	/**
	 * This method moves the tape one field to the left
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveRight()
	 */
	public abstract void moveLeft() throws TapeException;

	/** 
	 * This method moves the tape one field to the right
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveLeft()
	 */
	public abstract void moveRight() throws TapeException;

	/**
	 * @throws TapeException Thrown if writing the input word fails
	 */
	public abstract boolean writeInputWord() throws TapeException;
	
	public void run(){
		try{
			if(!this.writeInputWord()){
				super.setChanged();
				super.notifyObservers(Event.INPUTABORTED);
			}
			else{
				super.setChanged();
				super.notifyObservers(Event.INPUTFINISHED);
			}
		}
		catch(TapeException e){
			ErrorDialog.showError("Writing the input word failed.", e);
		}
		
	}
	
	/**
	 * Starts writing the input word in an own thread.
	 */
	public void startWritingInputWord(){
		this.writeInputWordThread = new Thread(this);
		this.writeInputWordThread.start();
	}

	/**
	 * Returns the thread in which the input word is written.
	 * @return thread in which the input word gets written
	 */
	public Thread getWriteInputWordThread() {
		return writeInputWordThread;
	}

	/**
	 * This method returns the tape type
	 * @return Either "LEGO" or "console" or "graphic" depending on the type of the tape
	 */
	public abstract Type getType();

	/**
	 * Checks if the tape has been initialized
	 * @return Returns true, if the tape has been initialized and false if not.
	 */
	public boolean isReady() {
		return this.ready;
	}

	/**
	 * Get the current position of the tape
	 * @return The head position on the tape
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * Get the name of the tape
	 * @return Returns the tape name string
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the tape
	 * @param name New tape name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the input word
	 * @return Input word
	 */
	public String getInputWord() {
		return inputWord;
	}

	/**
	 * Set the input word
	 * @param inputWord New input word
	 * @throws TapeException Exception if error occurs while setting input word
	 */
	public void setInputWord(String inputWord) throws TapeException {
		if (this.ready) throw new TapeException(this, "Tape has already been initialized. Input word can't be changed at this time anymore.");
		this.inputWord = inputWord;
	}

	/**
	 * Returns true if input is allowed.
	 */
	public boolean getInputAllowed() {
		return this.allowInput;
	}
	
	/**
	 * Sets the delay.
	 * @param b 
	 */
	public void setDelay(boolean b){
		this.delay = b;
	}
	
	/**
	 * Returns if the delay is enabled.
	 * @return True if enabled.
	 */
	public boolean isDelayEnabled(){
		return this.delay;
	}
}
