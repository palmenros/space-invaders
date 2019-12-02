package tp.p2.exceptions;

public class UnknownCommandException extends CommandException {

	/**
	 * UUID for serialization
	 */
	private static final long serialVersionUID = -6641265292647375431L;
	
	private static final String message = "Unknown command";
	
	public UnknownCommandException()
	{
		super(message);
	}
}
