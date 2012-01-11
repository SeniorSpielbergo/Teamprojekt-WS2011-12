package machine.brainfuck;

import java.util.ArrayList;

import gui.brainfuck.BrainfuckEditor;
import tape.*;
import machine.*;

/**
 * Interpreter for brainfuck code using a tape of 2-bit symbols. 
 * @author Sven Schuster
 */
public class BrainfuckSimulation extends Simulation {
	private Tape actionTape;
	private Tape inputTape;
	private Tape outputTape;
	private String code;
	private ArrayList<Integer> loopBegin;
	private boolean outputMoved = false,
			inputMoved = false;

	/**
	 * Creates new simulation with given BrainfuckMachine.
	 * @param machine brainfuck machine to run.
	 */
	public BrainfuckSimulation(BrainfuckMachine machine){
		super(machine);

		this.inputTape = machine.getTapes().get(0);
		this.outputTape = machine.getTapes().get(1);
		this.actionTape = machine.getTapes().get(2);
		this.code = ((BrainfuckEditor) machine.getEditor()).getCode();
		this.loopBegin = new ArrayList<Integer>();
		this.loopBegin.add(0);

		this.addObserver((BrainfuckEditor) machine.getEditor());
	}

	/**
	 * Executing the brainfuck machine.
	 * @throws TapeException If an operation on the tape could not be executed correctly.
	 * @throws IllegalArgumentException If the syntax of the brainfuck code is not correct. 
	 */
	@Override
	public void runMachine() throws TapeException, IllegalArgumentException {
		if(!checkSyntax(code)) {
			this.abortSimulation = true;
			throw new IllegalArgumentException("Syntaxcheck failed."); // TODO: better solution?
		}
		else
			runMachine(code);
		
		if(this.abortSimulation) {
			this.simulationAborted = true;
			super.setChanged();
			super.notifyObservers((Object) Simulation.simulationState.ABORTED);
		}
		else {
			super.setChanged();
			super.notifyObservers((Object) Simulation.simulationState.FINISHED);
		}
	}

	// Recursive function to simulate brainfuck code.
	private void runMachine(String code) throws TapeException, IllegalArgumentException {
		int instructionPointer = 0;
		while(!this.abortSimulation && instructionPointer < code.length()){
			switch(code.charAt(instructionPointer)) {
			case '<': 
				this.highlight(instructionPointer);
				actionTape.moveLeft();
				break;
			case '>':
				this.highlight(instructionPointer);
				actionTape.moveRight();
				break;
			case '+': 
				this.highlight(instructionPointer);
				switch(actionTape.read()) {
				case '#':
					actionTape.write('0'); break;
				case '0':
					actionTape.write('1'); break;
				case '1': 
					actionTape.write('2'); break;
				case '2': 
					break;
				default: 
					break;
				}
				break;
			case '-':
				this.highlight(instructionPointer);
				switch(actionTape.read()) {
				case '#':
					break;
				case '0':
					actionTape.write('#'); break;
				case '1': 
					actionTape.write('0'); break;
				case '2': 
					actionTape.write('1'); break;
				default: 
					break;
				}
				break;
			case '[': 
				this.highlight(instructionPointer);
				int currentValue;
				loopBegin.add(loopBegin.get(loopBegin.size()-1) + instructionPointer + 1);
				this.sleep();
				while(true) {
					switch(actionTape.read()) {
					case '#':
						currentValue = 0; break;
					case '0':
						currentValue = 1; break;
					case '1': 
						currentValue = 2; break;
					case '2': 
						currentValue = 3; break;
					default: 
						currentValue = 0; break;
					}
					if(currentValue == 0){
						int x = 0;
						int y = 1;
						int i = instructionPointer;
						while(true) {
							i++;
							x++;
							switch(code.charAt(i)){
							case '[': y++; break;
							case ']': y--; break;
							default: break;
							}
							if(code.charAt(i) == ']' && y == 0)
								break;
						}
						instructionPointer += x-1;
						break;
					}
					else{
						String loopCode = "";
						int i = instructionPointer;
						int x = 1;
						while(x != 0){
							i++;
							switch(code.charAt(i)) {
							case '[': 
								loopCode+=code.charAt(i);
								x++; 
								break;
							case ']':
								x--; 
								if(x != 0)
									loopCode+=code.charAt(i);
								break;
							default: 
								loopCode+=code.charAt(i); 
								break;
							}
						}
						runMachine(loopCode);
					}
				}
				break;
			case ']': 
				loopBegin.remove(loopBegin.size()-1);
				this.highlight(instructionPointer);
				this.sleep();
				break;
			case '.':
				this.highlight(instructionPointer);
				if(this.outputMoved)
					inputTape.moveRight();
				outputTape.write(actionTape.read());
				this.outputMoved = true;
				break;
			case ',':
				this.highlight(instructionPointer);
				if(this.inputMoved)
					inputTape.moveRight();
				actionTape.write(inputTape.read());
				this.inputMoved = true;
				break;
			}
			instructionPointer++;
			while(this.simulationIsPaused) {
				this.sleep();
			}
		}
	}

	// Checks syntax of brainfuck-Application (just checks the loops).
	private boolean checkSyntax(String code) {
		int i = 0,
				x = 0;
		while(i < code.length()) {
			if(code.charAt(i) == '[')
				x++;
			else if(code.charAt(i) == ']')
				x--;
			if(x == -1)
				return false;
			i++;
		}
		return x == 0;
	}

	// Sleeps 400ms.
	private void sleep() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Notifies Editor to highlight character at instructionPointer.
	private void highlight(int ip) {
		super.setChanged();
		super.notifyObservers((Object) (ip + this.loopBegin.get(this.loopBegin.size()-1)));
	}
}	