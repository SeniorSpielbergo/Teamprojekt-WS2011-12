import java.io.IOException;


public class TapeException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tape tape = null;

	
	public TapeException(Tape tape) {
		super();
		this.tape = tape;
	}
	
	public TapeException(String msg, Tape tape) {
		super(msg);
		this.tape = tape;
	}
	
	public int getTapePosition() throws TapeException{
		return this.tape.getPosition();
	}
	
	public Tape getTape() {
		return this.tape;
	}
	
}
