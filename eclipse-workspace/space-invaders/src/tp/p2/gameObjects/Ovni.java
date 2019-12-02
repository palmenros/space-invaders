package tp.p2.gameObjects;

import tp.p2.game.Game;
import tp.p2.gameObjects.EnemyShip;

/**
 * Class that represents a UFO
 * @author Martín Gómez y Pedro Palacios
 */
/**
 * @author usuario_local
 *
 */
public class Ovni extends EnemyShip implements IExecuteRandomActions {

	/**
	 *  Total count of ovnis
	 */
	private static int ovniCount = 0;
	
	/**
	 *  Max Ovnis that can be on the game at the same time
	 */
	private static final int MAX_OVNIS = 1;
	
	/**
	 * Default health
	 */
	private static final int HEALTH = 1;
	
	/**
	 * Default score given to player when killed
	 */
	private static final int SCORE = 25;
	
	private static final String SYMBOL = "O";
	
	/**
	 * Construct an Ovni at its default position
	 */
	public Ovni(Game game)
	{
		this(game, 0, Game.COL_NUM);
	}
	
	/**
	 * Construct an Ovni at given position
	 * @param r Row
	 * @param c Column
	 */
	public Ovni(Game game, int r, int c)
	{
		super(game, r, c, HEALTH, SCORE, SYMBOL);
		ovniCount++;
	}
	
	
	/**
	 * Return help message
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return EnemyShip.constructHelpMessage("Ovni", SCORE, HEALTH, 0);
	}
		
	/**
	 * Update ovni position
	 */
	@Override
	public void update()
	{
		move(0,-1);
	}
	
	/**
	 * Destroy method
	 * 
	 */
	@Override
	public void destroy() {
		ovniCount--;
		super.destroy();
	}
	
	@Override
	public boolean receiveMissileAttack(int damage) {
		boolean result = super.receiveMissileAttack(damage);
		
		//If hit by a missile and killed, enable ShockWave
		if(result && !isAlive()) {
			game.enableShockWave();
		}
		
		return result;
	}

	@Override
	public boolean receiveShockWaveAttack(int damage) {
		boolean result = super.receiveShockWaveAttack(damage);
		
		//If hit by ShockWave and killed, enable ShockWave
		if(result && !isAlive()) {
			game.enableShockWave();
		}
		
		return result;
	}
	
	/**
	 * Static computer action for Ovni
	 * @param game Game
	 */
	public static void staticComputerAction(Game game)
	{
		if(ovniCount < MAX_OVNIS && IExecuteRandomActions.canGenerateRandomOvni(game)) {
			game.addObject(new Ovni(game));
		}
	}
	
	/**
	 * Return count of ovnis that are alive
	 * @return Count of alive ovnis
	 */
	public static int getOvniCount() {
		return ovniCount;
	}
}
