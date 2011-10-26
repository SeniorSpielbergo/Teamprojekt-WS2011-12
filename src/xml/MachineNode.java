import java.lang.String;

public class MachineNode {

	protected int id;
	protected String name;
	
	public MachineNode(int id, String nodeName) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
