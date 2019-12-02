package tp.p2.exceptions;

public class OffWorldException extends GameActionException {
	
	private static final long serialVersionUID = 8119460787093980000L;
	private final static String message = "Cannot move: ship is too close to border";

	public OffWorldException() {
		super(message);
	}	
}
