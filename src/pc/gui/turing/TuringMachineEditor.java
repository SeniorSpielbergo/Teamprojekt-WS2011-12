package gui.turing;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Dimension;
import machine.turing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import machine.turing.Edge;
import machine.turing.State;
import machine.turing.TuringMachine;

import com.mxgraph.swing.mxGraphComponent;

import com.mxgraph.view.mxGraphSelectionModel;
import com.mxgraph.view.mxGraph;
import com.mxgraph.model.*;

import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxEventObject;


import gui.MachineEditor;

public class TuringMachineEditor extends MachineEditor {
	private TuringMachine machine = null;
	private Object selectedObject = null;
	private ArrayList<Object> graphicalStates = null;
	private ArrayList<Object> graphicalEdges = null;
	
	protected JPanel jPanelLeft = null;
	protected JPanel jPanelGraph = null;
	protected mxGraph graph = null;
	protected JSplitPane jSplitPaneHorizontal = null;
	protected JPanel jPanelToolBox = null;
	protected JPanel jPanelProperties = null;
	
	public TuringMachineEditor(TuringMachine machine) {
		super();
		this.machine = machine;
		this.graphicalStates = new ArrayList<Object>(machine.getStates().size());
		this.graphicalEdges = new ArrayList<Object>(machine.getEdges().size());
		
		//create left panel
		this.jPanelLeft = new JPanel();
		this.jPanelLeft.setLayout(new BorderLayout());
		this.jPanelToolBox = new JPanel();
		this.jPanelProperties = new JPanel();
		this.jPanelLeft.add(this.jPanelToolBox, BorderLayout.PAGE_START);
		this.jPanelLeft.add(this.jPanelProperties, BorderLayout.CENTER);

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
		Object v1;
		Object v2;
		graph.getModel().beginUpdate();
		try
		{
			State s1 = new State("1", "abc", false, false);
			State s2 = new State("2", "def", false, false);
			v1 = graph.insertVertex(parent, null, s1, 20, 20, 80,
					30);
			v2 = graph.insertVertex(parent, null, s2, 240, 150,
					80, 30);
			graph.insertEdge(parent, null, new Edge(s1,s2,null), v1, v2);

		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		this.jPanelGraph.add(graphComponent, BorderLayout.CENTER);
		
		mxCell c = (mxCell) v1;
		State s = (State) c.getValue();
		System.out.println(s.getName());

		
		if (this.machine != null && this.machine.getEdges().size() > 0) {
			this.displayProperties(this.machine.getEdges().get(0));
		}
	}
	
	private void displayProperties(Edge edge) {
		PropertiesEdge propertiesEdge = new PropertiesEdge(this.machine.getNumberOfTapes(), edge);
		jPanelProperties.removeAll();
		jPanelProperties.add(propertiesEdge);
	}
	
	private void displayProperties(State state) {
		jPanelProperties.removeAll();
		//jPanelProperties.add(prop); //TODO: implement
	}

	private void drawGraph(){
		ArrayList<State> states = this.machine.getStates();
		ArrayList<Edge> edges = this.machine.getEdges();
		
		for (int i = 0;  i < states.size(); i++){
			graphicalStates[i] = graph.insertVertex(graph.getDefaultParent(), null, states.get(i), states.get(i).getXcoord(), states.get(i).getYcoord(), states.get(i).getWidth(), states.get(i).getHeight());
		}
	}

}
