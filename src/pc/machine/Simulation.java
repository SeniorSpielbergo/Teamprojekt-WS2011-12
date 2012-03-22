package machine;

import gui.ErrorDialog;
import tape.TapeException;
import java.util.Observable;

/**
 * Abstract class that controls a simulation of a machine.
 * @author Nessa Baier
 */
public abstract class Simulation extends Observable implements Runnable{
	public enum simulationState {
		ABORTED,
		FINISHED,
		FAILED
	};
	
	/**
	 * The machine to simulate.
	 */
	protected Machine machine;
	protected Thread simulationThread;
	boolean simulationAlreadyStarted;
	protected boolean simulationIsPaused,abortSimulation,simulationAborted;
	protected int maxNumberOfSteps, numberOfSteps, numberOfMovements, numberOfWritings;
	




	/**
	 * Returns the number of steps needed for the simulation.
	 * @return thenumberOfSteps
	 */
	public int getNumberOfSteps() {
		return numberOfSteps;
	}




	/**
	 * Returns the number of movements needed for the simulation.
	 * @return the numberOfMovements
	 */
	public int getNumberOfMovements() {
		return numberOfMovements;
	}


	/**
	 * Returns the number of writing actions.
	 * @return the numberOfWritings
	 */
	public int getNumberOfWritings() {
		return numberOfWritings;
	}




//	/**
//	 * @param numberOfWritings the numberOfWritings to set
//	 */
//	public void setNumberOfWritings(int numberOfWritings) {
//		this.numberOfWritings = numberOfWritings;
//	}
//
//
//
//
//	/**
//	 * @param numberOfSteps the numberOfSteps to set
//	 */
//	public void setNumberOfSteps(int numberOfSteps) {
//		this.numberOfSteps = numberOfSteps;
//	}

	/**
	 * Creates a new Simulation object from a Machine object.
	 * @param machine The machine to simulate
	 */
	public Simulation(Machine machine){
		this.machine = machine;
		this.simulationThread = new Thread(this);
		this.simulationAlreadyStarted = false;
		this.simulationIsPaused = false;
		this.abortSimulation = false;
		this.simulationAborted = false;
		this.maxNumberOfSteps = 1000;
		this.numberOfSteps= 0;
		this.numberOfMovements =0;
		this.numberOfWritings = 0;
	}

	/**
	 * Returns the maximum number of steps.
	 * @return the maxNumberOfSteps
	 */
	public int getMaxNumberOfSteps() {
		return maxNumberOfSteps;
	}

	/**
	 * @param maxNumberOfSteps the maxNumberOfSteps to set
	 */
	public void setMaxNumberOfSteps(int maxNumberOfSteps) {
		this.maxNumberOfSteps = maxNumberOfSteps;
	}

	/**
	 * Returns if the simulation should be aborted.
	 * @return the abortSimulation
	 */
	public boolean isAbortSimulation() {
		return abortSimulation;
	}

	/**
	 * Starts aborting the abortSimulation
	 */
	public void setAbortSimulation() {
		this.abortSimulation = true;
		
		super.setChanged();
		super.notifyObservers((Object)simulationState.ABORTED);
		System.out.println("Notify");
	}

	/**
	 * Returns true if the simulation is aborted.
	 * @return the simulationAborted
	 */
	public boolean isSimulationAborted() {
		return simulationAborted;
	}

	/**
	 * Set true if the simulation is aborted.
	 * @param simulationAborted the simulationAborted to set
	 */
	public void setSimulationAborted(boolean simulationAborted) {
		this.simulationAborted = simulationAborted;
	}

	/**
	 * Runs the simulation.
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
	 * Sets the current machine.
	 * @param machine The machine
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
		this.simulationAlreadyStarted = true;
	}

	/**
	 * Runs the thread.
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
