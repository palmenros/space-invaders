package tp.p1.game;

/**
 * Abstract class that represents a ship
 * @author Martín Gómez y Pedro Palacios
 */
abstract public class Ship extends GameObject {
	
	/**
	 *  Health of the ship. Always greater or equals to 0.
	 */
	private int health;
	
	/**
	 * Construct ship at location with given health
	 * @param r Row
	 * @param c Column
	 * @param health Health
	 */
	public Ship(Game game, int r, int c, int health)
	{
		super(game, r, c);
		this.health = Math.max(0, health);
	}
	
	/**
	 * Damage the ship
	 * @param damagePoints Health to remove
	 * @return True if the ship is still alive, false otherwise
	 */
	public boolean damage(int damagePoints)
	{
		health = Math.max(0, health - damagePoints);
		return health > 0;
	}
	
	/**
	 * Get health
	 * @return Health
	 */
	public int getHealth()
	{
		return health;
	}
	
}
