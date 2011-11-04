import javax.smartcardio.CardTerminals.State;
import java.util.ArrayList;


public class Simulation {

	Graph machine;
	State actualState, startState;
	ArrayList<Tape> tapes = null;
	ArrayList<Character> currentSymbols = new ArrayList<Character>();
	int numberOfTapes;



	public Simulation(Graph g, ArrayList<Tape> tapes){
		this.tapes = tapes;
		this.numberOfTapes = tapes.size();
		machine = new Graph(InOut.readXMLFromFile("example.xml"));		
		for( int i =0; i <= machine.getStates().size(); i++){
			if(machine.getStates.get(i).equals("start")){
				startState = machine.getStates().get(i);
			}
		}
	}

	void createMachine(){

		actualState = startState;
		this.findEdge();

	}

	public void runMachine(){
		if(!actualState.getType().equals("final"){
			for(int i = 0; i < numberOfTapes; i++)
				currentSymbols.add(i,this.tapes.get(i).read());

			Transition rightLabel = getRightLabel();

			for(int i = 0; i < numberOfTapes; i++)
				tapes.get(i).write(rightLabel).getWrite().get(i));

		switch(rightLabel().getAction().get(i)){

		case 'R':
			tapes.get(i).moveRight();
			break;
		case 'L':
			tapes.get(i).moveLeft();
			break;
		default:
			break;
		}

		actualState = rightLabel().getTo();
		if(!actualState.getType().equals("final")){
			runMachine();
		}

		}
	}

	private Transition getRightLabel(){
		Transition label;
		for(int j = 0; j < actualState.getEdge().getTransition().size(); j++){
			for(int i = 0; i < numberOfTapes; i++))
			if( e.getRead().get(i) == currentSymbols.get(i)){
				label = actualState.getEdge().getTransition().get(j);
			}
			else{
				label = null;
				break;
			}
		}
	}

	private void findEdge(){
		for( State s : machine.getStates()){
			for( Edge e : machine.getEdges()){
				if(e.getFrom().equals(s.getId()))
					s.getLabels().add(e);
			}
		}

	}
}
