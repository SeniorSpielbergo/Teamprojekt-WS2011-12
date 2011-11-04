import java.lang.String;
import java.util.ArrayList;

public class Edge {

	protected String from;
	protected String to;
	protected ArrayList<Transition> transition;
	
	public Edge(String from, String to, ArrayList<Transition> transition) {
		this.from = from;
		this.to = to;
		this.transition = transition;
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public ArrayList<Transition> getTransition() {
		return this.transition;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setTransition(ArrayList<Transition> transition) {
		this.transition = transition;
	}

}
