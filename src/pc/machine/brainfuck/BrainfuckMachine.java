package machine.brainfuck;

import gui.MachineEditor;
import gui.brainfuck.BrainfuckEditor;

import java.io.*;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.filechooser.FileFilter;

import tape.*;
import machine.*;

/**
 * Represents a brainfuck machine and provides methods to handle it.
 * @author Sven Schuster
 * 
 */
public class BrainfuckMachine extends Machine {
	public static final String FILE_EXTENSION = ".bf";

	private String code;

	/**
	 * Creates a new brainfuck machine with input-, output- and actiontape.
	 */
	public BrainfuckMachine() {
		super("Brainfuck machine");
		tapes.add(new GraphicTape("Input", true));
		tapes.add(new GraphicTape("Output", false));
		tapes.add(new GraphicTape("Action", false));
		this.code = "Type your brainfuck code here";
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Saves the brainfuck code to given filename.
	 * @param filename Filename
	 */
	@Override
	public void save(String filename) throws IOException {
		if (this.editor != null) {
			this.code = ((BrainfuckEditor) this.editor).getCode();
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(code);
			out.close();
		}
	}

	/**
	 * Loads brainfuck code out of given filename.
	 * @param fileName Filename to load code from.
	 * @throws FileNotFoundException If the file does not exist.
	 * @throws IOException If file could not be read.
	 */
	@Override
	public void load(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		byte[] buffer = new byte[(int) file.length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
		f.read(buffer);
		this.code = new String(buffer);
		if (this.editor != null)
			((BrainfuckEditor) this.editor).setCode(code);
	}

	/**
	 * Creates a new brainfuck simulation.
	 * @return BrainfuckSimulation
	 * @throws TapeException If the tape failed to initialize.
	 */
	@Override
	public Simulation createSimulation() throws TapeException {
		BrainfuckSimulation brainfuckSimulation = new BrainfuckSimulation(this);
		if(this.editor != null)
			brainfuckSimulation.addObserver((Observer) this.editor);
		return brainfuckSimulation;
	}

	/**
	 * Creates a new brainfuck editor.
	 * @return BrainfuckEditor
	 */
	@Override
	protected MachineEditor createEditor() {
		this.editor = new BrainfuckEditor();
		((BrainfuckEditor) this.editor).setCode(code);
		return this.editor;
	}
	
	@Override
	public MachineType getType() {
		return Machine.MachineType.BrainfuckMachine;
	}
	
	@Override
	public String getFileExtension() {
		return BrainfuckMachine.FILE_EXTENSION;
	}
	
	@Override
	public boolean isSimulatable() {
		this.code = ((BrainfuckEditor) this.editor).getCode();
		if(code.equals(""))
			return false;
		
		int i = 0,
				x = 0;
		while(i < code.length()) {
			if(code.charAt(i) == '[')
				x++;
			else if(code.charAt(i) == ']')
				x--;
			if(x == -1)
				return false;
			i++;
		}
		return x == 0;
	}
	
	@Override
	public Object clone() {
		BrainfuckMachine bf = new BrainfuckMachine();
		bf.getTapes().set(0,this.getTapes().get(0));
		bf.getTapes().set(1,this.getTapes().get(1));
		bf.getTapes().set(2,this.getTapes().get(2));
		bf.setCode(new String(((BrainfuckEditor) this.editor).getCode()));
		return bf;
	}
	
	@Override
	public ArrayList<FileFilter> getSupportedExportFormats() {
		ArrayList<FileFilter> filters = new ArrayList<FileFilter>();
		
		//add export filters here
		
		return filters;
	}

	@Override
	public void export(String filename) throws IOException {
		throw new IOException("Cannot export to '" + filename + "' because the filetype is not supported.");
	}
}