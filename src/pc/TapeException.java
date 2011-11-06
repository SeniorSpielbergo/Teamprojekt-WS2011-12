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
		super(tape.getName() + ", position " + tape.getPosition() + ": " + msg);
		this.tape = tape;
	}
	
	public TapeException(Tape tape, String msg, Throwable initCause) {
		super(tape.getName() + ", position " + tape.getPosition() + ": " + msg);
		this.tape = tape;
		this.initCause(initCause);
	}

	public Tape getTape() {
		return this.tape;
	}
	
}
