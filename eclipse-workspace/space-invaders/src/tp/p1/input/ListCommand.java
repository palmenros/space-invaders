package tp.p1.input;

import tp.p1.game.*;

/**
 * List command
 * @author Martín Gómez y Pedro Palacios
 */
public class ListCommand extends NoParamsCommand {

	/**
	 * Constructs new list command
	 */
	public ListCommand()
	{
		super("list", "l", "list", "Prints the list of available ships.");
	}
		
	/**
	 * Execute list command
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) {
		controller.displayShipList(new String[] {
				RegularShip.getHelpMessage(),
				DestroyerShip.getHelpMessage(),
				Ovni.getHelpMessage(),
				ExplosiveShip.getHelpMessage(),
				UcmShip.getHelpMessage()
		});
		
		return false;
	}

}
