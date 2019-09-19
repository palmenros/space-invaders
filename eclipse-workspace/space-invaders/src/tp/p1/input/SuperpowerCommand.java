package tp.p1.input;

/**
 * @author Mart�n G�mez y Pedro Palacios
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
