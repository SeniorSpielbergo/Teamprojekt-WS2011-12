import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.*;

/** This class represents an editor for Turing machines
 * 
 * @author David Wille
 * 
 */
public class Editor extends JFrame {

	static final long serialVersionUID = -3667258249137827980L;
	protected TuringMachine currentMachine;
	private JMenuItem newAction, openAction, saveAction, exportLatexAction, exitAction;
	
	/**
	 * Constructs the Editor window with all actionListeners and a basic setup
	 */
	public Editor() {
		setTitle("Editor");
		setSize(800, 800);
		// try to set look for Linux
		try {
			if (System.getProperties().getProperty("os.name") == "Linux") {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}
			else if (System.getProperties().getProperty("os.name") == "Mac OS X") {
				//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				//System.setProperty("apple.laf.useScreenMenuBar", "true");
			}
    	} 
		catch (Exception e) {
		}
		
		// create fileChooser
		final JFileChooser fc = new JFileChooser();
		
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
		menuBar.add(fileMenu);
        
		// create menu subitems
		newAction = new JMenuItem("New");
		openAction = new JMenuItem("Open");
		saveAction = new JMenuItem("Save");
		exportLatexAction = new JMenuItem("Export as LaTeX");
		exitAction = new JMenuItem("Exit");
		
		// disable actions
		saveAction.setEnabled(false);
		exportLatexAction.setEnabled(false);
        
		// add menu subitems
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exportLatexAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitAction);
		
		// menu shortcuts
		newAction.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
		openAction.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
		saveAction.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		exitAction.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not implemented yet!");
				saveAction.setEnabled(true);
				exportLatexAction.setEnabled(true);
			}
		});
		
		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int retVal = fc.showOpenDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION) {
 					File selectedFile = fc.getSelectedFile();
					currentMachine = InOut.readXMLFromFile(selectedFile.getName());
					saveAction.setEnabled(true);
					exportLatexAction.setEnabled(true);
				}
			}
		});
		
		saveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.showSaveDialog(null);
				JOptionPane.showMessageDialog(null, "Not implemented yet!");
				//InOut.writeXMLFromFile(selectedFile.getName(), currentMachine);
			}
		});
		
		exportLatexAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not implemented yet!");
			}
		});
		
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
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
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
