package tp.p1.input;

/**
 * @author Martín Gómez y Pedro Palacios
 * Help command
 */
public class HelpCommand extends SingleArgumentCommand {

	/**
	 * Constructs new help command
	 */
	public HelpCommand() {
		super("help");
	}

	
	/**
	 * Execute help command
	 */
	@Override
	public void execute() {
		System.out.println("Help command");
	}

}
