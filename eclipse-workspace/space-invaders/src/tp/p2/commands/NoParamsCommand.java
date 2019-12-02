package tp.p2.commands;

import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;

/**
 * Single Argument Command
 * @author Martín Gómez y Pedro Palacios
 */
public abstract class NoParamsCommand extends Command {

	
	/**
	 * Instantiates new Single Argument Command with key equal to first character
	 * @param name Command name
	 * @param help 	Command help string
	 */
	public NoParamsCommand(String name, String shortcut, String details, String help)
	{
		super(name, shortcut, details ,help);
	}
		
	public Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{
		if(words.length < 1) {
			return null;
		}
		
		if (!matchCommandName(words[0])) {
			return null;
		}
		
		if(words.length > 1) {
			throw new IncorrectArgumentNumberException(1);
		}
		
		return this;
	}

}
