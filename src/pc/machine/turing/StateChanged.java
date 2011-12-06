package machine.turing;
import java.util.EventObject;

public class StateChanged extends EventObject{
	public StateChanged(Object source){
		super(source);
	}
}
