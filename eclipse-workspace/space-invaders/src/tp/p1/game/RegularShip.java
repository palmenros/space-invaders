package tp.p1.game;

/**
 * Class that represents a common ship that cannot shoot
 * @author Martín Gómez y Pedro Palacios
 */
public class RegularShip extends EnemyShip {
	
	/**
	 * Default health
	 */
	private static final int HEALTH = 2;
	
	/**
	 * Default score
	 */
	private static final int SCORE = 5;
	
	/**
	 * Create regular ship at location
	 * @param r Row
	 * @param c Column
	 */
	public RegularShip(int r, int c)
	{
		super(r, c, HEALTH, SCORE);
	}
	
	/**
	 * Return string representation of regular ship
	 */
	public String toString()
	{
		return "C[" + getHealth() + "]";
	}
	
	/**
	 * Return help message
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return EnemyShip.constructHelpMessage("Regular ship", SCORE, HEALTH, 0);
	}
	
	/**
	 * Update ship
	 */
	public boolean update()
	{
		//Will be always alive until killed by missile
		//Movement is handled by Game
		return true;
	}
	
}
