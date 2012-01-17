package machine;

import gui.ErrorDialog;
import tape.TapeException;
import java.util.Observable;

/**
 * Abstract class that controls a simulation of a machine.
 * @author Vanessa Baier
 *
 */
public abstract class Simulation extends Observable implements Runnable{
	public enum simulationState {
		ABORTED,
		FINISHED,
		FAILED
	};
	
	/**
	 * The machine to simulate
	 */
	protected Machine machine;
	protected Thread simulationThread;
	boolean simulationAlreadyStarted;
	protected boolean simulationIsPaused,abortSimulation,simulationAborted;
	protected int maxNumberOfSteps, numberOfSteps, numberOfMovementsGraphic, numberOfMovementsLego,
					numberOfWritingsGraphic, numberOfWritingsLego;
	


	/**
	 * @return the numberOfWritingsGraphic
	 */
	public int getNumberOfWritingsGraphic() {
		return numberOfWritingsGraphic;
	}



	/**
	 * @param numberOfWritingsGraphic the numberOfWritingsGraphic to set
	 */
	public void setNumberOfWritingsGraphic(int numberOfWritingsGraphic) {
		this.numberOfWritingsGraphic = numberOfWritingsGraphic;
	}



	/**
	 * @return the numberOfWritingsLego
	 */
	public int getNumberOfWritingsLego() {
		return numberOfWritingsLego;
	}



	/**
	 * @param numberOfWritingsLego the numberOfWritingsLego to set
	 */
	public void setNumberOfWritingsLego(int numberOfWritingsLego) {
		this.numberOfWritingsLego = numberOfWritingsLego;
	}



	/**
	 * @return the numberOfSteps
	 */
	public int getNumberOfSteps() {
		return numberOfSteps;
	}



	/**
	 * @return the numberOfMovementsGraphic
	 */
	public int getNumberOfMovementsGraphic() {
		return numberOfMovementsGraphic;
	}



	/**
	 * @param numberOfMovementsGraphic the numberOfMovementsGraphic to set
	 */
	public void setNumberOfMovementsGraphic(int numberOfMovementsGraphic) {
		this.numberOfMovementsGraphic = numberOfMovementsGraphic;
	}



	/**
	 * @return the numberOfMovementsLego
	 */
	public int getNumberOfMovementsLego() {
		return numberOfMovementsLego;
	}



	/**
	 * @param numberOfMovementsLego the numberOfMovementsLego to set
	 */
	public void setNumberOfMovementsLego(int numberOfMovementsLego) {
		this.numberOfMovementsLego = numberOfMovementsLego;
	}



	/**
	 * @param numberOfSteps the numberOfSteps to set
	 */
	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	/**
	 * Creates a new Simulation object from a Machine object
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
		this.numberOfMovementsGraphic = 0;
		this.numberOfMovementsLego = 0;
		this.numberOfWritingsGraphic = 0;
		this.numberOfWritingsLego = 0;
	}

	/**
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
	 * @return the simulationAborted
	 */
	public boolean isSimulationAborted() {
		return simulationAborted;
	}

	/**
	 * @param simulationAborted the simulationAborted to set
	 */
	public void setSimulationAborted(boolean simulationAborted) {
		this.simulationAborted = simulationAborted;
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
