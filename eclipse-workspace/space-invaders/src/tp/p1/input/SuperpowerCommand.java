package tp.p1.input;

import tp.p1.game.Controller;

/**
 * Superpower command
 * @author Martín Gómez y Pedro Palacios
 */
public class SuperpowerCommand extends SingleArgumentCommand {

	/**
	 * Instantiate superpower command class
	 */
	public SuperpowerCommand()
	{
		super("shockwave", "w", "UCM-Ship releases a shock wave.");
	}
	
	/**
	 * Execute superpower command
	 */
	@Override
	public void execute(Controller controller) {
		
		if(controller.getGame().useSuperPower()) {
			controller.tick();
		} else {
			System.out.println("No superpower available");
		}

	}

}
