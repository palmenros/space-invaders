package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.game.IPlayerController;

/**
 * Exit command
 * @author Martín Gómez y Pedro Palacios
 */
public class ExitCommand extends NoParamsCommand {

	/**
	 * Constructs new exit command
	 */
	public ExitCommand() {
		super("exit", "e", "exit", "Terminates the program.");
	}

	/**
	 *	Executes exit command
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) {
		controller.exit();
		return false;
	}

}
