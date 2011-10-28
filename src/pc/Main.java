import lejos.pc.comm.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args){
		NXTComm nxtComm_IPS_03 = null;
		NXTComm nxtComm_NXT_03 = null;

		try {
			nxtComm_IPS_03 = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			nxtComm_NXT_03 = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		}
		catch (Exception e) {
			
		}
		
		NXTInfo info_IPS_03 = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_03", "00:16:53:13:53:BB");
		NXTInfo info_NXT_03 = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT_03", "00:16:53:0F:DB:8E");
		
		try {
			System.out.println("Connecting to IPS_03...");

			nxtComm_IPS_03.open(info_IPS_03);
			
			InputStream is_IPS_03 = nxtComm_IPS_03.getInputStream();
			OutputStream os_IPS_03 = nxtComm_IPS_03.getOutputStream();

			DataInputStream in_IPS_03 = new DataInputStream(is_IPS_03);
			DataOutputStream out_IPS_03 = new DataOutputStream(os_IPS_03);

			System.out.println("Connected. Connecting to NXT_03...");
			
			nxtComm_NXT_03.open(info_NXT_03);
			
			InputStream is_NXT_03 = nxtComm_NXT_03.getInputStream();
			OutputStream os_NXT_03 = nxtComm_NXT_03.getOutputStream();

			DataInputStream in_NXT_03 = new DataInputStream(is_NXT_03);
			DataOutputStream out_NXT_03 = new DataOutputStream(os_NXT_03);
			
			System.out.println("Connected. Ready.");

			
			while (true) {
				String cmd = JOptionPane.showInputDialog("Enter command:");
				if (cmd.equals("quit")) {
					System.out.println("Disconnecting...");
					out_NXT_03.writeChar('q');
					out_NXT_03.flush();
					out_IPS_03.writeChar('q');
					out_IPS_03.flush();
					nxtComm_NXT_03.close();
					nxtComm_IPS_03.close();
					System.out.println("Disconnected.");
					break;
				}
				else if (cmd.equals("IPS_03:test")) {
					out_IPS_03.writeChar('t');
					out_IPS_03.flush();
				}
				else if (cmd.equals("NXT_03:test")) {
					out_NXT_03.writeChar('t');
					out_NXT_03.flush();
				}
			}
			
//			while (true) {
//				System.out.println("Sensor 1: " + in.readInt());
//				System.out.println("Sensor 2: " + in.readInt());
//				System.out.println("Sensor 4: " + in.readInt());
//			}
		}
		catch (Exception e){
			//System.out.println(e.getMessage());
		}
	}
}