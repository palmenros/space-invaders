package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.exceptions.CommandExecuteException;
import tp.p2.game.IPlayerController;
import tp.p2.view.PrinterGenerator;
import tp.p2.view.PrinterTypes;

public class StringifyCommand extends NoParamsCommand {

	public StringifyCommand()
	{
		super("stringify", "g", "stringify", "Prints a serialized version of the game.");
	}
	
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) throws CommandExecuteException {
		
		controller.displayStringified();
		return false;
	}

}
