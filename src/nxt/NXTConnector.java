import lejos.nxt.comm.NXTCommConnector;
import javax.bluetooth.*;
public class NXTConnector implements NXTCommConnector {
	public NXTConnection connect(String target,int mode) {
		return new BTConnection(Bluetooth.getKnownDevice(target));
	}
}