package tp.p2.gameObjects;

import tp.p2.game.Game;

/**
 * Class that represents a missile thrown by the UCM-Ship
 * @author Martín Gómez y Pedro Palacios
 */
public class UCMMissile extends Weapon {

	/**
	 * Default harm that the missile will do to enemies
	 */
	public static final int DEFAULT_HARM = 1;
	
	private static final String SYMBOL = "M";

	/**
	 * Construct a new missile at position with default harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm which the missile produces
	 */
	public UCMMissile(Game game, int r, int c)
	{
		this(game, r, c, DEFAULT_HARM, SYMBOL);
	}
	
	/**
	 * Construct a new missile at position with given harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm
	 */
	public UCMMissile(Game game, int r, int c, int harm, String symbol)
	{
		super(game, r, c, harm, symbol);
	}

	public UCMMissile() {
		this(null, 0, 0);
	}

	/**
	 * Get string representation of missile
	 */
	public String toString()
	{
		return "oo";
	}
		
	/**
	 * Update missile position
	 */
	@Override
	public void update()
	{
		move(-1,0);
	}
	
	@Override
	public boolean performAttack(GameObject other)
	{
		boolean result = isAt(other) && other.receiveMissileAttack(getHarm());
		if(result) {
			kill();
		}
		
		return result;
	}
	
	/**
	 *	Receive bomb attack
	 *  @param dmg Damage to receive
	 *  @return True if affected
	 */
	@Override
	public boolean receiveBombAttack(int dmg) {
		damage(dmg);
		return true;
	}
	
	/**
	 * Called on destroy
	 */
	@Override
	public void destroy()
	{
		game.enableMissile();
		super.destroy();
	}
		
}
