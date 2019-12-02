package tp.p2.view;

public enum PrinterTypes {
	BOARDPRINTER("boardprinter", "prints the game formatted as a board", new FormattedPrinter()),
	SERIALIZER("serializer", "prints the game as plain text", new Serializer());

	private String name;
	private String text;
	private GamePrinter printerObject;
	
	private PrinterTypes(String name, String text, GamePrinter printer) {
		this.name = name;
		this.text = text;
		printerObject = printer;
	}
	
	public String printerHelp() {
		return name + ": " + text;
	}
	
	public GamePrinter getObject() {
		return printerObject;
	}
}
