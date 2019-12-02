package tp.p2.exceptions;

public class NotEnoughScoreException extends GameActionException {

	private static final long serialVersionUID = -4992898253937868633L;
	private final static String message = "Cannot buy supermissile: not enough score";

	public NotEnoughScoreException() {
		super(message);
	}	
}
