package gui.turing;

import java.util.ArrayList;

import machine.turing.State;

import com.mxgraph.model.mxCell;

	/**
	 * This class extends ArrayList<mxCell> to find mxCells with a specified State Object
	 * @author Philipp Neumann
	 */
	class StateList extends ArrayList<mxCell>{
		private static final long serialVersionUID = 4590100471318084729L;
		/**
		 * Constructs a new StateList
		 */
		public StateList(){
			super();
		}

		/**
		 * Constructs a new StateList with a given size
		 * @param size The size
		 */
		public StateList(int size){
			super(size);
		}
		/**
		 * Method to find mxCell with specified value of type State
		 * @param state
		 * @return mxCell
		 */
		mxCell getMxCell(State state){							
			for (int i = 0; i < this.size(); i++) {
				if(this.get(i).getValue().equals((Object) state)){
					return this.get(i);
				}
			}
			return null;
		}
	}