import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.io.*;

public class Editor extends JFrame {

	protected TuringMachine currentMachine;
	private JMenuItem newAction, openAction, saveAction, exportLatexPDFAction, exitAction;

	public Editor() {
		setTitle("Editor");
		setSize(800, 800);
		// try to set look for Linux
		try {
			if (System.getProperties().getProperty("os.name") == "Linux") {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}
    	} 
		catch (Exception e) {
		}
		
		// create fileChooser
		final JFileChooser fc = new JFileChooser();
		int returnVal;
		
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
		exportLatexPDFAction = new JMenuItem("Export as LaTeX and pdf");
		exitAction = new JMenuItem("Exit");
		
		// disable actions
		saveAction.setEnabled(false);
		exportLatexPDFAction.setEnabled(false);
        
		// add menu subitems
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exportLatexPDFAction);
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
				exportLatexPDFAction.setEnabled(true);
			}
		});
		
		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int retVal = fc.showOpenDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION) {
 					File selectedFile = fc.getSelectedFile();
					currentMachine = InOut.readXMLFromFile(selectedFile.getName());
					saveAction.setEnabled(true);
					exportLatexPDFAction.setEnabled(true);
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
		
		exportLatexPDFAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not implemented yet!");
				//try {
				//	Runtime.getRuntime().exec("pdflatex -output-directory=" + path);
				//}
				//catch (Exception e) {
				//}
			}
		});
		
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		Editor mainWindow = new Editor();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
