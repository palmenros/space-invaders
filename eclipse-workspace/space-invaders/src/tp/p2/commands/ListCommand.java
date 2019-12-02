package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.game.*;
import tp.p2.gameObjects.DestroyerShip;
import tp.p2.gameObjects.ExplosiveShip;
import tp.p2.gameObjects.Ovni;
import tp.p2.gameObjects.RegularShip;
import tp.p2.gameObjects.UcmShip;

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
