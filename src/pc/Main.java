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
				char write = JOptionPane.showInputDialog("Enter symbol:").toCharArray()[0];
				tapes.get(0).write();
			}
		}


		//		NXTComm nxtComm_IPS_03 = null;
		//		NXTComm nxtComm_NXT_03 = null;
		//
		//		try {
		//			nxtComm_IPS_03 = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		//			nxtComm_NXT_03 = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		//		}
		//		catch (Exception e) {
		//			
		//		}
		//		
		//		NXTInfo info_IPS_03 = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_03", "00:16:53:13:53:BB");
		//		NXTInfo info_NXT_03 = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT_03", "00:16:53:0F:DB:8E");
		//		
		//		try {
		//			System.out.println("Connecting to IPS_03...");
		//
		//			nxtComm_IPS_03.open(info_IPS_03);
		//			
		//			InputStream is_IPS_03 = nxtComm_IPS_03.getInputStream();
		//			OutputStream os_IPS_03 = nxtComm_IPS_03.getOutputStream();
		//
		//			DataInputStream in_IPS_03 = new DataInputStream(is_IPS_03);
		//			DataOutputStream out_IPS_03 = new DataOutputStream(os_IPS_03);
		//
		//			System.out.println("Connected. Connecting to NXT_03...");
		//			
		//			nxtComm_NXT_03.open(info_NXT_03);
		//			
		//			InputStream is_NXT_03 = nxtComm_NXT_03.getInputStream();
		//			OutputStream os_NXT_03 = nxtComm_NXT_03.getOutputStream();
		//
		//			DataInputStream in_NXT_03 = new DataInputStream(is_NXT_03);
		//			DataOutputStream out_NXT_03 = new DataOutputStream(os_NXT_03);
		//			
		//			System.out.println("Connected. Ready.");
		//
		//			
		//			while (true) {
		//				String cmd = JOptionPane.showInputDialog("Enter command:");
		//				if (cmd.equals("quit")) {
		//					System.out.println("Disconnecting...");
		//					out_NXT_03.writeChar('q');
		//					out_NXT_03.flush();
		//					out_IPS_03.writeChar('q');
		//					out_IPS_03.flush();
		//					nxtComm_NXT_03.close();
		//					nxtComm_IPS_03.close();
		//					System.out.println("Disconnected.");
		//					break;
		//				}
		//				else if (cmd.equals("IPS:test")) {
		//					out_IPS_03.writeChar('t');
		//					out_IPS_03.flush();
		//				}
		//				else if (cmd.equals("NXT:test")) {
		//					out_NXT_03.writeChar('t');
		//					out_NXT_03.flush();
		//				}
		//				else if (cmd.equals("read")) {
		//					out_IPS_03.writeChar('s');
		//					out_IPS_03.flush();
		//					char c0 = in_IPS_03.readChar();
		//					char c1 = in_IPS_03.readChar();
		//					System.out.println("Read: " + c0 + ", " + c1);
		//				}
		//			}
		//		}
		//		catch (Exception e){
		//			System.out.println(e.getMessage());
		//		}
	}
}