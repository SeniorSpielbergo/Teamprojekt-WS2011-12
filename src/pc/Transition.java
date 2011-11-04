import java.util.ArrayList;

public class Transition {

	String id;
	protected ArrayList<String> read;	// TODO character
	protected ArrayList<String> write;	// TODO character
	protected ArrayList<String> action;	// TODO character
	
	public Transition(String id, ArrayList<String> read, ArrayList<String> write, ArrayList<String> action) {
		this.id = id;
		this.read = read;
		this.write = write;
		this.action = action;
	}
	
	public String getId() {
		return this.id;
	}
	
	public ArrayList<String> getRead() {
		return this.read;
	}
	
	public ArrayList<String> getWrite() {
		return this.write;
	}
	
	public ArrayList<String> getAction() {
		return this.action;
	}

}