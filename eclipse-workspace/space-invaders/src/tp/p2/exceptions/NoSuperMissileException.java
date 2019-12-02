package tp.p2.exceptions;

public class NoSuperMissileException extends GameActionException {

	private static final long serialVersionUID = 4964680721055024116L;
	private final static String message = "Cannot fire supermissile: You don't have any supermissile";

	public NoSuperMissileException() {
		super(message);
	}
}
