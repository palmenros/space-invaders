package tp.p1.input;

/**
 * @author Martín Gómez y Pedro Palacios
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
