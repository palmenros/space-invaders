package tp.p1.input;

import tp.p1.game.Controller;
import tp.p1.game.IPlayerController;

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
	
	
	private final String details;
	
	/**
	 *  Help displayed with command
	 */
	private final String help;
	
	
	/**
	 * Creates a new command
	 * @param name	Name of the command
	 * @param shortcut	Small abbreviation of the command
	 * @param help	Command help displayed
	 */
	public Command(String name, String shortcut, String details, String help) {
		this.name = name.toLowerCase();
		this.shortcut = shortcut.toLowerCase();
		this.details = details;
		this.help = help;
	}	
	
	/**
	 * @return Help string of this command
	 */
	public String getHelpText()
	{
		return details + " : " + help;	
	}
		
	public abstract boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException;
	public abstract Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException;

	
	/**
	 * @param name Name to match against
	 * @return True if name matches shortcut or name
	 */
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) ||
		this.name.equalsIgnoreCase(name);
	}
	
}
