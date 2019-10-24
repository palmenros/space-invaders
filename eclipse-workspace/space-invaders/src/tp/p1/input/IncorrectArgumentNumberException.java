package tp.p1.input;

public class IncorrectArgumentNumberException extends CommandParseException {

	/**
	 * UUID for serialization
	 */
	private static final long serialVersionUID = -7723284838152030354L;
	
	private static final String message = "Incorrect number of arguments, expected ";
	
	public IncorrectArgumentNumberException(int expectedNumberOfArguments)
	{
		super(message + expectedNumberOfArguments + " arguments");
	}	
}
