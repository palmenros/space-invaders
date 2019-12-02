package tp.p2.view;

public class Serializer extends GamePrinter {
	
	private static final String HEADER = "- Space Invaders v2.0 -";
	
	public String toString() {
		return String.format(HEADER + "\n\n%s", game.serialize());
	}

	public static String getHeader() {
		return HEADER;
	}
}
