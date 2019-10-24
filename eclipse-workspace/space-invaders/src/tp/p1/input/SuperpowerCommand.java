package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.Game;

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
	public boolean execute(Game game, Controller controller) throws CommandExecuteException {
		
		if(game.useSuperPower()) {
			return true;
		} else {
			throw new CommandExecuteException("No shock wave available");
		}

	}

}
