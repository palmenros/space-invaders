package tp.p1.commands;

import tp.p1.controller.Controller;
import tp.p1.game.IPlayerController;
import tp.p1.view.PrinterGenerator;

public class ListPrintersCommand extends NoParamsCommand {

	/**
	 * Constructs new help command
	 */
	public ListPrintersCommand() {
		super("listPrinters", "p", "listPrinters", "List all availiable printers.");
	}

	
	/**
	 * Execute help command
	 */
	@Override
	public boolean execute(IPlayerController playerController, Controller controller) {
		controller.displayText(PrinterGenerator.printerHelp());
		return false;
	}


}
