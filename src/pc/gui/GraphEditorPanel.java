package gui;

import javax.swing.*;
import java.awt.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class GraphEditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3187956128743858754L;

	public GraphEditorPanel() {
		setBackground(Color.WHITE);
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
					30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
					80, 30);
			graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		add(graphComponent);
	}
}
