package tp.p1.input;

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
		super("superpower", "p");
	}
	
	/**
	 * Execute superpower command
	 */
	@Override
	public void execute() {
		
		System.out.println("Superpower");
	
	}

}
