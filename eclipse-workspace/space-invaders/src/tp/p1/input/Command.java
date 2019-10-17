package tp.p1.input;

import tp.p1.game.Controller;

/**
 * Represents an executable command by the game
 * @author Martín Gómez y Pedro Palacios
 */
public abstract class Command {
		
	/**
	 *  Name of the command
	 */
	protected final String name;
	
	/**
	 *  Alternative key of the command
	 */
	protected final String shortcut;
	
	
	/**
	 *  Help displayed with command
	 */
	protected String helpString;
	
	/**
	 * Creates a new command
	 * @param name	Name of the command
	 * @param shortcut	Small abbreviation of the command
	 * @param help	Command help displayed
	 */
	public Command(String name, String shortcut, String help) {
		this.name = name.toLowerCase();
		this.shortcut = shortcut.toLowerCase();
		this.helpString = help;
	}
	
	/**
	 * By default, the abbreviation will be the first character of the name
	 * @param name	Name of the command
	 * @param help Command help displayed
	 */
	public Command(String name, String help) {
		this(name, name.substring(0, 1), help);
	}
	
	
	/**
	 * @return Help string of this command
	 */
	public String getHelp()
	{
		return helpString;
	}
	
	/**
	 * Try executing this command with the given line
	 * @param line	Command line to try to parse
	 * @param controller Controller
	 * @return	Returns true if succeeded, false otherwise
	 */
	public abstract boolean tryExecute(String line, Controller controller);
		
}
