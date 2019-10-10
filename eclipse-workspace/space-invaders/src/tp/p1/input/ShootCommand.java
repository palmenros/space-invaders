package tp.p1.input;

import tp.p1.game.Controller;

/**
 * Shoot command
 * @author Martín Gómez y Pedro Palacios
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
