import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args){
		NXTComm nxtComm = null;
		try {
			nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		}
		catch (Exception e) {
			
		}
		NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_03", "00:16:53:13:53:BB");
		
		try {
			nxtComm.open(info);

			InputStream is = nxtComm.getInputStream();
			//OutputStream os = nxtComm.getOutputStream();

			DataInputStream in = new DataInputStream(is);
			//DataOutputStream out = new DataOutputStream(os);

			System.out.println("Connected...");
			
			while (true) {
				System.out.println("Sensor 1: " + in.readInt());
				System.out.println("Sensor 2: " + in.readInt());
				System.out.println("Sensor 4: " + in.readInt());
			}

		}
		catch (Exception e){
			//System.out.println(e.getMessage());
		}
	}
}