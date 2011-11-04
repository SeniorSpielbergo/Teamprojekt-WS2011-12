import javax.smartcardio.CardTerminals.State;


public class Simulation {

	Graph machine;
	Tape tape;
	State actualState, startState;
	char currentSymbol;


	public Simulation(Graph g){
		tape = new Tape();
		machine = new Graph(InOut.readXMLFromFile("example.xml"));
		for( int i =0; i <= machine.getStates().size(); i++){
			if(machine.getStates.get(i).equals("start")){
				startState = machine.getStates().get(i);
			}
		}

	}

	void createMachine(){

		actualState = startState;
		this.findLabels();

	}

	public void runMachine(){
		currentSymbol = this.tape.read();
		for(Edge e : actualState.getLabels()){
			if( e.getRead() == currentSymbol)
				break;
		}
	}



	private void findLabels(){
		for( State s : machine.getStates()){
			for( Edge e : machine.getEdges()){
				if(e.getFrom().equals(s.getId()))
					s.getLabels().add(e);
			}
		}

	}
}
