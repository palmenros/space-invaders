package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Martín Gómez y Pedro Palacios
 * Exit command
 */
public class ExitCommand extends SingleArgumentCommand {

	/**
	 * Constructs new exit command
	 */
	public ExitCommand() {
		super("exit", "Terminates the program.");
	}

	/**
	 *	Executes exit command
	 */
	@Override
	public void execute(Controller controller) {
		System.out.println("Game Over");
		controller.exit();
	}

}
