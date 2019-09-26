package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Martín Gómez y Pedro Palacios
 *
 */
public abstract class SingleArgumentCommand extends Command {

	
	/**
	 * Instantiates new Single Argument Command with key equal to first character
	 * @param name Command name
	 * @param help 	Command help string
	 */
	public SingleArgumentCommand(String name, String help)
	{
		super(name, help);
	}
	
	/**
	 * Instantiates new Single Argument Command
	 * 
	 * @param name	Command name
	 * @param key	Command abbreviation
	 * @param help 	Command help string
	 */
	public SingleArgumentCommand(String name, String key, String help)
	{
		super(name, key, help);
	}

	/**
	 *	Only execute if line is equal to name or key
	 */
	@Override
	public boolean tryExecute(String line, Controller controller) {
		
		String lower = line.toLowerCase();
		
		if( !lower.equals(name) && !lower.equals(key)) {return false;}
		
		execute(controller);
		
		return true;
	}
	
	/**
	 *	Return help from name and help string
	 */
	public String getHelp()
	{
		return name + ": " + helpString;
	}
	
	/**
	 *	Execute the specific command
	 * @param controller TODO
	 */
	public abstract void execute(Controller controller);

}
