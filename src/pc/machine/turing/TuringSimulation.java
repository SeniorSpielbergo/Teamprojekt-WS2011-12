package machine.turing;

import java.util.ArrayList;

import machine.Simulation;
import tape.*;


public class TuringSimulation extends Simulation{

	TuringMachine machine;
	State actualState, startState, nextState;
	ArrayList<Tape> tapes = null;
	ArrayList<Character> currentSymbols = new ArrayList<Character>();


	public TuringSimulation(TuringMachine machine) throws TapeException{
		super(machine);
		this.machine = machine;
		this.tapes = machine.getTapes();

		for( int i=0; i < machine.getStates().size(); i++){
			if(machine.getStates().get(i).isStartState()){
				startState = machine.getStates().get(i);
				actualState = startState;
			}
		}
		System.out.println("Start: "+startState.getId());
		System.out.println("aktueller Zustand: " +actualState.getId());
		this.findEdge();
	}

	public void runMachine() throws TapeException{
		if(!actualState.isFinalState()){
			currentSymbols.clear();
			for(int i = 0; i < machine.getNumberOfTapes(); i++)
				currentSymbols.add(i,this.tapes.get(i).read());

			Transition rightLabel = getRightLabel();
			System.out.println("rightLabel: "+rightLabel);

			for(int i = 0; i < machine.getNumberOfTapes(); i++){
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
			System.out.println("aktueller Zustand: " +actualState.getId());

			if(!(actualState.isFinalState())){

				while(this.simulationIsPaused){

					try{
						Thread.sleep(400);
					}
					catch(InterruptedException e){}

				}


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
			System.out.println("Transition: "+e.getTransitions().size());
			for(int j = 0; j < e.getTransitions().size(); j++){
				System.out.println(e.getTransitions().get(j).getId() +" ");
				System.out.println("Groeße read: "+e.getTransitions().get(j).getRead().size());
				for(int i = 0; i < machine.getNumberOfTapes(); i++){
					System.out.println("Transition-Symbol: " + e.getTransitions().get(j).getRead().get(i));
					System.out.println("Tape symbol: " + currentSymbols.get(i));
					if( e.getTransitions().get(j).getRead().get(i) == currentSymbols.get(i)){
						label = e.getTransitions().get(j);
						System.out.println(label);
						nextState = e.getTo();
					}
					else{
						label = null;
						nextState = null;
						break;
					}
				}
				if(label != null){
					System.out.println("Edge: " + e);
					return label;
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
