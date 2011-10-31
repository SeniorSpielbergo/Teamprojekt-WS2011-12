import java.lang.String;

public class State {

	protected String id;
	protected String name;
	
	public State(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
