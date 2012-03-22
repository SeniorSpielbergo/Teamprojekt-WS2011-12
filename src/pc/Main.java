import javax.swing.*;
import tape.*;

import java.util.*;

/** Contains the main method to run the server program
 * 
 * @author Nils Breyer
 * 
 */
public class Main {
	public static ArrayList<Tape> tapes = new ArrayList<Tape>();

	public static void main(String[] args) {
		String type = JOptionPane.showInputDialog("Enter 'LEGO' or 'PC':");
		if (type.equals("LEGO")) {
			//MasterRobot ips_03 = new MasterRobot("IPS_03", "00:16:53:13:53:BB");
			MasterRobot ips_03 = new MasterRobot("NXT_07", "00:16:53:04:ED:8C");
			//SlaveRobot nxt_03 = new SlaveRobot("NXT_40", "00:16:53:05:9C:73");
			SlaveRobot nxt_03 = new SlaveRobot("NXT_03", "00:16:53:0F:DB:8E");
			Tape tape_lego = new LEGOTape(ips_03, nxt_03);
			tapes.add(tape_lego);
		}
		else if (type.equals("PC")) {
			Tape tape_console = new ConsoleTape();
			tapes.add(tape_console);
		}
		else {
			System.out.println("If you are too stupid to enter one of the words 'LEGO' or 'PC', you shouldn't use this program.");
			return;
		}


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
			try {
				if (cmd.equals("quit")) {
					tapes.get(0).shutdown();
					break;
				}
				else if (cmd.equals("read")) {
					char c = tapes.get(0).read();
					System.out.println("Read: " + c);
				}
				else if (cmd.equals("write")) {
					char write = JOptionPane.showInputDialog("Enter new symbol:").toCharArray()[0];
					tapes.get(0).write(write);
				}
				else if (cmd.equals("l")) {
					tapes.get(0).moveLeft();
				}
				else if (cmd.equals("r")) {
					tapes.get(0).moveRight();
				}
				else {
					System.out.println("Unkown command '" + cmd + "'.");
				}
			}
			catch (TapeException e) {
				System.out.println("Error while excecuting the command '" + cmd + "': ");
				e.printStackTrace();
			}

		}
	}
}