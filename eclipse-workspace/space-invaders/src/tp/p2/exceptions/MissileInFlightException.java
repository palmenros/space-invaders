package tp.p2.exceptions;

public class MissileInFlightException extends GameActionException {
	private static final long serialVersionUID = -2692410659316642880L;
	private final static String message = "Cannot fire missile: missile already exists on board";
	
	public MissileInFlightException() {
		super(message);
	}
}
