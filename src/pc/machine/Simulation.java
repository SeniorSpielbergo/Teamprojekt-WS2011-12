package machine;

import gui.ErrorDialog;
import tape.TapeException;

/**
 * Abstract class that controls a simulation of a machine.
 * @author Vanessa Baier
 *
 */
public abstract class Simulation implements Runnable{
	/**
	 * The machine to simulate
	 */
	protected Machine machine;
	protected Thread simulationThread;
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

	/**
	 * The method that invokes that the thread get started.
	 */
	public void start(){
		this.simulationAlreadyStarted = true;
		this.simulationThread.start();

	}

	/**
	 * Returns the current machine.
	 * @return machine
	 */
	public Machine getMachine() {
		return machine;
	}

	/**
	 * Sets the currentmachine.
	 * @param machine
	 */
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	/**
	 * Returns the simulation's thread.
	 * @return simulationThread
	 */
	public Thread getSimulationThread() {
		return simulationThread;
	}

	/**
	 * Returns if the simulation has already started.
	 * @return simulationIsStarted True if the simulation has started <br>
	 * 								False if the simulation hasn't started yet.
	 */
	public boolean isSimulationAlreadyStarted() {
		return simulationAlreadyStarted;
	}

	/**
	 * Sets that the simulation has started.
	 * 
	 */
	public void setSimulationAlreadyStarted() {
		this.simulationAlreadyStarted = true; //TODO is needed?
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

	/**
	 * Resumes the simulation.
	 */
	public void resume() {
		this.simulationIsPaused = false;
	}

	/**
	 * Pauses the simulation.
	 */
	public void pause() {
		this.simulationIsPaused = true;
	}
}
