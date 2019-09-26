package tp.p1.input;

import tp.p1.game.Controller;

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
		super("list", "Prints the list of available ships.");
	}
		
	/**
	 * Execute list command
	 */
	@Override
	public void execute(Controller controller) {
		System.out.println("List");
	}

}
