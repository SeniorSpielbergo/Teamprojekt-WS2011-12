package gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import machine.Machine;
import machine.Simulation;
import machine.brainfuck.BrainfuckMachine;
import machine.turing.*;
import tape.*;
import gui.RunWindow.*;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;

/** This class represents an editor for Turing machines
 * 
 * @author Vanessa Baier, Nils Breyer, Sven Schuster, Philipp Neumann, David Wille
 * 
 */
@SuppressWarnings("serial")
public class Editor extends JFrame implements ActionListener {
	/**
	 * The name of the application
	 */
	public static final String appName = "TuringBrain IDE";
	/**
	 * The machine currently open in the editor
	 */
	protected Machine currentMachine;
	private String currentFilename = "";
	private JMenu newSubmenu;
	private JMenuItem newBFAction;
	private JMenuItem newTMAction;
	private JMenuItem openAction;
	private JMenuItem saveAction;
	private JMenuItem saveAsAction;
	private JMenuItem exportLatexAction;
	private JMenuItem exitAction;
	private JMenuItem runAction;
	private JMenuItem organizeRobotsAction;
	private JMenuItem aboutAction;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu simulationMenu;
	private JMenu helpMenu;

	/**
	 * Constructs the Editor window with all actionListeners and a basic setup
	 */
	public Editor() {
		setTitle(appName);
		setSize(800, 800);
		setMinimumSize(new Dimension(600, 400));

		initEditor();

		menuBar.add(fileMenu);
		menuBar.add(simulationMenu);
		menuBar.add(helpMenu);

		// disable actions
		saveAction.setEnabled(false);
		saveAsAction.setEnabled(false);
		exportLatexAction.setEnabled(false);
		runAction.setEnabled(false);

		// add menu subitems
		fileMenu.add(newSubmenu);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exportLatexAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitAction);
		simulationMenu.add(runAction);
		simulationMenu.add(organizeRobotsAction);
		helpMenu.add(aboutAction);

