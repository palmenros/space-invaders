package tp.p1.view;

import tp.p1.game.Game;

public class PrinterGenerator {

	/**
	 * List of available commands
	 */
	private static PrinterTypes[] availablePrinters = {
			PrinterTypes.BOARDPRINTER,
			PrinterTypes.SERIALIZER
	};	
	
	/**
	 * Tries to parse words array into a command
	 * @param words Words to parse
	 * @return Command if found one that parses, null otherwise
	 * @throws IncorrectArgumentNumberException
	 * @throws IncorrectArgumentFormatException
	 */
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
