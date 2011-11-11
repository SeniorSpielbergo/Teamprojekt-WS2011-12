package TuringMachine;
import java.util.ArrayList;

/** This class represents the transitions on all tapes for a specific edge
 * 
 * @author David Wille
 * 
 */
public class Transition {

	String id;
	protected ArrayList<Character> read;
	protected ArrayList<Character> write;
	protected ArrayList<Character> action;
	
	/**
	 * Constructs a new transition with all read, write and head configurations
	 * @param id Id of the transition
	 * @param read List of symbols invoking a transition on the tapes
	 * @param write List of symbols written on the tapes during transition
	 * @param action List of head movements on the tapes
	 */
	public Transition(String id, ArrayList<Character> read, ArrayList<Character> write, ArrayList<Character> action) {
		this.id = id;
		this.read = read;
		this.write = write;
		this.action = action;
	}
	
	/**
	 * Returns the transition's id
	 * @return Transition Id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Returns the list of symbols invoking a transition on the tapes
	 * @return List of symbols invoking a transition on the tapes
	 */
	public ArrayList<Character> getRead() {
		return this.read;
	}
	
	/**
	 * Returns the list of symbols written on the tapes during transition
	 * @return List of symbols written on the tapes during transition
	 */
	public ArrayList<Character> getWrite() {
		return this.write;
	}
	
	/**
	 * Returns the list of head movements on the tapes
	 * @return List of head movements on the tapes
	 */
	public ArrayList<Character> getAction() {
		return this.action;
	}

	/**
	 * Gives a string representation of the Transition
	 */
	@Override
	public String toString() {
		return "Transition [id=" + id + ", read=" + read + ", write=" + write
				+ ", action=" + action + "]\n";
	}
	
	

}