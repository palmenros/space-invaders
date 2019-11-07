package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.IPlayerController;

/**
 * Shoot command
 * @author Martín Gómez y Pedro Palacios
 */
public class ShootCommand extends NoParamsCommand {

	/**
	 * Constructs shoot command
	 */
	public ShootCommand()
	{
		super("shoot", "s", "shoot", "UCM-Ship launches a missile.");
	}
	
	/**
	 * Execute shoot command
	 * @throws CommandExecuteException 
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException {
		if(playerController.shootMissile()) {
			return true;
		} else {
			throw new CommandExecuteException("Cannot shoot");
		}
	}
}
