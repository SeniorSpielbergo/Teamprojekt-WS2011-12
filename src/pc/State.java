import java.lang.String;
import java.util.ArrayList;

public class State {

	protected String id;
	protected String name;
	protected String type;
	protected ArrayList<Edge> edge;
	
	public State(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.edge = new ArrayList<Edge>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public ArrayList<Edge> getEdge() {
		return this.edge;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.name = type;
	}
	
	public boolean equals(String type){
		return this.type.equals(type);
	}
}
