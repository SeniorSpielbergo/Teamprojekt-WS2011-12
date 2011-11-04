import java.util.ArrayList;

public class Edge {

	protected State from;
	protected State to;
	protected ArrayList<Transition> transition;
	
	public Edge(State from, State to, ArrayList<Transition> transition) {
		this.from = from;
		this.to = to;
		this.transition = transition;
	}
	
	public State getFrom() {
		return this.from;
	}
	
	public State getTo() {
		return this.to;
	}
	
	public ArrayList<Transition> getTransition() {
		return this.transition;
	}
	
	public void setFrom(State from) {
		this.from = from;
	}
	
	public void setTo(State to) {
		this.to = to;
	}
	
	public void setTransition(ArrayList<Transition> transition) {
		this.transition = transition;
	}

}
