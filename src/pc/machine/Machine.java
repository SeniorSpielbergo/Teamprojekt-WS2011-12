package machine;

import gui.MachineEditor;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

import tape.LEGOTape;
import tape.Tape;
import tape.TapeException;

/**
 * An abstract class that represents a machine that operates on tapes.
 * @author Nils Breyer
 */
public abstract class Machine {
	/**
	 * An enum containing different machine types.
	 */
	public enum MachineType {
		/**
		 * A Turing machine.
		 */
		TuringMachine, 
		/**
		 * A Brainfuck machine.
		 */
		BrainfuckMachine
	}
	/**
	 * The machine name string.
	 */
	protected String name = "";
	/**
	 * The machine author string.
	 */
	protected String author = "";
	/**
	 * The machine description string.
	 */
	protected String description = "";
	/**
	 * The tapes array of the machine.
	 */
	protected ArrayList<Tape> tapes;
	/**
	 * The graphical editor for the machine.
	 */
	protected MachineEditor editor = null;
	/**
	 * Constructs an untitled Turing machine.
	 */
	public Machine() {
		this("Untitled machine");
	}

	/**
	 * Constructs an empty Turing machine with a name.
	 * @param name Name for the Turing machine
	 */
	public Machine(String name) {
		this.name = name;
		this.tapes = new ArrayList<Tape>();
	}

	/**
	 * Initializes the tapes and writes the input words to the tape.
	 * @throws TapeException Exception if any problems occur while initializing
	 */
	public void initTapes() throws TapeException {
		//init tapes
		for (Tape tape : this.tapes) {
			tape.init();
		}
	}

	/**
	 * Initializes the tapes and writes the input words to the tape.
	 * @throws TapeException Exception if any problems occur while initializing
	 */
	public void writeInputWords() throws TapeException {
		//init tapes
		for (Tape tape : this.tapes) {
			tape.startWritingInputWord();
		}
	}

	/**
	 * Shuts down the tapes.
	 * @throws TapeException Exception if any problems occur while shutting down
	 */
	public void shutdownTapes() throws TapeException {
		//init tapes
		for (Tape tape : this.tapes) {
			if(tape.isReady()){
				System.out.println(" i shut down the tapes...jetzt aber wirklich");
				tape.shutdown();
			}
		}
	}

	/**
	 * Returns the Turing machine's name.
	 * @return The Turing machine's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the Turing machine's author.
	 * @return author The Turing machine's author.
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * Sets the Turing machine's author.
	 * @param author The Turing machine's author.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Returns the Turing machine's description.
	 * @return description The Turing machine's description.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Sets the Turing machine's description.
	 * @param description The Turing machine's description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the Turing machine's tapes.
	 * @return tapes Number of tapes.
	 */
	public ArrayList<Tape> getTapes() {
		return this.tapes;
	}

	/**
	 * Returns the number of tapes of this machine.
	 * @return numberOfTapes Number of tapes.
	 */
	public int getNumberOfTapes() {
		return this.tapes.size();
	}

	/**
	 * Saves the machine code to a file on the disk.
	 * @param filename File where to save the machine's code.
	 * @throws IOException Thrown if an I/O error occurs while writing to disk.
	 */
	public abstract void save(String filename) throws IOException;

	/**
	 * Loads the machine code from a file.
	 * This operation replaces the current code in the machine object with the code from the file.
	 * @param filename A file that contains machine code for the fitting machine type.
	 * @throws IOException Thrown if an I/O error occurs while reading the file or the file format is corrupt.
	 */
	public abstract void load(String filename) throws IOException;

	/**
	 * Returns a new Simulation object to simulate this machine.
	 * @return simulation The simulation object.
	 * @throws TapeException Thrown if the simulation couldn't be created.
	 */
	public abstract Simulation createSimulation() throws TapeException;

	/**
	 * Returns a new graphical MachineEditor for this machine.
	 * Abstract method to be implemented by subclasses
	 * @return MachineEditor The new MachineEditor.
	 */
	protected abstract MachineEditor createEditor();

	/**
	 * Returns the graphical MachineEditor for this machine.
	 * @return MachineEditor The MachineEditor.
	 */
	public MachineEditor getEditor() {
		if (this.editor == null) {
			this.editor = this.createEditor();
		}
		return this.editor;
	}
	
	/**
	 * Returns the robot's names.
	 * @return robotNames ArrayList with names.
	 */
	public ArrayList<String> getRobotNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Tape t : this.getTapes()) {
			if (t instanceof LEGOTape) {
				names.add(((LEGOTape) t).getMaster().getName());
				names.add(((LEGOTape) t).getSlave().getName());
			}
		}
		return names;
	}
	
	/**
	 * Returns the supported export formats.
	 * Abstract method to be implemented by subclasses.
	 * @return supportedExportFormats The supported formats.
	 */
	public abstract ArrayList<FileFilter> getSupportedExportFormats();
	
	/**
	 * Exports the current file.
	 * Abstract method to be implemented by subclasses.
	 *
	 * @param filename The filename.
	 * @throws IOException Thrown if an I/O error occurs.
	 */
	public abstract void export(String filename) throws IOException;

	/**
	 * Returns type of the machine.
	 * Abstract method to be implemented by subclasses.
	 * @return type The type of this machine.
	 */
	public abstract MachineType getType();

	/**
	 * Returns the file extension.
	 * Abstract method to be implemented by subclasses.
	 * @return extension The file extension.
	 */
	public abstract String getFileExtension();
	
	/**
	 * Returns if the simulation is executable or not.
	 * Abstract method to be implemented by subclasses.
	 * @return isSimulatable True if it is simulatable.
	 */
	public abstract boolean isSimulatable();
	
	/**
	 *Returns a cloned object of the machine.
	 *Abstract method to be implemented by subclasses.
	 *@return machineObject   
	 */
	public abstract Object clone();
}
