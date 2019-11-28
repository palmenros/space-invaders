package tp.p1.gameObjects;

import tp.p1.game.Game;

/**
 * Class which represents any kind of weapon
 * @author Martín Gómez y Pedro Palacios
 *
 */
public abstract class Weapon extends GameObject {
	
	
	/**
	 * Harm which the weapon produces when it attacks
	 */
	protected int harm;
	
	private static int HEALTH = 1;
	
	/**
	 * Construct a new weapon at given game, position and harm
	 * @param game
	 * @param r
	 * @param c
	 * @param harm
	 */
	public Weapon(Game game, int r, int c, int harm, String symbol)
	{
		super(game, r, c, HEALTH, symbol);
		this.harm = harm;
	}
	
	public abstract void update();
	
	/**
	 * Get harm
	 * @return harm
	 */
	public int getHarm()
	{
		return harm;
	}
}
