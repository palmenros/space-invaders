package tp.p1.input;

import tp.p1.game.*;

/**
 * @author Mart�n G�mez y Pedro Palacios
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
		System.out.println(RegularShip.getHelpMessage());
		System.out.println(DestroyerShip.getHelpMessage());
		System.out.println(Ovni.getHelpMessage());
		System.out.println(UcmShip.getHelpMessage());
	}

}
