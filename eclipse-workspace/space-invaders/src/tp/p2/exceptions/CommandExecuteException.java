package tp.p2.exceptions;

public class CommandExecuteException extends CommandException {

	/**
	 *  UUID for serialization
	 */
	private static final long serialVersionUID = 6364447106262721065L;

	public CommandExecuteException(String message) {
		super(message);
	}
	
	public CommandExecuteException(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public String getMessage() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(super.getMessage());
		
		Throwable cause = getCause();
		if(cause != null) {
			stringBuilder.append("\nCause of exception: \n");
			stringBuilder.append(cause.getClass().getTypeName() + ": " + cause.getMessage());
		}
		return stringBuilder.toString();
	}

}