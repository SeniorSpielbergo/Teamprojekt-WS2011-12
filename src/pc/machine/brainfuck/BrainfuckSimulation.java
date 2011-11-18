package machine.brainfuck;
import tape.*;

import machine.*;

import javax.swing.JLabel;

/**
 * Interpreter for brainfuck-Code using a Tape of 2-bit symbols. 
 * @author Sven Schuster
 *
 */
public class BrainfuckSimulation extends Simulation {
	Tape actionTape;
	Tape inputTape;
	String code;
	String outputString = "";
	char toWrite;
	JLabel outputLabel;

	/**
	 * Creates new Simulator with given Tape and Input-String.
	 * @param tape Tape to run the simulation on.
	 * @param inputString Optional String for the brainfuck-Application to read.
	 * @param outputLabel Label to write the output in. 
	 */
	public BrainfuckSimulation(BrainfuckMachine machine){
		super(machine);
		this.actionTape = machine.getTapes().get(0);
		this.inputTape = machine.getTapes().get(1);
		this.code = machine.getCode();
		this.outputLabel = machine.getOutputLabel();
	}

	/**
	 * Returns the Output of the simulated brainfuck-Application.
	 * @return String output
	 */
	public String getOutput() {
		return this.outputString;
	}

	// Prints the output into the outputLabel if existing, else prints output to console.
	private void output() {
		this.outputLabel.setText(outputString);
	}

	// Checks syntax of brainfuck-Application (just checks the loops)
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
	
	/**
	 * Executing the brainfuckMachine.
	 * @throws TapeException If something on the tape went wrong.
	 * @throws IllegalArgumentException If the syntax of the brainfuck-Code is not correct. 
	 */
	public void runMachine() throws TapeException, IllegalArgumentException {
		runMachine(code);
	}

	private void runMachine(String code) throws TapeException, IllegalArgumentException {
		int instructionPointer = 0;
		if(checkSyntax(code)){
			while(instructionPointer < code.length()){
				switch(code.charAt(instructionPointer)) {
				case '<': 
					actionTape.moveLeft();
					break;
				case '>': 
					actionTape.moveRight();
					break;
				case '+': 
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
					int currentValue;
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
							instructionPointer += x;
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
					break;
				case '.': 
					outputString += actionTape.read();
					output();
					break;
				case ',':
					char input = inputTape.read();
					inputTape.moveRight();
					actionTape.write(input);
					break;
				}
				instructionPointer++;
			}
		}
		else
			throw new IllegalArgumentException();
	}
}