package tp.p1.util;
import tp.p1.game.Bomb;

public interface BombCallback {
	
	/**
	 * Called for every bomb in bomb list
	 * @param bomb Bomb
	 * @return True if should be removed
	 */
	boolean shouldRemove(Bomb bomb);
}
