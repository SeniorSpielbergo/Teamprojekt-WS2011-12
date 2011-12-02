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
	boolean simulationAlreadyStarted;
	protected boolean simulationIsPaused;


	/**
	 * Creates a new Simulation object from a Machine object
	 * @param machine The machine to simulate
	 */
	public Simulation(Machine machine){
		this.machine = machine;
		this.simulationThread = new Thread(this);
		this.simulationAlreadyStarted = false;
		this.simulationIsPaused = false;
	}

	/**
	 * Runs the simulation
	 * @throws TapeException Thrown, if a tape action fails during the simulation
	 */
	protected abstract void runMachine() throws TapeException;
	
	public void start(){
		this.simulationAlreadyStarted = true;
		this.simulationThread.start();
		
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Thread getSimulationThread() {
		return simulationThread;
	}

	public void setSimulationThread(Thread simulationThread) {
		this.simulationThread = simulationThread;
	}

	public boolean isSimulationAlreadyStarted() {
		return simulationAlreadyStarted;
	}

	public void setSimulationAlreadyStarted(boolean simulationAlreadyStarted) {
		this.simulationAlreadyStarted = simulationAlreadyStarted;
	}
	
	/**
	 * Runs the thread
	 * */
	public void run(){
		this.simulationAlreadyStarted = true;
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
	
	public void resume() {
		this.simulationIsPaused = false;
	}

	public void pause() {
		this.simulationIsPaused = true;
	}
}
