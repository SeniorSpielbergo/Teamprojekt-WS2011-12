package TuringMachine;

import java.util.ArrayList;
import Tape.*;


public class Simulation {

	TuringMachine machine;
	State actualState, startState, nextState;
	ArrayList<Tape> tapes = null;
	ArrayList<Character> currentSymbols = new ArrayList<Character>();
	int numberOfTapes;



	public Simulation(TuringMachine machine, ArrayList<Tape> tapes){
		this.machine = machine;
		this.tapes = tapes;
		this.numberOfTapes = machine.getTapes();
		for( int i =0; i < machine.getStates().size(); i++){
			if(machine.getStates().get(i).getType() == State.Type.START){
				startState = machine.getStates().get(i);
				actualState = startState;
			}

		}
		System.out.println(startState.getId());
		System.out.println(actualState.getId());
		this.findEdge();
	}


	public void runMachine()throws TapeException{
		if(actualState.getType() != State.Type.FINAL){
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
			if(!(actualState.getType() == State.Type.FINAL)){
				runMachine();
			}

			else{
				System.out.println("ich habe fertig");
			}

		}
		else{
			System.out.println("ich musste nie was tun");
		}
	}

	private Transition getRightLabel(){
		Transition label= null;
		for(Edge e : actualState.getEdge()){
			for(int j = 0; j < e.getTransition().size(); j++){
				for(int i = 0; i < numberOfTapes; i++){
					if( e.getTransition().get(j).getRead().get(i) == currentSymbols.get(i)){
						label = e.getTransition().get(j);
						nextState = e.getTo();

					}
					else{
						label = null;
						nextState = null;
						break;
					}
				}
				if(label != null){
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
