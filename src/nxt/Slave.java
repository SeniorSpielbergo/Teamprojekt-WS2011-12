import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
public class Slave {
	public static void main(String[] args){
		NXTConnection connection = null;
		LCD.drawString("waiting for bt connection", 1, 1);
		connection = Bluetooth.waitForConnection();
		DataInputStream dataIn = connection.openDataInputStream();
		DataOutputStream dataOut = connection.openDataOutputStream();
		int received = -1;
		while(received < 100){
			try{
				received = dataIn.readInt();
				LCD.clearDisplay();
				
				LCD.drawInt(received, 1, 1);
				dataOut.writeInt(received);
				dataOut.flush();
			} catch(IOException e) {
				System.out.print(e);
			}
		}
		try {
			dataIn.close();
		} catch(IOException e) {
			System.out.print(e);
		}
	}
}
