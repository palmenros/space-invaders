package tp.p2.exceptions;

public class IncorrectArgumentFormatException extends CommandParseException {

	/**
	 * UUID for serialization
	 */
	private static final long serialVersionUID = -8442156013383136807L;
	
	private static final String message = "Incorrect argument format";
	
	public IncorrectArgumentFormatException(String string)
	{
		super(message + ": " + string);
	}	

	public IncorrectArgumentFormatException()
	{
		super(message);
	}	
}
