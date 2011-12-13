package machine.turing;

import com.mxgraph.view.mxGraphSelectionModel;

public class TuringMachineState {
	protected String name;
	protected TuringMachine machine;
	protected mxGraphSelectionModel selection; 
	
	public TuringMachineState(String name, TuringMachine machine) {
		this.name = name;
		this.machine = machine;
//		this.selection = selection;
	}
	
	public TuringMachine getMachine() {
		return this.machine;
	}
	
	public mxGraphSelectionModel getSelection() {
		return this.selection;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name + "\n";
	}
}