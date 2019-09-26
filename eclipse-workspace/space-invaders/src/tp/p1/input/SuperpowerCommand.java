package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Martín Gómez y Pedro Palacios
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
		
		System.out.println("Superpower");
	
	}

}
