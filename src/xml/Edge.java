import java.lang.String;

public class Edge {

	protected int id;
	protected int from;
	protected int to;
	protected String[] read;
	protected String write;
	
	public Edge(int id, int from, int to, String[] read, String write) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.read = read;
		this.write = write;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getFrom() {
		return this.from;
	}
	
	public int getTo() {
		return this.to;
	}
	
	public String[] getRead() {
		return this.read;
	}
	
	public String getWrite() {
		return this.write;
	}
	
	public void setFrom(int from) {
		this.from = from;
	}
	
	public void setTo(int to) {
		this.to = to;
	}
	
	public void setRead(String[] read) {
		this.read = read;
	}
	
	public void setWrite(String write) {
		this.write = write;
	}

}
