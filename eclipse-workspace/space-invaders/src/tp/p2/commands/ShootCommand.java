package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.exceptions.GameActionException;
import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;
import tp.p2.game.IPlayerController;

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
	
	private boolean superMissile = false;
	
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
				throw new IncorrectArgumentFormatException("You can only shoot a missile (default) or supermissile");
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
		
		try {
			playerController.shootMissile(superMissile);
			return true;
		} catch(GameActionException e) {
			throw new CommandExecuteException("Failed to shoot", e);			
		}
	}
}
