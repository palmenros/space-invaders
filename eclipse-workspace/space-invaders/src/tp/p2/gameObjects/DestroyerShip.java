package tp.p2.gameObjects;

import tp.p2.game.Game;

/**
 * Destroyer ship
 * @author Martín Gómez y Pedro Palacios 
 */
public class DestroyerShip extends AlienShip implements IExecuteRandomActions {

	/**
	 * Count of destroyer ships
	 */
	private static int destroyerCount = 0;
	
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
	
	private static final String SYMBOL = "D";
	
	/**
	 * Create a new destroyer at row, column
	 * @param r Row
	 * @param c Column
	 */
	public DestroyerShip(Game game, int r, int c)
	{
		super(game, r, c, HEALTH, SCORE, SYMBOL);
		destroyerCount++;
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
	
	@Override
	public void computerAction()
	{
		if(canShoot && IExecuteRandomActions.canGenerateRandomBomb(game))
		{
			canShoot = false;
			game.addObject(new Bomb(game, getRow(), getCol(), this));
		}
	}
	
	/**
	 * Update destroyer ship
	 */
	@Override
	public void update()
	{
		//Call parent update
		super.update();
	}
	
	/**
	 * Delete object
	 */
	public void destroy() {
		destroyerCount--;
		super.destroy();
	}
	
	/**
	 * Return count of destroyer ships that are still alive
	 * @return count of alive destroyer ships
	 */
	public static int getDestroyerShipCount() {
		return destroyerCount;
	}
}