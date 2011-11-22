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

public class BrainfuckMachine extends Machine {
	private BrainfuckEditor brainfuckEditor;
	private String code = "Type your brainfuck code here";

	public BrainfuckMachine() {
		super();
		tapes.add(new ConsoleTape("Input"));
		tapes.add(new ConsoleTape("Output"));
		tapes.add(new ConsoleTape("Action"));		
	}

	public BrainfuckMachine(String name) {
		super(name);
		tapes.add(new ConsoleTape("Input"));
		tapes.add(new ConsoleTape("Output"));
		tapes.add(new ConsoleTape("Action"));
	}

	public void save(String filename) throws IOException {
		if (brainfuckEditor != null) {
			this.code = brainfuckEditor.getCode();
		}
		FileWriter fstream = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(code);
		out.close();
	}

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

	public Simulation createSimulation() throws TapeException {
		BrainfuckSimulation brainfuckSimulation = new BrainfuckSimulation(this);
		return brainfuckSimulation;
	}

	protected MachineEditor createEditor() {
		brainfuckEditor = new BrainfuckEditor();
		brainfuckEditor.setCode(code);
		return brainfuckEditor;
	}

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
}