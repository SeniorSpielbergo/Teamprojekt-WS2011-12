package machine.brainfuck;

import gui.MachineEditor;
import gui.brainfuck.*;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import tape.TapeException;
import tape.ConsoleTape;

import machine.Machine;
import machine.Simulation;
import machine.turing.TuringMachine;

/**
 * Represents a brainfuck machine and provides methods to handle it.
 * @author Sven Schuster
 * 
 */
public class BrainfuckMachine extends Machine {
	public static final String FILE_EXTENSION = ".bf";

	private BrainfuckEditor brainfuckEditor;
	private String code = "Type your brainfuck code here";

	/**
	 * Creates a new brainfuck machine with input-, output- and actiontape.
	 */
	public BrainfuckMachine() {
		super();
		tapes.add(new ConsoleTape("Input", true));
		tapes.add(new ConsoleTape("Output", false));
		tapes.add(new ConsoleTape("Action", false));		
	}

	/**
	 * Creates a new brainfuck machine with input-, output- and actiontape.
	 * @param name Name of machine
	 */
	public BrainfuckMachine(String name) {
		super(name);
		tapes.add(new ConsoleTape("Input"));
		tapes.add(new ConsoleTape("Output"));
		tapes.add(new ConsoleTape("Action"));
	}

	/**
	 * Saves the brainfuck code to given filename.
	 * @param filename Filename
	 */
	public void save(String filename) throws IOException {
		if (brainfuckEditor != null) {
			this.code = brainfuckEditor.getCode();
		}
		FileWriter fstream = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(code);
		out.close();
	}

	/**
	 * Loads brainfuck code out of given filename.
	 * @param filename Filename to load code from.
	 * @throws FileNotFoundException If the file does not exist.
	 * @throws IOException If file could not be read.
	 */
	public void load(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		byte[] buffer = new byte[(int) file.length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
		f.read(buffer);
		code = new String(buffer);
		if (brainfuckEditor != null) {
			brainfuckEditor.setCode(code);
		}
	}

	/**
	 * Creates a new brainfuck simulation.
	 * @return BrainfuckSimulation
	 * @throws TapeException If the tape failed to initialize.
	 */
	public Simulation createSimulation() throws TapeException {
		BrainfuckSimulation brainfuckSimulation = new BrainfuckSimulation(this);
		return brainfuckSimulation;
	}

	/**
	 * Creates a new brainfuck editor.
	 * @return BrainfuckEditor
	 */
	protected MachineEditor createEditor() {
		brainfuckEditor = new BrainfuckEditor();
		brainfuckEditor.setCode(code);
		return brainfuckEditor;
	}

	/**
	 * Returns the Code of the brainfuck editor.
	 * @return Code
	 */
	public String getCode() {
		if (brainfuckEditor != null) {
			this.code = brainfuckEditor.getCode();
		}

		return this.code;
	}

	@Override
	public MachineType getType() {
		return Machine.MachineType.BrainfuckMachine;
	}
	
	public String getFileExtension() {
		return BrainfuckMachine.FILE_EXTENSION;
	}
}