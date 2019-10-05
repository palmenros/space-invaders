package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Reset command
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
