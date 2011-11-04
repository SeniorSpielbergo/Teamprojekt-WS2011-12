import java.util.*;

public class ConsoleTape extends Tape {
	HashMap<Integer, Character> memory = new HashMap<Integer, Character>();
	int position = 0;
	
	public void init() throws Exception {
		this.ready = true;
	}
	
	public void shutdown()  {
		this.ready = false;
	}
		
	
	public char read() {
		if (this.memory.get(this.position) != null) {
			return this.memory.get(this.position);
		}
		else {
			return '#';
		}
	}
	
	public void write(char c) {
		this.memory.put(this.position, c);
		this.printTape();
	}
	
	public void moveLeft() {
		position--;
		this.printTape();
	}
	
	public void moveRight() {
		position++;
		this.printTape();
	}
	
	public void test() {//TODO: remove
		System.out.println("Test"); 
	}
	
	public void printTape() {
		System.out.print("-");
		for (int i=-20; i<=20; i++) {
			System.out.print("--");
		}
		System.out.println("");

		System.out.print("|");
		for (int i=-20; i<=20; i++) {
			if (this.memory.get(i) != null) {
				System.out.print(this.memory.get(i));
			}
			else {
				System.out.print("#");
			}
			System.out.print("|");
		}
		System.out.println("");
		
		for (int i=-20; i<this.position; i++) {
			System.out.print("  ");
		}
		System.out.print(" ^");
		System.out.println("");
	
		System.out.print("-");
		for (int i=-20; i<=20; i++) {
			System.out.print("--");
		}
		System.out.println("");

		
	}
}
