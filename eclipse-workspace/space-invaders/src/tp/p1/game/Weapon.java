package tp.p1.game;

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
	
	/**
	 * Construct a new weapon at given game, position and harm
	 * @param game
	 * @param r
	 * @param c
	 * @param harm
	 */
	public Weapon(Game game, int r, int c, int harm)
	{
		super(game, r, c);
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
