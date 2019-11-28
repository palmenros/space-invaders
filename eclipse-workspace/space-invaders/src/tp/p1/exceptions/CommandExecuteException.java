package tp.p1.exceptions;

public class CommandExecuteException extends CommandException {

	/**
	 *  UUID for serialization
	 */
	private static final long serialVersionUID = 6364447106262721065L;

	public CommandExecuteException(String message) {
		super(message);
	}

}