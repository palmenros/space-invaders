package tp.p1.input;

import tp.p1.game.Controller;

/**
 * Reset command
 * @author Martín Gómez y Pedro Palacios
 */
public class ResetCommand extends SingleArgumentCommand {

	/**
	 * Constructs reset command
	 */
	public ResetCommand() {
		super("reset", "Starts a new game.");
	}

	/**
	 * Executes reset command
	 */
	@Override
	public void execute(Controller controller) {
		System.out.println("Game Reset!");
		controller.getGame().reset();
	}

}
