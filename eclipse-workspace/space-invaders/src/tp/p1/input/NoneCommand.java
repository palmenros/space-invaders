package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * None command
 */
public class NoneCommand extends SingleArgumentCommand {

	/**
	 * Construct new command
	 */
	public NoneCommand() {
		super("none", "Skips one cycle.");
	}

	/**
	 * Execute none command
	 */
	@Override
	public void execute(Controller controller) {
		controller.tick();
	}
	
	/**
	 *	Returns true if empty string or none command
	 */
	@Override
	public boolean tryExecute(String line, Controller controller)
	{
		if(line.equals(""))
		{
			execute(controller);
			return true;
		}
		
		return super.tryExecute(line, controller);
	}
	
	/**
	 *	Return none command help string
	 */
	public String getHelp()
	{
		return "[" + name + "]: " + helpString;
	}
}
