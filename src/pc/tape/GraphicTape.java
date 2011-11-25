package tape;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraphicTape extends Tape {

	JPanel tapePanel;
	JLabel labelTape;
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
	 * Creates a new graphic tape with a specific name
	 * @param name The tape name
	 * @param allowInput true/false if allowed
	 */
	public GraphicTape(String name, boolean allowInput) {
		super(name, allowInput);
	}

	public Type getType() {

		return Type.GRAPHIC;
	}

	/** 
	 * This method initializes the tape
	 * @throws TapeException If the tape has already been initialized
	 */
	public void init() throws TapeException {
		if (this.ready) throw new TapeException(this, "Tape has already been initialized.");
		System.out.println(this.name + ": Tape ready.");
		tapePanel = new JPanel();
		labelTape = new JLabel();
		//set ready
		this.ready = true;
	}

	@Override
	public void shutdown() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		//reset tape
		this.ready = false;
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

	@Override
	public void write(char c) throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");
		this.memory.put(this.position, c);
		this.printTape();

	}

	@Override
	public void moveLeft() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		position--;
		this.printTape();

	}

	@Override
	public void moveRight() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		position++;
		this.printTape();

	}

	@Override
	public void test() throws TapeException {
		if (!this.ready) throw new TapeException(this, "Tape has not been initialized.");

		System.out.println("Test"); 

	}

	@Override
	public void writeInputWord() throws TapeException {
		if (this.position != 0) {
			throw new TapeException(this, "Input word can only be written when at position 0");
		}
		//write input word to tape
		for (int i = 0; i < this.inputWord.length(); i++) {
			this.write(this.inputWord.charAt(i));
			this.moveRight();
		}
		for (int i = 0; i < this.inputWord.length(); i++) {
			this.moveLeft();
		}
	}

	public void printTape() {
		labelTape.setText(this.getMemoryAsFormattedString(-20,40));
	}

	public JPanel getTapePanel() {
		return tapePanel;
	}

	public void setTapePanel(JPanel tapePanel) {
		this.tapePanel = tapePanel;
	}

	public JLabel getLabelTape() {
		return labelTape;
	}

	public void setLabelTape(JLabel labelTape) {
		this.labelTape = labelTape;
	}

	public String getMemoryAsFormattedString(int offset, int length) {
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


}
