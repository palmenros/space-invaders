package tp.p1.commands;

import tp.p1.controller.Controller;
import tp.p1.exceptions.CommandExecuteException;
import tp.p1.game.IPlayerController;
import tp.p1.view.PrinterGenerator;
import tp.p1.view.PrinterTypes;

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
