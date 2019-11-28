package tp.p1.commands;

import tp.p1.controller.Controller;
import tp.p1.exceptions.CommandExecuteException;
import tp.p1.game.IPlayerController;

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
		
		if(playerController.shockWave()) {
			return true;
		} else {
			throw new CommandExecuteException("No shock wave available");
		}

	}

}