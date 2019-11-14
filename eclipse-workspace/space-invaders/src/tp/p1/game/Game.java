//TODO: On reset game reset static variables

package tp.p1.game;

import java.util.Random;
import tp.p1.util.*;

/**
 * Class that represents a game
 * @author Martín Gómez y Pedro Palacios
 */
public class Game implements IPlayerController {

	/**
	 * Number of rows in board
	 */
	public static final int ROW_NUM = 8;
	
	/**
	 * Number of columns in board 
	 */
	public static final int COL_NUM = 9;
	
	/**
	 * Chosen level by the user
	 */
	private final Level level;
	
	/**
	 * Random Number Generator
	 */
	private Random rand;
	
	/**
	 * Number of cycles since start of the game
	 */
	private int cycleCount;

	/**
	 *	User score 
	 */
	private int score;

	/**
	 * Player's ship
	 */
	private UcmShip ucmShip;

	/**
	 * SuperPower
	 */
	private ShockWave superPower;

	/**
	 *  Game object board
	 */
	private GameObjectBoard board;
	
	/**
	 * Board initializer
	 */
	private BoardInitializer initializer;
		
	/**
	 * Create new game with given level and seed
	 * @param level	Level
	 * @param seed Random seed
	 */
	public Game(Level level, int seed)
	{
		this.level = level;
		rand = new Random(seed);
		initializer = new BoardInitializer();
		
		initGame();
	}
	
	/**
	 * Initialize game
	 */
	public void initGame()
	{
		//Initialize variables
		score = 0;
		cycleCount = 0;
			
		ucmShip = new UcmShip(this);
		superPower = null;	
		board = initializer.initialize(this);
		board.add(ucmShip);
	}
		
	/**
	 * Do another game cycle
	 * @return Game state that indicates if game has finished
	 */
	public GameState tick()
	{
		computerAction();
		update();
	
		return getGameState();
	}
	
	/**
	 * Calculate what the AI will do
	 */
	private void computerAction()
	{		
		Ovni.staticComputerAction(this);		
		board.computerAction();
	}
	
	/**
	 * Update game objects
	 */
	private void update()
	{
		board.update();
		cycleCount++;
	}
	
	/**
	 * Return string of object at position
	 * @param r	Row
	 * @param c	Column
	 * @return String representation of object
	 */
	public String characterAtToString(int r, int c)
	{
		return board.toString(r, c);
	}
	
	/**
	 *	Return string representation of this game
	 */
	public String toString()
	{
		GamePrinter gamePrinter = new GamePrinter(this, ROW_NUM, COL_NUM);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Life: " + ucmShip.getHealth() + "\n");
		stringBuilder.append("Number of cycles: " + cycleCount + "\n");
		stringBuilder.append("Points: " + score + "\n");
		
		int remainingAliens = getRemainingAliens();
	
		stringBuilder.append("Remaining aliens: " + remainingAliens + "\n");
		stringBuilder.append("ShockWave: " + ( superPower == null ? "NO" : "YES" ) + "\n");
		
		stringBuilder.append(gamePrinter.toString());
		
		return stringBuilder.toString();
	}
	
	/**
	 * Reset game
	 */
	public void reset()
	{
		board.reset();
		AlienShip.reset();
		initGame();
	}
	
	/**
	 * Return UCM Ship
	 * @return UCM ship
	 */
	public UcmShip getUcmShip()
	{
		return ucmShip;
	}

	/**
	 * Try to use SuperPower
	 * @return true if could use, false otherwise
	 */
	public boolean shockWave()
	{
		//TODO: Implement shockWave
		
		//if(superPower == null) { return false; }
	
		//int damage = superPower.getHarm();
		//superPower.kill();;
		
		//if(ovni != null) {	
		//	damageOvni(damage);
		//}
				
		//receivePoints(destroyerList.damageAll(damage));
		//receivePoints(regularList.damageAll(damage));
		
		
		return true;
	}
	
	/**
	 * Shoot a missile
	 * @return true if succeeded, false oterwise
	 */
	public boolean shootMissile()
	{
		return ucmShip.shoot();
	}
		
	/**
	 * Get Game State
	 * @return game state
	 */
	private GameState getGameState() 
	{
		if(getRemainingAliens() == 0) {
			return GameState.PLAYER_WIN;
		}
				
		if(ucmShip.getHealth() == 0) {
			return GameState.ALIEN_WIN;
		}
		
		boolean isAnyAlienLastRow = AlienShip.haveLanded();
		
		if(isAnyAlienLastRow) {
			return GameState.ALIEN_WIN;
		}
		
		return GameState.IN_PROGRESS;
	}
	
	/**
	 * Tries to move UCM ship delta column
	 * @param dc Delta columns to move
	 * @return True if able to move, false otherwise
	 */
	public boolean move(int dc)
	{
		if(ucmShip.canMove(0, dc)) {
			ucmShip.move(0, dc);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds points to score
	 * @param points Points to add to score
	 */
	public void receivePoints(int points)
	{
		score += points;
	}
	
	/**
	 * Enables Shock Wave
	 */
	public void enableShockWave()
	{
		superPower = new ShockWave(this, 0, 0);	
	}
	
	/**
	 * Add game object to board
	 * @param object Add new object to Game Board
	 */
	public void addObject(GameObject object)
	{
		board.add(object);
	}	
	
	/**
	 * Enables missile so it can be shot again
	 */
	public void enableMissile()
	{
		ucmShip.enableMissile();
	}
	
	/**
	 * Get random
	 * @return Random
	 */
	public Random getRandom()
	{
		return rand;
	}
	
	/**
	 * Get level
	 * @return Level
	 */
	public Level getLevel()
	{
		return level;
	}
		
	private int getRemainingAliens() {
		return DestroyerShip.getDestroyerShipCount() + RegularShip.getRegularShipCount() + Ovni.getOvniCount();
	}
}
