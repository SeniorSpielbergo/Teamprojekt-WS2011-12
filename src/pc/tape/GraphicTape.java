package tape;

import javax.swing.*;


import java.awt.*;
import java.util.HashMap;

/**
 * 
 * @author Nessa Baier, Nils Breyer
 *
 */
public class GraphicTape extends DisplayableTape {
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

	@Override
	public Type getType() {

		return Type.GRAPHIC;
	}

	@Override
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


	@Override
	public void shutdown() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		//reset tape
		this.ready = false;
		this.iWishToInterruptThisThread = false;
		this.position = 0;
		this.memory.clear();
	}


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

	
	/** 
	 * This method writes a symbol at the current tape position
	 * @param c Character to write ('*' is not allowed)
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
				super.setChanged();
				super.notifyObservers(Event.INPUTABORTED);
				break;
			}

			this.write(this.inputWord.charAt(i));
			this.moveRight();
		}
		for (int i = 0; i < this.inputWord.length(); i++) {
			if(this.iWishToInterruptThisThread){
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

	/**
	 * Returns a formatted string representing the current tape state.
	 * It includes the tape name, relevant parts of the memory and the head position.
	 */
	@Override
	public String toString() {
		String text;
		if (ready) {
			text = " " + this.getName() + "@pos "  + this.getPosition() + ": \n";
			int offset = this.getPosition() - (this.getPosition() % 10) - 20 ;
			text += this.getMemoryAsFormattedString(offset, 40);
		}
		else {
			text = " " + this.getName() + " (tape not initialized), input word: " + this.inputWord;
		}

		return text;
	}

	/**
	 * Returns a formatted string representing the current memory state.
	 * It includes specified part of the memory and the head position.
	 * @param offset First memory position to be included
	 * @param length Number of memory fields to be included
	 * @return The formatted string with the memory content
	 */
	public String getMemoryAsFormattedString(int offset, int length) { //only used for debugging
		if (length < 1) return "";

		//print top horizontal line
		String text = "-";
		for (int i=offset; i<=offset+length; i++) {
			text += "--";
		}
		text += "\n";

		//print memory
		text += "|";
		for (int i=offset; i<=offset+length; i++) {
			if (this.memory.get(i) != null) {
				text += this.memory.get(i);
			}
			else {
				text += "#";
			}
			text += "|";
		}
		text += "\n";

		//print head position
		if (this.position >= offset && this.position <= offset+length) {
			for (int i=offset; i<this.position; i++) {
				text += "  ";
			}
			text += " ^";
		}
		text += "\n";

		//print bottom horizontal line
		text += "-";
		for (int i=offset; i<=offset+length; i++) {
			text += "--";
		}
		text += "\n";

		return text;
	}

	@Override
	public String getStyle() {
		return this.canvas.getStyle();
	}

	@Override
	public void setStyle(String style) {
		this.canvas.setStyle(style);
	}
	
	/**
	 * Returns the height of the canvas.
	 * @return height of the canvas
	 */
	public int getHeight() {
		return this.canvas.getHeight() + this.textlabel.getHeight() + 2;
	}
}
