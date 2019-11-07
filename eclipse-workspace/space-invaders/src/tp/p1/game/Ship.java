package tp.p1.game;

/**
 * Abstract class that represents a ship
 * @author Martín Gómez y Pedro Palacios
 */
abstract public class Ship extends GameObject {
	
	/**
	 * Construct ship at location with given health
	 * @param r Row
	 * @param c Column
	 * @param health Health
	 */
	public Ship(Game game, int r, int c, int health)
	{
		super(game, r, c, health);
	}
	
}
