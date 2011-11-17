package machine.turing;
import java.io.IOException;
import java.util.ArrayList;
import Tape.*;

public class TestSimulation {

	public static void main (String[] args) throws TapeException, IOException{
		ArrayList<Tape> tapes = new ArrayList<Tape>();
		ConsoleTape tape = new ConsoleTape();
		tape.init();
		tapes.add(tape);
		TuringMachine machine = TuringMachine.loadFromXML("TMadd.xml");
		System.out.println(machine.getTapes());
		Simulation simulation = new Simulation(machine);

		
		try{
			simulation.runMachine();
		}
		catch(TapeException e){
			System.out.println("geht nicht");
			e.printStackTrace();
		}
	}
}
