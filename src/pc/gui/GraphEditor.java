//TODO: check, if this file is still necessary


package gui;

import javax.swing.*;
import java.awt.*;

public class GraphEditor extends JPanel {
	private static final long serialVersionUID = -1681241095366484171L;
	//private GraphToolbox toolbox;
	private GraphEditorPanel editorpanel;
	
	public GraphEditor() {
		setLayout(new BorderLayout());
		
		//toolbox = new GraphToolbox();
		editorpanel = new GraphEditorPanel();
		//add(toolbox, BorderLayout.WEST);
		add(editorpanel, BorderLayout.CENTER);
	}
}