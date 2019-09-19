package tp.p1.input;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * Shoot command
 */
public class ShootCommand extends SingleArgumentCommand {

	/**
	 * Constructs shoot command
	 */
	public ShootCommand()
	{
		super("shoot");
	}
	
	/**
	 * Execute shoot command
	 */
	@Override
	public void execute() {
		System.out.println("Shoot");
	}

}
