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
	}
	
	/**
	 * Update bomb
	 */
	public boolean update()
	{
		return move(1,0);
	}
}
