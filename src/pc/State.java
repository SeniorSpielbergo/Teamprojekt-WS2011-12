import java.lang.String;
import java.util.ArrayList;

public class State {
	
	public enum Type {
		START, NORMAL, FINAL
	}

	protected String id;
	protected String name;
	protected Type type;
	protected ArrayList<Edge> edge;
	
	public State(String id, String name, Type type) {
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
	
	public Type getType() {
		return this.type;
	}
	
	public ArrayList<Edge> getEdge() {
		return this.edge;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean equals(String type){
		return this.type.equals(type);
	}
}
