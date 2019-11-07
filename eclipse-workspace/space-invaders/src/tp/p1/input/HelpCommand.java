package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.IPlayerController;

/**
 * Help command
 * @author Martín Gómez y Pedro Palacios
 */
public class HelpCommand extends NoParamsCommand {

	/**
	 * Constructs new help command
	 */
	public HelpCommand() {
		super("help", "h", "help", "Prints this help message.");
	}

	
	/**
	 * Execute help command
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) {
		controller.displayCommandHelp(CommandGenerator.commandHelp());
		return false;
	}

}
