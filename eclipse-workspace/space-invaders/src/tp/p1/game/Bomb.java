package tp.p1.game;

/**
 * Bomb
 * @author Martín Gómez y Pedro Palacios
 */
public class Bomb extends Weapon {
	
	/**
	 * Owner which has shot this bomb
	 */
	private DestroyerShip owner;
	
	
	/**
	 * Create a new bomb
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param owner Owner of this bomb
	 */
	public Bomb(Game game, int r, int c, DestroyerShip owner)
	{
		super(game, r, c, owner.getHarm());
		this.owner = owner;
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
	public void update()
	{
		
		GameObject object = game.getAt(getRow() + 1, getCol());
		if(object != null && object.receiveBombAttack(getHarm())) {
			kill();
		}
	
		move(1,0);	
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
}
