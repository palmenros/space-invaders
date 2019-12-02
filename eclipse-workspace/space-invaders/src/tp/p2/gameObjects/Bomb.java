package tp.p2.gameObjects;

import tp.p2.game.Game;

/**
 * Bomb
 * @author Martín Gómez y Pedro Palacios
 */
public class Bomb extends Weapon {
	
	/**
	 * Owner which has shot this bomb
	 */
	private DestroyerShip owner;
	
	private static final String SYMBOL = "B";
	
	/**
	 * Create a new bomb
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param owner Owner of this bomb
	 */
	public Bomb(Game game, int r, int c, DestroyerShip owner)
	{
		super(game, r, c, DestroyerShip.getHarm(), SYMBOL);
		this.owner = owner;
	}
	
	public Bomb() {
		this(null, 0, 0, null);
	}

	/**
	 * String representation of the bomb
	 */
	public String toString()
	{
		return ".";
	}
	
	/**
	 * When destroyed, owner can shoot another bomb again
	 */
	public void destroy()
	{
		owner.resetBomb();
		super.destroy();
	}
	
	/**
	 * Update bomb
	 */
	@Override
	public void update()
	{		
		move(1,0);	
	}
	
	@Override
	public boolean performAttack(GameObject other)
	{
		boolean result = isAt(other) && other.receiveBombAttack(getHarm());
		if(result) {
			kill();
		}
		
		return result;
	}
		
	/**
	 *	Receive missile attack
	 *  @param dmg Damage to receive
	 *  @return True if affected
	 */
	@Override
	public boolean receiveMissileAttack(int dmg) {
		damage(dmg);
		return true;
	}
	
	// call to getLabel assumes owner already serialized (so label already generated)
	public String generateSerialRef() {
		return labelRefSeparator + owner.getLabel();
	}

	@Override
	public String serialize() {
		return super.serialize() + generateSerialRef();
	}
}
