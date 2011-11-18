package machine;

import tape.TapeException;

public abstract class Simulation {

	Machine machine;
	
	public Simulation(Machine machine){
		this.machine = machine;
	}
	
	public abstract void runMachine() throws TapeException;
	

}