		// menu shortcuts
		newTMAction.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		newBFAction.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | Event.SHIFT_MASK ));
		openAction.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		saveAction.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		exitAction.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		runAction.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}

	/**
	 * The editor main, which initializes a new editor window
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// try to set look for Linux and Mac OS X
		try {
			if (System.getProperties().getProperty("os.name").equals("Linux")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}
			else if (System.getProperties().getProperty("os.name").equals("Mac OS X")) {
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", Editor.appName);
				System.setProperty("apple.laf.useScreenMenuBar", "true");
			}
		} 
		catch (Exception e) {
		}

		Editor mainWindow = new Editor();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);

		//mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Initializes the editor
	 */
	public void initEditor() {
		// create menu bar
		menuBar = new JMenuBar();

		// set menu bar
		setJMenuBar(menuBar);

		// create submenu items
		newSubmenu = new JMenu("New");
		newTMAction = new JMenuItem("Turing machine...");
		newBFAction = new JMenuItem("Brainfuck program");
		newSubmenu.add(newTMAction);
		newSubmenu.add(newBFAction);

		// create menu subitems
		openAction = new JMenuItem("Open...");
		saveAction = new JMenuItem("Save");
		saveAsAction = new JMenuItem("Save As...");
		exportLatexAction = new JMenuItem("Export as LaTeX");
		exitAction = new JMenuItem("Exit");
		runAction = new JMenuItem("Run...");

		organizeRobotsAction = new JMenuItem("Organize robots...");
		aboutAction = new JMenuItem("About...");

		// create menu items
		fileMenu = new JMenu("File");
		simulationMenu = new JMenu("Simulation");
		helpMenu = new JMenu("Help");

		// init actionListener
		newTMAction.addActionListener(this);
		newBFAction.addActionListener(this);
		openAction.addActionListener(this);
		saveAction.addActionListener(this);
		saveAsAction.addActionListener(this);
		exportLatexAction.addActionListener(this);
		exitAction.addActionListener(this);
		runAction.addActionListener(this);
		organizeRobotsAction.addActionListener(this);
		aboutAction.addActionListener(this);
	}

	/**
	 * Creates a new file
	 * @param type The machine type to be created
	 */
	public void newFile(Machine.MachineType type) {
		this.closeCurrentFile();
		switch(type) {
		case TuringMachine:
			gui.turing.NewTMDialogue newTMDialogue = new gui.turing.NewTMDialogue();
			gui.turing.NewTMDialogue.ReturnValue returnValue = newTMDialogue.showDialogue();
			switch(returnValue){
			case CREATE:
				this.currentMachine = new TuringMachine(newTMDialogue.getMachinesName(),newTMDialogue.getNumberOfTapes());
				this.loadEditor();
				break;

			case CANCEL:
				break;
			}
			break;
		case BrainfuckMachine:
			this.currentMachine = new BrainfuckMachine();
			this.loadEditor();
			break;
		}
	}

	/**
	 * Opens a file
	 */
	public void openFile() {
		this.closeCurrentFile();

		final JFileChooser fc = new JFileChooser();
		// set current directory for file chooser
		try {
			File currentDirectory = new File(new File(".").getCanonicalPath());
			fc.setCurrentDirectory(currentDirectory);
		}
		catch (IOException e) {
		}

		// set xml filter for file chooser
		fc.setFileFilter (new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(BrainfuckMachine.FILE_EXTENSION);
			}
			public String getDescription() {
				return "Brainfuck programs (" + BrainfuckMachine.FILE_EXTENSION + ")";
			}
		});
		fc.setFileFilter (new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(TuringMachine.FILE_EXTENSION);
			}
			public String getDescription() {
				return "Turing machines (" + TuringMachine.FILE_EXTENSION + ")";
			}
		});
		fc.setFileFilter (new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(TuringMachine.FILE_EXTENSION) || f.getName().toLowerCase().endsWith(BrainfuckMachine.FILE_EXTENSION);
			}
			public String getDescription() {
				return "All supported files (" + TuringMachine.FILE_EXTENSION + ", " + BrainfuckMachine.FILE_EXTENSION + ")";
			}
		});

		int retVal = fc.showOpenDialog(null);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			Machine machine = null;
			if(selectedFile.getName().toLowerCase().endsWith(TuringMachine.FILE_EXTENSION)) {
				machine = new TuringMachine();
			}
			else if(selectedFile.getName().toLowerCase().endsWith(BrainfuckMachine.FILE_EXTENSION)) {
				machine = new BrainfuckMachine();
			}
			else {
				ErrorDialog.showError("The file '" + selectedFile.getName() + "' couldn't be openend, because the filetype is not supported.");
				return;
			}
			try {
				machine.load(selectedFile.getAbsolutePath());
				this.currentMachine = machine;
				this.currentFilename = selectedFile.getAbsolutePath();
				this.loadEditor();
			}
			catch(Exception e) {
				ErrorDialog.showError("The file '" + selectedFile.getName() + "' couldn't be openend, because the file is corrupt.", e);
			}
		}
	}
	
	/**
	 * Saves a file
	 */
	public void saveFile() {
		if (!this.currentFilename.equals("")) {
			try {
				this.currentMachine.save(this.currentFilename);
			} catch (IOException e) {
				ErrorDialog.showError("Saving the file '" + this.currentFilename + "' failed because of an I/O error.", e);
			}
			catch (RuntimeException e){
				ErrorDialog.showError("Saving the file '" + this.currentFilename + "' failed because of an unkown error.", e);
			}
		}
		else {
			this.saveAsFile();
		}
	}

	/**
	 * Saves a file under a certain name
	 */
	public void saveAsFile() {
		final JFileChooser fc = new JFileChooser();
		// set current directory for file chooser
		try {
			File currentDirectory = new File(new File(".").getCanonicalPath());
			fc.setCurrentDirectory(currentDirectory);
		}
		catch (IOException e) {
		}

		// set xml filter for file chooser
		fc.setFileFilter (new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(currentMachine.getFileExtension());
			}
			public String getDescription() {
				return currentMachine.getFileExtension();
			}
		});
		
		File f = null;
		if (this.currentFilename.equals("")) {
			f = new File(this.currentMachine.getName() + this.currentMachine.getFileExtension());
		}
		else {
			f = new File(this.currentFilename);
		}
		fc.setSelectedFile(f);
		int retVal = fc.showSaveDialog(null);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			if (selectedFile.exists()){
				int result = JOptionPane.showConfirmDialog(null, "Do you want to override the file?", "Override", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.NO_OPTION) {
					saveAsFile();
					return;
				} 
				
			}
			try { //TODO: check if the file already exists and prompt if to save anyway
				this.currentMachine.save(selectedFile.getPath());
			} catch (IOException e) {
				ErrorDialog.showError("Saving the file '" + selectedFile.getName() + "' failed because of an I/O error.", e);
			}
			catch (RuntimeException e){
				ErrorDialog.showError("Saving the file '" + selectedFile.getName() + "' failed because of an unkown error.", e);
			}
		}
	}

	/**
	 * Exports the Turing machine to LaTeX
	 */
	public void exportLatex() {
		JOptionPane.showMessageDialog(null, "Not implemented yet!");
	}

	/**
	 * Closes the editor
	 */
	public void exitEditor() {
		this.closeCurrentFile();
		System.exit(0);
	}

	/**
	 * Opens the run window
	 */
	public void runSimulation() {
		RunWindow runWindow = new RunWindow(currentMachine);
		runWindow.setLocationRelativeTo(null);

		ReturnValue returnValue = runWindow.showDialog();
		if (returnValue == ReturnValue.RUN) {
			simulate();
		}
	}

	/**
	 * Shows the window to add and remove robots
	 */
	public void organizeRobots() {
		OrganizeRobots organizeRobotsWindow = new OrganizeRobots();
		organizeRobotsWindow.setLocationRelativeTo(null);
		organizeRobotsWindow.showDialog();
	}

	/**
	 * Simulates the Turing machine
	 */
	public void simulate() {
		SimulationWindow simulationWindow = new SimulationWindow(this.currentMachine);
		//create tapes and write input
			// --> moved to simulation window

		//simulate


	}

	/**
	 * Responds to a clicked button
	 * @param e ActionEvent that indicates changes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newTMAction) {
			newFile(Machine.MachineType.TuringMachine);
		}
		else if (e.getSource() == newBFAction) {
			newFile(Machine.MachineType.BrainfuckMachine);
		}
		else if (e.getSource() == openAction) {
			openFile();
		}
		else if (e.getSource() == saveAction) {
			saveFile();
		}
		else if (e.getSource() == saveAsAction) {
			saveAsFile();
		}
		else if (e.getSource() == exportLatexAction) {
			exportLatex();
		}
		else if (e.getSource() == runAction) {
			runSimulation();
		}
		else if (e.getSource() == organizeRobotsAction) {
			organizeRobots();
		}
		else if (e.getSource() == aboutAction) {
			JOptionPane.showMessageDialog(null, "Not implemented yet!");
		}
		else if (e.getSource() == openAction) {
			openFile();
		}
		else if (e.getSource() == exitAction) {
			exitEditor();
		}
	}

	/**
	 * Loads and prepares the editor for the current machine
	 */
	public void loadEditor() {
		this.add(this.currentMachine.getEditor());

		int menupos = 1;
		for (JMenu menu : this.currentMachine.getEditor().getMenus()) {
			this.menuBar.add(menu, menupos);
			menupos++;
		}

		saveAction.setEnabled(true);
		saveAsAction.setEnabled(true);
		runAction.setEnabled(true);
		exportLatexAction.setEnabled(false);

		validate();
	}

	/**
	 * Closes the current machine. Must be run before a new machine can be loaded.
	 */
	public void closeCurrentFile() {
		if (this.currentMachine != null) {
			int result = JOptionPane.showConfirmDialog(null, "Do you want to save the current machine?", "Close file", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				this.saveFile();
			}

			this.currentFilename = "";

			saveAction.setEnabled(false);
			saveAsAction.setEnabled(false);
			runAction.setEnabled(false);
			exportLatexAction.setEnabled(false);

			for (JMenu menu : this.currentMachine.getEditor().getMenus()) {
				this.menuBar.remove(menu);
			}
			this.remove(this.currentMachine.getEditor());
			this.currentMachine = null;
		}
	}
}
