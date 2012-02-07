package machine.turing;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class represents the transitions on all tapes for a specific edge.
 * @author David Wille
 */
public class Transition implements Serializable {
	private static final long serialVersionUID = 2530568958006425276L;
	private String id;
	private ArrayList<Character> read;
	private ArrayList<Character> write;
	private ArrayList<Character> action;
	
	/**
	 * Constructs a new transition with all read, write and head configurations.
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
	 * Returns the transition's id.
	 * @return Transition Id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Returns the list of symbols invoking a transition on the tapes.
	 * @return List of symbols invoking a transition on the tapes
	 */
	public ArrayList<Character> getRead() {
		return this.read;
	}
	
	/**
	 * Returns the list of symbols written on the tapes during transition.
	 * @return List of symbols written on the tapes during transition
	 */
	public ArrayList<Character> getWrite() {
		return this.write;
	}
	
	/**
	 * Returns the list of head movements on the tapes.
	 * @return List of head movements on the tapes
	 */
	public ArrayList<Character> getAction() {
		return this.action;
	}

	@Override
	public String toString() {
		String actionString = "<";
		String readString = "<";
		String writeString = "<";
		for (int i = 0; i < action.size(); i++) {
			actionString += "" + action.get(i);
			readString += "" + read.get(i);
			writeString += "" + write.get(i);
			if (i < action.size()-1) {
				actionString += ",";
				readString += ",";
				writeString += ",";
			}
			else if (i == action.size()-1) {
				actionString += ">";
				readString += ">";
				writeString += ">";
			}
		}
		return readString + "/" + writeString + "/" + actionString;
	}
	
	@Override
	public Object clone() {
		ArrayList<Character> readNew = new ArrayList<Character>();
		ArrayList<Character> writeNew = new ArrayList<Character>();
		ArrayList<Character> actionNew = new ArrayList<Character>();
		for(Character c : this.read) {
			readNew.add(c);
		}
		for(Character c : this.write) {
			writeNew.add(c);
		}
		for(Character c : this.action) {
			actionNew.add(c);
		}
		return new Transition(UUID.randomUUID().toString(), 
				readNew,
				writeNew,
				actionNew);
	}

}