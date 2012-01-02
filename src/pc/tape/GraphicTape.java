package tape;

import javax.swing.*;


import java.awt.*;
import java.util.HashMap;

/**
 * 
 * @author Nessa Baier, Nils Breyer
 *
 */
public class GraphicTape extends Tape {

	/**
	 * Panel for the tape.
	 */
	JPanel tapePanel;
	/**
	 * Label for the tape's name
	 */
	JLabel textlabel;
	/**
	 * Canvas for displaying the tapes content.
	 */
	GraphicTapePanel canvas;
	/**
	 * Stores the current tape content
	 */
	HashMap<Integer, Character> memory = new HashMap<Integer, Character>();

	/**
	 * Creates a new graphic tape
	 */
	public GraphicTape() {
		super("Default graphic tape");
	}

	/**
	 * Creates a new graphic tape
	 * @param allowInput true/false if allowed
	 */
	public GraphicTape(boolean allowInput) {
		super("Default graphic tape", allowInput);
	}

	/**
	 * Creates a new graphic tape with a specific name
	 * @param name The tape name
	 */
	public GraphicTape(String name) {
		super(name);
	}

	/**
	 * Creates a new graphic tape with a specific name.
	 * @param name The tape name
	 * @param allowInput true/false if allowed
	 */
	public GraphicTape(String name, boolean allowInput) {
		super(name, allowInput);
	}

	/**
	 * Returns the tape's type.
	 */
	public Type getType() {

		return Type.GRAPHIC;
	}

	/** 
	 * This method initializes the tape.
	 * @throws TapeException If the tape has already been initialized.
	 */
	public void init() throws TapeException {
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");

		System.out.println(this.name + ": Tape ready.");
		//add graphic stuff
		this.tapePanel = new JPanel(new BorderLayout());
		this.textlabel = new JLabel(this.name);
		this.canvas = new GraphicTapePanel(this);
		this.tapePanel.add(textlabel,BorderLayout.NORTH);
		this.tapePanel.add(this.canvas, BorderLayout.CENTER);

		//set ready
		this.ready = true;
	}

	/**
	 * This method shuts the tape down.
	 * @throws TapeException If the tape has not been initialized
	 */
	@Override
	public void shutdown() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		//reset tape
		this.ready = false;
		this.iWishToInterruptThisThread = false;
		this.position = 0;
		this.memory.clear();
	}

	/** 
	 * This method returns the char at the current tape position
	 * @return Character at the current position
	 * @throws TapeException If the tape has not been initialized
	 * @see #write(char)
	 */
	@Override
	public char read() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		if (this.memory.get(this.position) != null) {
			return this.memory.get(this.position);
		}
		else {
			return '#';
		}
	}

	@Override
	/** 
	 * This method writes a symbol at the current tape position
	 * @param c Character to write (allowed characters are #, 0, 1, 2)
	 * @throws TapeException If the tape has not been initialized
	 * @see #read()
	 */
	public void write(char c) throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");
		char oldChar = this.get(this.getPosition());
		this.memory.put(this.position, c);
		this.canvas.write(oldChar);
		if(this.isDelayEnabled()){
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){}
		}

	}

	@Override
	/** 
	 * This method moves the tape one field to the left
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveRight()
	 */
	public void moveLeft() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		position--;
		this.canvas.move(position + 1);
		if(this.isDelayEnabled()){
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){}
		}
	}

	@Override
	/** 
	 * This method moves the tape one field to the right
	 * @throws TapeException If the tape has not been initialized
	 * @see #moveLeft()
	 */
	public void moveRight() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");
		
		position++;
		this.canvas.move(position - 1);

		if(this.isDelayEnabled()){
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){}
		}
	}

	@Override
	/**
	 * This method runs a test on the tape. It is not specified what this method actually does.
	 * @throws TapeException If the tape has not been initialized
	 */
	public void test() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		System.out.println("Test"); 

	}

	@Override
	/**
	 * Writes the input word on the tape.
	 * @throws TapeException If something went wrong with the tapes.
	 */
	public boolean writeInputWord() throws TapeException {
		if (this.position != 0) {
			throw new TapeException(this, "Input word can only be written when at position 0");
		}
		//write input word to tape
		for (int i = 0; i < this.inputWord.length(); i++) {
			if(this.iWishToInterruptThisThread){
				System.out.println("thread wird gestoppt");
				super.setChanged();
				super.notifyObservers(Event.INPUTABORTED);
				break;
			}

			this.write(this.inputWord.charAt(i));
			this.moveRight();
		}
		for (int i = 0; i < this.inputWord.length(); i++) {
			if(this.iWishToInterruptThisThread){
				System.out.println("thread wird gestoppt");
				super.setChanged();
				super.notifyObservers(Event.INPUTABORTED);
				break;
			}
			this.moveLeft();
		}
		if(this.iWishToInterruptThisThread)
			return false;
		return true;
	}

	/**
	 * Returns the tapePanel.
	 * @return tapePanel
	 */
	public JPanel getTapePanel() {
		return tapePanel;
	}
	
	public char get(int position) {
		if (this.memory.get(position) != null) {
			return this.memory.get(position);
		}
		else {
			return '#';
		}
	}

	@Override
	public Object clone() {
		return this;
	}
}
