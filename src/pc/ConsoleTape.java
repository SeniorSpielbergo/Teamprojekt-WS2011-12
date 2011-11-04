import java.util.*;

public class ConsoleTape extends Tape {
	Dictionary<Integer, char> memory = new Dictionary<Integer, char>();
	int position = 0;
	
	public void init() throws Exception {
		this.ready = true;
	}
	
	public abstract void shutdown()  {
		this.ready = false;
	}
		
	
	public abstract char read() {
		return this.memory.get(this.position);
	}
	
	public abstract void write(char c) {
		return this.memory.get(this.position, c);
	}
	
	public abstract void moveLeft() {
		position--;
	}
	
	public abstract void moveRight() {
		position++;
	}
	
	public abstract void test() {//TODO: remove
		System.out.println("Test"); 
	}
}
