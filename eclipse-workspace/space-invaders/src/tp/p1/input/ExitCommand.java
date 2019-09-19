package tp.p1.input;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Exit command
 */
public class ExitCommand extends SingleArgumentCommand {

	/**
	 * Constructs new exit command
	 */
	public ExitCommand() {
		super("exit");
	}

	/**
	 *	Executes exit command
	 */
	@Override
	public void execute() {
		System.out.println("Exit");
	}

}
