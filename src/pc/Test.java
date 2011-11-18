import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;

import machine.turing.*;

import tape.ConsoleTape;
import tape.Tape;
import tape.TapeException;

/** This class provides a test program for InOut.java
 * 
 * @author David Wille
 * 
 */
public class Test {

	public static void main(String argv[]) throws IOException {
		State n0 = new State("0", "q0", State.Type.START);
		State n1 = new State("1", "q1", State.Type.NORMAL);
		ArrayList<State> states = new ArrayList<State>();
		states.add(n0);
		states.add(n1);
		ArrayList<Character> read = new ArrayList<Character>();
		read.add('0');
		read.add('1');
		read.add('2');
		ArrayList<Character> write = new ArrayList<Character>();
		write.add('0');
		write.add('1');
		write.add('2');
		ArrayList<Character> action = new ArrayList<Character>();
		action.add('0');
		action.add('1');
		action.add('2');
		Transition t0 = new Transition("0", read, write, action);
		ArrayList<Transition> transition = new ArrayList<Transition>();
		transition.add(t0);
		Edge e0 = new Edge(n0, n1, transition);
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<Tape> tapes = new ArrayList<Tape>();
		for (int i = 0; i < 4; i++) {
			ConsoleTape tape = new ConsoleTape();
			try {
				tape.setInputWord("abcd");
			}
			catch (TapeException e) {
				e.printStackTrace();
			}
		}
		edges.add(e0);
		String machineName = "test";
		TuringMachine machine = new TuringMachine(machineName, states, edges, new ArrayList<Tape>());
		
		machine.saveXML("example.xml");
		TuringMachine machine2 = null;
		try {
			machine2 = TuringMachine.loadFromXML("TMadd.xml");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		InOut.writeLatexToFile("example.tex", machine2);
	}
	
}
