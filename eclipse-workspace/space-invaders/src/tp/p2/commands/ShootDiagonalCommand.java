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
public class ShootDiagonalCommand extends Command {

	/**
	 * Constructs shoot command
	 */
	public ShootDiagonalCommand()
	{
		super("shootdiagonal", "sd", "shootdiagonal", "UCM-Ship launches a diagonal missile.");
	}
	
	public Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{
		if(words.length < 1) {
			return null;
		}
		
		if (!matchCommandName(words[0])) {
			return null;
		}
		
		if(words.length != 1) 
		{
			throw new IncorrectArgumentNumberException(1);
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
			playerController.shootDiagonalMissile();
			return true;
		} catch(GameActionException e) {
			throw new CommandExecuteException("Failed to shoot", e);			
		}
	}
}
