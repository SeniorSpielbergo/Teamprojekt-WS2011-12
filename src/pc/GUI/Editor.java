package GUI;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import TuringMachine.*;
import Tape.*;

import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/** This class represents an editor for Turing machines
 * 
 * @author David Wille
 * 
 */
public class Editor extends JFrame {
	static final long serialVersionUID = -3667258249137827980L;
	static final String appName = "Turing Simulator";
	protected TuringMachine currentMachine;
	private JMenuItem newAction, openAction, saveAction, exportLatexAction, exitAction, runAction, testAction;
	private final JFileChooser fc = new JFileChooser();
	
	/**
	 * Constructs the Editor window with all actionListeners and a basic setup
	 */
	public Editor() {
		setTitle("Editor");
		setSize(800, 800);
		// try to set look for Linux
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
		
		// set current directory
		try {
			File currentDirectory = new File(new File(".").getCanonicalPath());
    		fc.setCurrentDirectory(currentDirectory);
		}
		catch (IOException e) {
		}
		
		// set xml filter
		fc.setFileFilter (new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith( ".xml" );
			}
			public String getDescription() {
				return "*.xml";
			}
		});
        
		// create menu bar
		JMenuBar menuBar = new JMenuBar();
		
		// set menu bar
		setJMenuBar(menuBar);
        
		// create menu items
		JMenu fileMenu = new JMenu("File");
		JMenu simulationMenu = new JMenu("Simulation");
		menuBar.add(fileMenu);
		menuBar.add(simulationMenu);
        
		// create menu subitems
		newAction = new JMenuItem("New");
		openAction = new JMenuItem("Open");
		saveAction = new JMenuItem("Save");
		exportLatexAction = new JMenuItem("Export as LaTeX");
		exitAction = new JMenuItem("Exit");
		runAction = new JMenuItem("Run");
		testAction = new JMenuItem("Test");
		
		// disable actions
		saveAction.setEnabled(false);
		exportLatexAction.setEnabled(false);
		runAction.setEnabled(false);
        
		// add menu subitems
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exportLatexAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitAction);
		simulationMenu.add(runAction);
		simulationMenu.add(testAction);
		
		// menu shortcuts
		newAction.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		openAction.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		saveAction.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		exitAction.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		runAction.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newFile();
			}
		});
		
		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFile();
			}
		});
		
		saveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveFile();
			}
		});
		
		exportLatexAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportLatex();
			}
		});
		
		runAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runSimulation();
			}
		});
		
		testAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				simulate();
			}
		});
		
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitEditor();
			}
		});
		
	}
	
	/**
	 * The editor main, which initializes a new editor window
	 * @param args
	 */
	public static void main(String[] args) {
		Editor mainWindow = new Editor();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		//mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void newFile() {
		JOptionPane.showMessageDialog(null, "Not implemented yet!");
		saveAction.setEnabled(true);
		exportLatexAction.setEnabled(true);
		runAction.setEnabled(true);
	}
	
	public void openFile() {
		int retVal = fc.showOpenDialog(null);
		if (retVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
			currentMachine = InOut.readXMLFromFile(selectedFile.getName());
			saveAction.setEnabled(true);
			exportLatexAction.setEnabled(true);
			runAction.setEnabled(true);
		}
	}
	
	public void saveFile() {
		fc.showSaveDialog(null);
		JOptionPane.showMessageDialog(null, "Not implemented yet!");
		//InOut.writeXMLFromFile(selectedFile.getName(), currentMachine);
	}
	
	public void exportLatex() {
		JOptionPane.showMessageDialog(null, "Not implemented yet!");
	}
	
	public void exitEditor() {
		System.exit(0);
	}
	
	public void runSimulation() {
		RunWindow runWindow = new RunWindow(currentMachine);
		runWindow.setVisible(true);
		runWindow.setLocationRelativeTo(null);
	}
	
	public void simulate() {
		System.out.println(this.currentMachine);
		ArrayList<Tape> tapes = new ArrayList<Tape>();
		
		//create tapes and write input
		try {
			for (int i=0; i < this.currentMachine.getTapes(); i++) {
//				Tape tape_console = new ConsoleTape();
//				tape_console.init();
//				tapes.add(tape_console);
//				this.writeInputWordToTape(tape_console, this.currentMachine.getInitial().get(i));

				MasterRobot ips_03 = new MasterRobot("IPS_03", "00:16:53:13:53:BB");
				SlaveRobot nxt_03 = new SlaveRobot("NXT_03", "00:16:53:0F:DB:8E");
				Tape tape_lego = new LEGOTape(ips_03, nxt_03);
				tape_lego.init();
				tapes.add(tape_lego);
				this.writeInputWordToTape(tape_lego, this.currentMachine.getInitial().get(i));
			}
		}
		catch (TapeException e){
		    ErrorDialog.showError("The initialization of the tapes failed because of a Tape exception (" + e.getMessage() + ").", e);
		    return;
		}
		catch (RuntimeException e){
		    ErrorDialog.showError("The initialization of the tapes failed because of an undefined exception.", e);
		    return;
		}
		
		//simulate
		try {
			Simulation sim = new Simulation(this.currentMachine, tapes);
			sim.runMachine();
		}
		catch (TapeException e){
		    ErrorDialog.showError("The simulation failed because of a Tape exception (" + e.getMessage() + ").", e);
		}
		catch (RuntimeException e){
		    ErrorDialog.showError("The simulation failed because of an undefined exception.", e);
		}

	}
	
	public void writeInputWordToTape(Tape t, ArrayList<Character> input) throws TapeException{
		System.out.println("Input length: " + input.size());
		for (Character c : input) {
			t.write(c);
			t.moveRight();
		}
		for (int i = 0; i < input.size(); i++) {
			t.moveLeft();
		}
		
	}
}
