package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.Game;

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
	public boolean execute(Game game, Controller controller) throws CommandExecuteException {
		if(game.shoot()) {
			return true;
		} else {
			throw new CommandExecuteException("Cannot shoot");
		}
	}
}
