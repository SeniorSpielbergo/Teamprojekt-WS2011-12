package gui.turing;

import java.util.ArrayList;

import machine.turing.State;

import com.mxgraph.model.mxCell;

class EdgeList extends ArrayList<mxCell>{
	private static final long serialVersionUID = -6540044275767431408L;
	public EdgeList() {
		super();
	}
	public EdgeList(int size) {
		super(size);
	}

	mxCell getMxCell(State source, State target) {
		for (int i = 0; i < this.size(); i++) {
			if((this.get(i).getSource().getValue().equals((Object) source)) && (this.get(i).getTarget().getValue().equals((Object) target))){
				return this.get(i);
			}
		}
		return null;
	}

}