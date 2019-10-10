package tp.p1.game;

/**
 * Bomb
 * @author Martín Gómez y Pedro Palacios
 */
public class Bomb extends GameObject {
	
	/**
	 * Owner which has shot this bomb
	 */
	private DestroyerShip owner;
	
	
	/**
	 * Create a new bomb
	 * @param r Row
	 * @param c Column
	 * @param owner Owner of this bomb
	 */
	public Bomb(int r, int c, DestroyerShip owner)
	{
		super(r, c);
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
	 * Returns harm that this bomb inflicts, which is the same of the owner
	 * @return Harm this bomb inflicts
	 */
	public int getHarm()
	{
		return owner.getHarm();
	}
	
	/**
	 * Update bomb
	 */
	public boolean update()
	{
		return move(1,0);
	}
}
