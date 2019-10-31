package tp.p1.game;

/**
 * Destroyer ship
 * @author Martín Gómez y Pedro Palacios 
 */
public class DestroyerShip extends AlienShip {

	/**
	 * Default health of destroyer ship
	 */
	private static final int HEALTH = 1;

	/**
	 * Default harm of destroyer ship 
	 */
	private static final int HARM = 1;
	
	/**
	 * Default score points of destroyer ship
	 */
	private static final int SCORE = 10;
	
	/**
	 * Can shoot another bomb?
	 */
	private boolean canShoot = true;
	
	
	/**
	 * Create a new destroyer at row, column
	 * @param r Row
	 * @param c Column
	 */
	public DestroyerShip(Game game, int r, int c)
	{
		super(game, r, c, HEALTH, SCORE);
	}
	
	/**
	 * Return string representation of destroyer
	 */
	public String toString()
	{
		return "D[" + getHealth() + "]";
	}
	
	/**
	 * Get help message of destroyer 
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return EnemyShip.constructHelpMessage("Destroyer ship", SCORE, HEALTH, HARM);
	}
	
	/**
	 * Shoot a new bomb
	 * @return null if cannot shoot, bomb created if could shoot
	 */
	public Bomb shoot()
	{
		if(!canShoot) {
			return null;
		}
		canShoot = false;
		return new Bomb(game, getRow(), getCol(), this);
	}

	/**
	 * Reset value to be able to shoot again
	 */
	public void resetBomb()
	{
		canShoot = true;
	}
	
	/**
	 * Get destroyer harm
	 * @return Harm that destroyers inflict
	 */
	public int getHarm()
	{
		return HARM;
	}
	
	/**
	 * Update destroyer ship
	 */
	public boolean update()
	{
		//Do nothing
		return true;
	}
}
