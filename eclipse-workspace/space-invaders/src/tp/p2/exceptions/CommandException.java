package tp.p2.exceptions;

public class CommandException extends Exception {

	/**
	 * UUID for serialization
	 */
	private static final long serialVersionUID = -8163907191146156440L;

	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
