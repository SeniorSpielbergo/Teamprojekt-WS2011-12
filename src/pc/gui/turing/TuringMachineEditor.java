package gui.turing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.mxgraph.swing.mxGraphComponent;

import com.mxgraph.view.mxGraphSelectionModel;
import com.mxgraph.view.mxGraph;

import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxEventObject;


import gui.MachineEditor;

public class TuringMachineEditor extends MachineEditor {
	protected JPanel jPanelLeft = null;
	protected JPanel jPanelGraph = null;
	protected mxGraph graph = null;
	protected JSplitPane jSplitPaneHorizontal = null;
	protected JPanel jPanelToolBox = null;
	protected JPanel jPanelProperties = null;
	
	public TuringMachineEditor() {
		super();

		//create left panel
		this.jPanelLeft = new JPanel();

		//create main graph panel
		this.jPanelGraph = new JPanel();
		this.jPanelGraph.setLayout(new BorderLayout());
		
		//create split pane
		this.jSplitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				this.jPanelLeft, this.jPanelGraph);
		this.jSplitPaneHorizontal.setOneTouchExpandable(true);
		this.jSplitPaneHorizontal.setDividerLocation(150);
		Dimension minimumSize = new Dimension(100, 50);
		this.jPanelLeft.setMinimumSize(minimumSize);
		this.jPanelGraph.setMinimumSize(minimumSize);
		this.setLayout(new BorderLayout());
		this.add(this.jSplitPaneHorizontal, BorderLayout.CENTER);

		//create the graph
		mxGraph graph = new mxGraph();

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
					30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
					80, 30,"ROUNDED");
			graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		this.jPanelGraph.add(graphComponent, BorderLayout.CENTER);
		
		

	}
	
	private void displayProperties(PropertiesEdge prop){
		jPanelProperties.removeAll();
		jPanelProperties.add(prop);
	}
	
	private void displayProperties(PropertiesState prop){
		jPanelProperties.removeAll();
		jPanelProperties.add(prop);
	}



}
