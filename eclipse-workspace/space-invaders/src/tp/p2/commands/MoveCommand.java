package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.exceptions.GameActionException;
import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;
import tp.p2.game.*;

/**
 * Represents specific Move Command
 * @author Martín Gómez y Pedro Palacios
 */
public class MoveCommand extends Command {

	/**
	 * Constructs the key and the command of move using the first letter as key
	 */
	public MoveCommand() {
		super("move", "m", "move <left|right><1|2>", "Moves UCM-Ship to the indicated direction.");
	}
	
	/**
	 * Number of steps to move
	 */
	private int number;
	
	/**
	 *	Direction where to move
	 */
	private String direction;
	
	
	@Override
	public Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{	
		//Check the name of the command
		if( words.length < 1 || !matchCommandName(words[0])) {
			return null;
		}
		
		if(words.length != 3) {
			throw new IncorrectArgumentNumberException(3);
		}
		
		//Check the direction
		if(!words[1].equals("right") && !words[1].equals("left")) {
			throw new IncorrectArgumentFormatException("Move direction must be left or right");
		}
		
		//Check the number
		if(!words[2].equals("1") && !words[2].equals("2")) {
			throw new IncorrectArgumentFormatException("Move amount can only be 1 or 2");
		}
		
		//Execute the command
		number = Integer.parseInt(words[2]);
		direction = words[1];	
		
		return this;
	}
	
	/**
	 * Executes move command
	 * @param controller Controller
	 * @throws CommandExecuteException 
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException
	{
		int dc = number * ( direction.equals("right") ? 1 : -1 );
		
		try {
			playerController.move(dc);
			return true;
		} catch(GameActionException e) {
			throw new CommandExecuteException("Failed to move", e);			
		}
	}
}
