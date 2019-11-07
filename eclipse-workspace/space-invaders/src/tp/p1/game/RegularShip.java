package tp.p1.game;

/**
 * Class that represents a common ship that cannot shoot
 * @author Martín Gómez y Pedro Palacios
 */
public class RegularShip extends AlienShip {
	
	/**
	 * Count of regular ships
	 */
	private static int regularCount = 0;
	
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
	public RegularShip(Game game, int r, int c)
	{
		super(game, r, c, HEALTH, SCORE);
		regularCount++;
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
	public void update()
	{
		//Call parent update
		super.update();
	}
	
	
	/**
	 * Delete object
	 */
	public void destroy() {
		regularCount--;
		super.destroy();
	}
	
	/**
	 * Return count of regular ships that are still alive
	 * @return count of alive regular ships
	 */
	public static int getRegularShipCount() {
		return regularCount;
	}
	
}
