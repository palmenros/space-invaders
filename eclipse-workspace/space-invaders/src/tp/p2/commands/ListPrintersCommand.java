package tp.p2.commands;

import tp.p2.controller.Controller;
import tp.p2.game.IPlayerController;
import tp.p2.view.PrinterGenerator;

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
