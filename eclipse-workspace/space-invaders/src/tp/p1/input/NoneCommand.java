package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.IPlayerController;

/**
 * None command
 * @author Martín Gómez y Pedro Palacios
 */
public class NoneCommand extends NoParamsCommand {

	/**
	 * Construct new command
	 */
	public NoneCommand() {
		super("none", "n", "[none]", "Skips one cycle.");
	}

	/**
	 * Execute none command
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) {
		return true;
	}
	
	/**
	 *	Returns true if empty string or none command
	 * @throws IncorrectArgumentFormatException 
	 * @throws IncorrectArgumentNumberException 
	 */
	@Override
	public Command parse(String[] commandWords) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{
		if(commandWords.length == 1 && commandWords[0].equals(""))
		{
			return this;
		}
		
		return super.parse(commandWords);
	}

}
