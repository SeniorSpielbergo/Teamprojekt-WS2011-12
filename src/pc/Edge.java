import java.lang.String;
import java.util.ArrayList;

public class Edge {

	protected String id;
	protected String from;
	protected String to;
	protected ArrayList<String> read;
	protected ArrayList<String> write;
	protected ArrayList<String> action;
	
	public Edge(String id, String from, String to, ArrayList<String> read, ArrayList<String> write, ArrayList<String> action) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.read = read;
		this.write = write;
		this.action = action;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
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
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setRead(ArrayList<String> read) {
		this.read = read;
	}
	
	public void setWrite(ArrayList<String> write) {
		this.write = write;
	}

	public void setAction(ArrayList<String> action) {
		this.action = action;
	}

}
