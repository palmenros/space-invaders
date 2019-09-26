package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Martín Gómez y Pedro Palacios
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
		System.out.println("Shoot");
	}

}
