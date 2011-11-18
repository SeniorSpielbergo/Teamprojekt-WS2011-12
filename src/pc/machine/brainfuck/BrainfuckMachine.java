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

import javax.swing.JLabel;

import tape.TapeException;
import tape.ConsoleTape;

import machine.Machine;
import machine.Simulation;

public class BrainfuckMachine extends Machine {
	private BrainfuckEditor brainfuckEditor;

	public BrainfuckMachine() {
		super();
		tapes.add(new ConsoleTape("Action"));
		tapes.add(new ConsoleTape("Input"));
	}
	
	public BrainfuckMachine(String name) {
		super(name);
		tapes.add(new ConsoleTape());
		tapes.add(new ConsoleTape());
	}
	
	public void save(String filename) throws IOException {
		FileWriter fstream = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(brainfuckEditor.getCode());
		out.close();
	}
	
	public void load(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		byte[] buffer = new byte[(int) file.length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
		f.read(buffer);
		String code = new String(buffer);
		brainfuckEditor.setCode(code);
	}

	public Simulation createSimulation() throws TapeException {
		BrainfuckSimulation brainfuckSimulation = new BrainfuckSimulation(this);
		return brainfuckSimulation;
	}

	protected MachineEditor createEditor() {
		brainfuckEditor = new BrainfuckEditor();
		return brainfuckEditor;
	}
	
	public String getCode() {
		return brainfuckEditor.getCode();
	}
	
	public JLabel getOutputLabel() {
		return brainfuckEditor.getOutputLabel();
	}

	@Override
	public MachineType getType() {
		return Machine.MachineType.BrainfuckMachine;
	}
}