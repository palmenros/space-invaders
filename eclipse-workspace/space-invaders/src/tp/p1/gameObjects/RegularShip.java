package tp.p1.gameObjects;

import tp.p1.game.Game;

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
	protected static final int HEALTH = 2;
	
	/**
	 * Default score
	 */
	protected static final int SCORE = 5;
	
	private static final String SYMBOL = "R";
	
	/**
	 * Create regular ship at location
	 * @param r Row
	 * @param c Column
	 */
	public RegularShip(Game game, int r, int c)
	{
		this(game, r, c, HEALTH, SCORE);
	}
	
	protected RegularShip(Game game, int r, int c, int health, int score)
	{
		super(game, r, c, health, score, SYMBOL);
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
	
	@Override
	public void computerAction()
	{
		super.computerAction();
		if (IExecuteRandomActions.shouldBecomeExplosiveShip(game))
		{
			game.addObject(new ExplosiveShip(game, getRow(), getCol(), getHealth(), getScore()));
			kill();
		}
	}
	
}
