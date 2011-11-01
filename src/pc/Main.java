/* This class contains the main method to run the server program
 * 
 * Responsible: Nils Breyer
 * 
 */
import javax.swing.*;
import java.util.*;

public class Main {
	public static ArrayList<Tape> tapes = new ArrayList<Tape>();

	public static void main(String[] args){
		MasterRobot ips_03 = new MasterRobot("IPS_03", "00:16:53:13:53:BB");
		SlaveRobot nxt_03 = new SlaveRobot("NXT_03", "00:16:53:0F:DB:8E");
		Tape tape = new Tape(ips_03, nxt_03);
		tapes.add(tape);

		try {
			for (Tape t : tapes) {
				t.init();
			}
		}
		catch (Exception e) {
			System.out.println("Initializing failed. Shutting down.");
			System.exit(-1);
		}

		while (true) {
			String cmd = JOptionPane.showInputDialog("Enter command:");
			if (cmd.equals("quit")) {
				tapes.get(0).shutdown();
				break;
			}
			else if (cmd.equals("test")) {
				tapes.get(0).test();
			}
			else if (cmd.equals("read")) {
				char c = tapes.get(0).read();
				System.out.println("Read: " + c);
			}
			else if (cmd.equals("write")) {
				char write = JOptionPane.showInputDialog("Enter new symbol:").toCharArray()[0];
				tapes.get(0).write(write);
			}
			else if (cmd.equals("left")) {
				tapes.get(0).moveLeft();
			}
			else if (cmd.equals("right")) {
				tapes.get(0).moveRight();
			}
		}
	}
}