package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.IPlayerController;

/**
 * Reset command
 * @author Martín Gómez y Pedro Palacios
 */
public class ResetCommand extends NoParamsCommand {

	/**
	 * Constructs reset command
	 */
	public ResetCommand() {
		super("reset", "r", "reset", "Starts a new game.");
	}

	/**
	 * Executes reset command
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) {
		controller.resetGame();
		return false;
	}

}
