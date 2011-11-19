package machine;

import tape.TapeException;

/**
 * Abstract class that controls a simulation of a machine
 * @author Vanessa Baier
 *
 */
public abstract class Simulation {
	/**
	 * The machine to simulate
	 */
	Machine machine;
	
	/**
	 * Creates a new Simulation object from a Machine object
	 * @param machine The machine to simulate
	 */
	public Simulation(Machine machine){
		this.machine = machine;
	}
	
	/**
	 * Runs the simulation
	 * @throws TapeException Thrown, if a tape action fails during the simulation
	 */
	public abstract void runMachine() throws TapeException;
	

}
