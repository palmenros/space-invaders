package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Represents an executable command by the game
 */
public abstract class Command {
		
	/**
	 *  Name of the command
	 */
	protected String name;
	
	/**
	 *  Alternative key of the command
	 */
	protected String key;
	
	
	/**
	 *  Help displayed with command
	 */
	protected String helpString;
	
	/**
	 * Creates a new command
	 * @param name	Name of the command
	 * @param key	Small abbreviation of the command
	 * @param help	Command help displayed
	 */
	public Command(String name, String key, String help) {
		this.name = name.toLowerCase();
		this.key = key.toLowerCase();
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
	 * @param controller TODO
	 * @return	Returns true if succeeded, false otherwise
	 */
	public abstract boolean tryExecute(String line, Controller controller);
		
}