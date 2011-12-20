package gui.turing;

import java.util.ArrayList;

import machine.turing.State;

import com.mxgraph.model.mxCell;

/**
 * This class extends ArrayList<mxCell> to find mxCells with a specified Edge Object
 * @author Philipp Neumann
 */
class EdgeList extends ArrayList<mxCell>{
	private static final long serialVersionUID = -6540044275767431408L;
	/**
	 * Constructs a new EdgeList
	 */
	public EdgeList() {
		super();
	}
	
	/**
	 * Constructs a new EdgeList with a given size
	 * @param size The size
	 */
	public EdgeList(int size) {
		super(size);
	}

	/**
	 * Method to find mxCell with specified value of type Edge
	 * @param source Start state
	 * @param target End state
	 * @return null
	 */
	mxCell getMxCell(State source, State target) {
		for (int i = 0; i < this.size(); i++) {
			if((this.get(i).getSource().getValue().equals((Object) source)) && (this.get(i).getTarget().getValue().equals((Object) target))){
				return this.get(i);
			}
		}
		return null;
	}

}