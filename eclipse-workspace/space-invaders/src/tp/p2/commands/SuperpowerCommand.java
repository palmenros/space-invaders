package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.exceptions.GameActionException;
import tp.p2.game.IPlayerController;

/**
 * Superpower command
 * @author Martín Gómez y Pedro Palacios
 */
public class SuperpowerCommand extends NoParamsCommand {

	/**
	 * Instantiate superpower command class
	 */
	public SuperpowerCommand()
	{
		super("shockwave", "w", "shockwave", "UCM-Ship releases a shock wave.");
	}
	
	/**
	 * Execute superpower command
	 * @throws CommandExecuteException 
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException {
		
		try {
			playerController.shockWave();
			return true;
		} catch(GameActionException e) {
			throw new CommandExecuteException("Failed to use ShockWave", e);
		}

	}

}
