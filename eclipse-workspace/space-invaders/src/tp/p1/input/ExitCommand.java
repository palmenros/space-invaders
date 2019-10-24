package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.Game;

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
	public boolean execute(Game game, Controller controller) {
		controller.exit();
		return false;
	}

}
