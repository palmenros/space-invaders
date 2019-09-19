package tp.p1.input;

/**
 * @author Martín Gómez y Pedro Palacios
 * List command
 */
public class ListCommand extends SingleArgumentCommand {

	/**
	 * Constructs new list command
	 */
	public ListCommand()
	{
		super("list");
	}
		
	/**
	 * Execute list command
	 */
	@Override
	public void execute() {
		System.out.println("List");
	}

}
