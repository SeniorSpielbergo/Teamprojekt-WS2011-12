import java.lang.String;
import java.util.ArrayList;

public class Edge {

	protected String id;
	protected String from;
	protected String to;
	protected ArrayList<String> read;
	protected String write;
	
	public Edge(String id, String from, String to, ArrayList<String> read, String write) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.read = read;
		this.write = write;
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
	
	public String getWrite() {
		return this.write;
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
	
	public void setWrite(String write) {
		this.write = write;
	}

}
