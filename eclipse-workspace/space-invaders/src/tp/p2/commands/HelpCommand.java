package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.game.IPlayerController;

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
		controller.displayText(CommandGenerator.commandHelp());
		return false;
	}

}
