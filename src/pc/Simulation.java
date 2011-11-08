import java.util.ArrayList;

import Tape.Tape;
import Tape.TapeException;


public class Simulation {

	TuringMachine machine;
	State actualState, startState, nextState;
	ArrayList<Tape> tapes = null;
	ArrayList<Character> currentSymbols = new ArrayList<Character>();
	int numberOfTapes;



	public Simulation(TuringMachine g, ArrayList<Tape> tapes){
		this.tapes = tapes;
		this.numberOfTapes = tapes.size();
		machine = (InOut.readXMLFromFile("example.xml"));		
		for( int i =0; i <= machine.getStates().size(); i++){
			if(machine.getStates().get(i).equals("start")){
				startState = machine.getStates().get(i);
			}
		}
	}

	void createMachine(){

		actualState = startState;
		this.findEdge();

	}

	public void runMachine()throws TapeException{
		if(!actualState.getType().equals("final")){
			for(int i = 0; i < numberOfTapes; i++)
				currentSymbols.add(i,this.tapes.get(i).read());

			Transition rightLabel = getRightLabel();

			for(int i = 0; i < numberOfTapes; i++){
				tapes.get(i).write(rightLabel.getWrite().get(i));

				switch(rightLabel.getAction().get(i)){

				case 'R':
					tapes.get(i).moveRight();
					break;
				case 'L':
					tapes.get(i).moveLeft();
					break;
				default:
					break;
				}
			}

			actualState = nextState;
			if(!actualState.getType().equals("final")){
				runMachine();
			}

		}
	}

	private Transition getRightLabel(){
		Transition label= null;
		for(Edge e : actualState.getEdge()){
			for(int j = 0; j < e.getTransition().size(); j++){
				for(int i = 0; i < numberOfTapes; i++)
					if( e.getTransition().get(j).getRead().get(i) == currentSymbols.get(i)){
						label = e.getTransition().get(j);
						//nextState = e.getTransition().getTo();
						
					}
					else{
						label = null;
						nextState = null;
						break;
					}
			}
		}
		return label;
	}

	private void findEdge(){
		for( State s : machine.getStates()){
			for( Edge e : machine.getEdges()){
				if(e.getFrom().equals(s.getId()))
					s.getEdge().add(e);
			}
		}

	}
}
