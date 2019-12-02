package tp.p2.view;

public class Serializer extends GamePrinter {

	public String toString() {
		return String.format("- Space Invaders v2.0 -\n\n%s", game.serialize());
	}
}
