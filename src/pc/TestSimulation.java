import java.util.ArrayList;
import Tape.*;
import TuringMachine.*;

public class TestSimulation {

	public static void main (String[] args) throws TapeException{
		ArrayList<Tape> tapes = new ArrayList<Tape>();
		ConsoleTape tape = new ConsoleTape();
		tape.init();
		tapes.add(tape);
		TuringMachine machine = InOut.readXMLFromFile("TMadd.xml");
		System.out.println(machine.getTapes());
		Simulation simulation = new Simulation(machine, tapes);

		
		try{
			simulation.runMachine();
		}
		catch(TapeException e){
			System.out.println("geht nicht");
			e.printStackTrace();
		}
	}
}