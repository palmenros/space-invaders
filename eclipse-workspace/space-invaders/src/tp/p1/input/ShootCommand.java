package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Shoot command
 */
public class ShootCommand extends SingleArgumentCommand {

	/**
	 * Constructs shoot command
	 */
	public ShootCommand()
	{
		super("shoot", "UCM-Ship launches a missile.");
	}
	
	/**
	 * Execute shoot command
	 */
	@Override
	public void execute(Controller controller) {
		if(controller.getGame().shoot()) {
			controller.tick();
		} else {
			System.out.println("Cannot shoot");
		}
	}

}
