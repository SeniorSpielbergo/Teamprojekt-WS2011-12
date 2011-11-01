import javax.swing.*;
import java.awt.event.*;

public class Editor extends JFrame {

	public Editor() {
		setTitle("Editor");
		setSize(800, 800);
        
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
        
		// add menu subitems
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(saveAction);
		fileMenu.add(exitAction);
		
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
