package tp.p1.input;

/**
 * @author Martín Gómez y Pedro Palacios
 * None command
 */
public class NoneCommand extends SingleArgumentCommand {

	/**
	 * Construct new command
	 */
	public NoneCommand() {
		super("none");
	}

	/**
	 * Execute none command
	 */
	@Override
	public void execute() {
		System.out.println("None command");
	}
	
	/**
	 *	Returns true if empty string or none command
	 */
	@Override
	public boolean tryExecute(String line)
	{
		return line.equals("") || super.tryExecute(line);
	}

}
