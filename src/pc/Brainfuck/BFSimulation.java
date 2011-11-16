package Brainfuck;
import Tape.*;
import javax.swing.JLabel;

/**
 * Interpreter for brainfuck-Code using a Tape of 2-bit symbols. 
 * @author Sven Schuster
 *
 */
public class BFSimulation {
	Tape tape;
	String code;
	String inputString;
	String outputString = "";
	int inputIterator = 0;
	char toWrite;
	JLabel outputLabel;

	/**
	 * Creates new Simulator with given Tape and Input-String.
	 * @param tape Tape to run the simulation on.
	 * @param inputString Optional String for the brainfuck-Application to read.
	 * @param outputLabel Label to write the output in. 
	 */
	public BFSimulation(Tape tape, String inputString, JLabel outputLabel){
		this.tape = tape;
		this.inputString = inputString;
		this.outputLabel = outputLabel;
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
		if(outputLabel != null)
			outputLabel.setText(outputString);
		else
			System.out.println(outputString);
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
	 * Executing the given brainfuck-Code.
	 * @param code The brainfuck-Code to be executed.
	 * @throws TapeException If something on the tape went wrong.
	 * @throws IllegalArgumentException If the syntax of the brainfuck-Code is not correct. 
	 */
	public void runSimulation(String code) throws TapeException, IllegalArgumentException {
		int instructionPointer = 0;
		if(checkSyntax(code)){
			while(instructionPointer < code.length()){
				switch(code.charAt(instructionPointer)) {
				case '<': 
					tape.moveLeft();
					break;
				case '>': 
					tape.moveRight();
					break;
				case '+': 
					switch(tape.read()) {
					case '#':
						tape.write('0'); break;
					case '0':
						tape.write('1'); break;
					case '1': 
						tape.write('2'); break;
					case '2': 
						break;
					default: 
						break;
					}
					break;
				case '-':
					switch(tape.read()) {
					case '#':
						break;
					case '0':
						tape.write('#'); break;
					case '1': 
						tape.write('0'); break;
					case '2': 
						tape.write('1'); break;
					default: 
						break;
					}

					break;
				case '[': 
					int currentValue;
					while(true) {
						switch(tape.read()) {
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
							runSimulation(loopCode);
						}
					}
					break;
				case ']': 
					break;
				case '.': 
					outputString += tape.read();
					output();
					break;
				case ',': 
					if(inputIterator < inputString.length())
						tape.write(inputString.charAt(inputIterator));
					else
						tape.write('#');
					inputIterator++;
					break;
				}
				instructionPointer++;
			}
		}
		else
			throw new IllegalArgumentException();
	}
}