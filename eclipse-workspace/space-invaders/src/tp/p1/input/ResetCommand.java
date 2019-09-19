package tp.p1.input;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Reset command
 */
public class ResetCommand extends SingleArgumentCommand {

	/**
	 * Constructs reset command
	 */
	public ResetCommand() {
		super("reset");
	}

	/**
	 * Executes reset command
	 */
	@Override
	public void execute() {
		System.out.println("Reset");
	}

}
