package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.exceptions.GameActionException;
import tp.p2.exceptions.IncorrectArgumentFormatException;
import tp.p2.exceptions.IncorrectArgumentNumberException;
import tp.p2.game.IPlayerController;

public class BuyCommand extends Command {

	private static int SUPERMISSILE_COST = 20;
	
	/**
	 * Constructs the key and the command of move using the first letter as key
	 */
	public BuyCommand() {
		super("buy", "b", "buy supermissile", "Buy supermissile for 20 points.");
	}
	
	@Override
	public Command parse(String[] words) throws IncorrectArgumentNumberException, IncorrectArgumentFormatException
	{	
		//Check the name of the command
		if( words.length < 1 || !words[0].equals(name) && !words[0].equals(shortcut)) {
			return null;
		}
		
		if(words.length != 2) {
			throw new IncorrectArgumentNumberException(2);
		}
		
		if(!words[1].equals("supermissile")) {
			throw new IncorrectArgumentFormatException("You can only buy a supermissile");
		}		
		
		return this;
	}
	
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException
	{	
		try {
			playerController.buySuperMissile(SUPERMISSILE_COST);
			return true;
		} catch(GameActionException e) {
			throw new CommandExecuteException("Failed to buy supermissile", e);			
		}
	}

}
