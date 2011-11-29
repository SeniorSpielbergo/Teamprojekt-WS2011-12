package machine;

import gui.ErrorDialog;
import tape.TapeException;

/**
 * Abstract class that controls a simulation of a machine
 * @author Vanessa Baier
 *
 */
public abstract class Simulation implements Runnable{
	/**
	 * The machine to simulate
	 */
	Machine machine;
	Thread simulationThread;


	/**
	 * Creates a new Simulation object from a Machine object
	 * @param machine The machine to simulate
	 */
	public Simulation(Machine machine){
		this.machine = machine;
		simulationThread = new Thread(this);
	}

	/**
	 * Runs the simulation
	 * @throws TapeException Thrown, if a tape action fails during the simulation
	 */
	protected abstract void runMachine() throws TapeException;
	
	public void start(){
		this.simulationThread.start();
	}

	/**
	 * Runs the thread
	 * */
	public void run(){
		try{
			this.runMachine();
		}
		catch (TapeException e){
			ErrorDialog.showError("The simulation failed because of a tape exception.", e);
		}
		catch (RuntimeException e){
			ErrorDialog.showError("The simulation failed because of an undefined exception.", e);
		}
		finally {
			try {
				this.machine.shutdownTapes();
			} catch (TapeException e) {
				ErrorDialog.showError("Warning: The tapes could't be shutdown correctly.", e);
			}

		}
	}

}
