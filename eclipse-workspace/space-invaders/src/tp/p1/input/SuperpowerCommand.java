package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Superpower command
 */
public class SuperpowerCommand extends SingleArgumentCommand {

	/**
	 * Instantiate superpower command class
	 */
	public SuperpowerCommand()
	{
		super("superpower", "p", "UCM-Ship releases a shock wave.");
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
