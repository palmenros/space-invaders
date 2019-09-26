package tp.p1.input;

import tp.p1.game.Controller;

/**
 * @author Martín Gómez y Pedro Palacios
 * Represents specific Move Command
 */
public class MoveCommand extends Command {

	/**
	 * Constructs the key and the command of move using the first letter as key
	 */
	public MoveCommand() {
		super("move", "<left|right><1|2>: Moves UCM-Ship to the indicated direction.");
	}
	
	/**
	 * Number of steps to move
	 */
	private int number;
	
	/**
	 *	Direction where to move
	 */
	private String direction;
	
	/**
	 *	Try executing move command and check data
	 */
	@Override
	public boolean tryExecute(String line, Controller controller) {
		String[] parts = line.toLowerCase().split(" ");
		
		//Assert there is a command and two arguments
		if(parts.length != 3) {return false;}
		
		//Check the name of the command
		if(!parts[0].equals(name) && !parts[0].equals(key)) {return false;}
		
		//Check the direction
		if(!parts[1].equals("right") && !parts[1].equals("left")) {return false;}
		
		//Check the number
		if(!parts[2].equals("1") && !parts[2].equals("2")) { return false;}
		
		//Execute the command
		number = Integer.parseInt(parts[2]);
		direction = parts[1];	
		execute();
		
		return true;
	}
	
	/**
	 * Executes move command
	 */
	private void execute()
	{
		System.out.println("Move " + direction + " " + number);
	}
	
	
	/**
	 *	Return move help string
	 */
	public String getHelp()
	{
		return name + " " + helpString;
	}
	
}
