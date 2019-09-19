package tp.p1.input;

/**
 * @author Martín Gómez y Pedro Palacios
 *
 */
public abstract class SingleArgumentCommand extends Command {

	
	/**
	 * Instantiates new Single Argument Command with key equal to first character
	 * @param name Command name
	 */
	public SingleArgumentCommand(String name)
	{
		super(name);
	}
	
	/**
	 * Instantiates new Single Argument Command
	 * 
	 * @param name	Command name
	 * @param key	Command abbreviation
	 */
	public SingleArgumentCommand(String name, String key)
	{
		super(name, key);
	}

	/**
	 *	Only execute if line is equal to name or key
	 */
	@Override
	public boolean tryExecute(String line) {
		
		String lower = line.toLowerCase();
		
		if( !lower.equals(name) && !lower.equals(key)) {return false;}
		
		execute();
		
		return true;
	}
	
	/**
	 *	Execute the specific command
	 */
	public abstract void execute();

}
