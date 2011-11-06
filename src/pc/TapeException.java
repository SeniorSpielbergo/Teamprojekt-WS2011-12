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
	
	public TapeException(Tape tape, String msg) {
		super(msg);
		this.tape = tape;
	}
	
	public TapeException(Tape tape, String msg, Throwable initCause) {
		super(msg);
		this.tape = tape;
		this.initCause(initCause);
	}
	
	public int getTapePosition() throws TapeException{
		return this.tape.getPosition();
	}
	
	public Tape getTape() {
		return this.tape;
	}
	
}
