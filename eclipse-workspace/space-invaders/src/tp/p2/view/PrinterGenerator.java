package tp.p2.view;

import tp.p2.game.Game;

public class PrinterGenerator {

	/**
	 * List of available commands
	 */
	private static PrinterTypes[] availablePrinters = {
			PrinterTypes.BOARDPRINTER,
			PrinterTypes.SERIALIZER
	};	
	
	public static GamePrinter getPrinter(PrinterTypes type, Game game) {
		GamePrinter result = type.getObject();
		result.setGame(game);
		return result;	
	}
	
	public static String printerHelp()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		for(PrinterTypes printerType : availablePrinters)
		{
			stringBuilder.append(printerType.printerHelp() + "\n");
		}
		
		
		return stringBuilder.toString();
	}	
	
}
