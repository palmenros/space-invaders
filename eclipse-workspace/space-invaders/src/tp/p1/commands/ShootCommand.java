package tp.p1.commands;

import tp.p1.controller.Controller;
import tp.p1.exceptions.CommandExecuteException;
import tp.p1.exceptions.IncorrectArgumentFormatException;
import tp.p1.exceptions.IncorrectArgumentNumberException;
import tp.p1.game.IPlayerController;

/**
 * Shoot command
 * @author Martín Gómez y Pedro Palacios
 */
public class ShootCommand extends Command {

	/**
	 * Constructs shoot command
	 */
	public ShootCommand()
	{
		super("shoot", "s", "shoot <supermissile>", "UCM-Ship launches a missile.");
	}
	
	boolean superMissile = false;
	
	public Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{
		if(words.length < 1) {
			return null;
		}
		
		if (!matchCommandName(words[0])) {
			return null;
		}
		
		if(words.length == 1) {
			superMissile = false;
		} else if (words.length == 2) {
			if(words[1].equals("supermissile")) {
				superMissile = true;
			} else {
				throw new IncorrectArgumentFormatException();
			}
		} else if (words.length > 2) {
			throw new IncorrectArgumentNumberException(2);
		}

		return this;
	}
	
	/**
	 * Execute shoot command
	 * @throws CommandExecuteException 
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException {
		//TODO: Better error messages
		if(playerController.shootMissile(superMissile)) {
			return true;
		} else {
			throw new CommandExecuteException("Cannot shoot");
		}
	}
}
