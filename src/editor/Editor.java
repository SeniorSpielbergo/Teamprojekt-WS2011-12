import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Editor extends JFrame {

	public Editor() {
		setTitle("Editor");
		setSize(800, 800);
		
		// create fileChooser
		final JFileChooser fc = new JFileChooser();
		int returnVal;
        
		// create menu bar
		JMenuBar menuBar = new JMenuBar();
		
		// set menu bar
		setJMenuBar(menuBar);
        
		// create menu items
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
        
		// create menu subitems
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem saveAction = new JMenuItem("Save");
		JMenuItem exitAction = new JMenuItem("Exit");
		
		// disable saveAction
		saveAction.setEnabled(false);
        
		// add menu subitems
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitAction);
		
		// menu shortcuts
		newAction.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
		openAction.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
		saveAction.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		exitAction.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		
		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int retVal = fc.showOpenDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION) {
 					File selectedFile = fc.getSelectedFile();
					JOptionPane.showMessageDialog(null, selectedFile.getName());
				}
			}
		});
		
		saveAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.showSaveDialog(null);
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
	}

}
