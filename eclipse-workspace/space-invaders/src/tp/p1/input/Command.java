package tp.p1.input;

/**
 * @author Martín Gómez y Pedro Palacios
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
	 * Creates a new command
	 * @param name	Name of the command
	 * @param key	Small abbreviation of the command
	 */
	public Command(String name, String key) {
		this.name = name.toLowerCase();
		this.key = key.toLowerCase();
	}
	
	/**
	 * By default, the abbreviation will be the first character of the name
	 * @param name	Name of the command
	 */
	public Command(String name) {
		this(name, name.substring(0, 1));
	}
	
	/**
	 * Try executing this command with the given line
	 * @param line	Command line to try to parse
	 * @return	Returns true if succeeded, false otherwise
	 */
	public abstract boolean tryExecute(String line);
		
}
