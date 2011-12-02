package machine.brainfuck;
import tape.*;
import machine.*;

/**
 * Interpreter for brainfuck code using a tape of 2-bit symbols. 
 * @author Sven Schuster
 * 
 */
public class BrainfuckSimulation extends Simulation {
	private Tape actionTape;
	private Tape inputTape;
	private Tape outputTape;
	private String code;

	/**
	 * Creates new simulation with given tape and inputstring.
	 * @param machine brainfuck machine to run.
	 */
	public BrainfuckSimulation(BrainfuckMachine machine){
		super(machine);
		this.inputTape = machine.getTapes().get(0);
		this.outputTape = machine.getTapes().get(1);
		this.actionTape = machine.getTapes().get(2);
		this.code = machine.getCode();
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
	 * Executing the brainfuck machine.
	 * @throws TapeException If an operation on the tape could not be executed correctly.
	 * @throws IllegalArgumentException If the syntax of the brainfuck code is not correct. 
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
					outputTape.write(actionTape.read());
					outputTape.moveRight();
					break;
				case ',':
					char input = inputTape.read();
					inputTape.moveRight();
					actionTape.write(input);
					break;
				}
				instructionPointer++;
				while(this.simulationIsPaused) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else
			throw new IllegalArgumentException("Syntaxcheck failed");
	}
}