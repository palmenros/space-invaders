package tp.p2.exceptions;

public class NoShockWaveException extends GameActionException {
	
	private static final long serialVersionUID = 8401613804133562395L;
	private final static String message = "Cannot release shockwave: no shockwave available";

	public NoShockWaveException() {
		super(message);
	}	
}
