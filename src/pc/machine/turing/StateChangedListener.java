package machine.turing;
import java.util.EventObject;

public interface StateChangedListener {
	public void handleStateChanged(EventObject e);
}
