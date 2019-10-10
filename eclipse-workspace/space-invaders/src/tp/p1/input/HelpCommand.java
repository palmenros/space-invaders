package tp.p1.input;

import tp.p1.game.Controller;

/**
 * Help command
 * @author Martín Gómez y Pedro Palacios
 */
public class HelpCommand extends SingleArgumentCommand {

	/**
	 * Constructs new help command
	 */
	public HelpCommand() {
		super("help", "Prints this help message.");
	}

	
	/**
	 * Execute help command
	 */
	@Override
	public void execute(Controller controller) {
		for(Command c : controller.getCommandList().getCommands())
		{
			System.out.println(c.getHelp());
		}
	}

}
