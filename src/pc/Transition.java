import java.util.ArrayList;

public class Transition {

	String id;
	protected ArrayList<Character> read;
	protected ArrayList<Character> write;
	protected ArrayList<Character> action;
	
	public Transition(String id, ArrayList<Character> read, ArrayList<Character> write, ArrayList<Character> action) {
		this.id = id;
		this.read = read;
		this.write = write;
		this.action = action;
	}
	
	public String getId() {
		return this.id;
	}
	
	public ArrayList<Character> getRead() {
		return this.read;
	}
	
	public ArrayList<Character> getWrite() {
		return this.write;
	}
	
	public ArrayList<Character> getAction() {
		return this.action;
	}

}