package tp.p2.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

import tp.p2.exceptions.FileContentsException;
import tp.p2.exceptions.GameActionException;
import tp.p2.exceptions.OffWorldException;
import tp.p2.gameObjects.AlienShip;
import tp.p2.gameObjects.DestroyerShip;
import tp.p2.gameObjects.ExplosiveShip;
import tp.p2.gameObjects.GameObject;
import tp.p2.gameObjects.GameObjectGenerator;
import tp.p2.gameObjects.Ovni;
import tp.p2.gameObjects.RegularShip;
import tp.p2.gameObjects.UcmShip;
import tp.p2.input.FileContentsVerifier;

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
	private Level level;
	
	/**
	 * Random Number Generator
	 */
	private Random rand;
	
	/**
	 * Number of cycles since start of the game
	 */
	private int cycleCount;

	/**
	 * Player's ship
	 */
	private UcmShip ucmShip;

	/**
	 *  Game object board
	 */
	private GameObjectBoard board;
	
	/**
	 * Board initializer
	 */
	private BoardInitializer initializer;
	
	private boolean serializing = false;
		
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
		cycleCount = 0;
			
		ucmShip = new UcmShip(this);
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
	 * Shoot a missile
	 */
	public void shootMissile(boolean superMissile) throws GameActionException
	{
		ucmShip.shoot(superMissile);
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
	 */
	public void move(int dc) throws GameActionException
	{
		if(ucmShip.canMove(0, dc)) {
			ucmShip.move(0, dc);
		} else {
			throw new OffWorldException();
		}
	}
	
	/**
	 * Adds points to score
	 * @param points Points to add to score
	 */
	public void receivePoints(int points)
	{
		ucmShip.receiveScore(points);
	}
	
	/**
	 * Try to use SuperPower
	 * @return true if could use, false otherwise
	 */
	public void shockWave() throws GameActionException
	{
		ucmShip.useShockWave();
	}	
	
	/**
	 * Enables Shock Wave
	 */
	public void enableShockWave()
	{
		ucmShip.enableShockWave();
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
		
	public int getRemainingAliens() {
		return DestroyerShip.getDestroyerShipCount() + RegularShip.getRegularShipCount() + Ovni.getOvniCount() + ExplosiveShip.getExplosiveShipCount();
	}

	@Override
	public void buySuperMissile(int cost) throws GameActionException {
		ucmShip.buySuperMissile(cost);
	}

	public int getPlayerHealth() {
		return ucmShip.getHealth();
	}

	public int getCycleCount() {
		return cycleCount;
	}

	public int getScore() {
		return ucmShip.getScore();
	}

	public boolean hasShockWave() {
		return ucmShip.hasShockWave();
	}

	public int getSuperMissileNum() {
		return ucmShip.getSuperMissileNum();
	}
	
	public void setSerializing() {
		serializing = true;
	}
	
	public boolean isSerializing() {
		return serializing;
	}

	public String serialize() {
		serializing = false;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("G;%d\n", cycleCount));
		stringBuilder.append(level.serialize() + "\n");
		stringBuilder.append(board.serialize());
		return stringBuilder.toString();
	}
	
	public void load(BufferedReader stream) throws FileContentsException, IOException {
		
		//Clean old state
		board.reset();
		board = new GameObjectBoard();
		AlienShip.reset();
		
		FileContentsVerifier verifier = new FileContentsVerifier();
		
		String line = stream.readLine();
		if(line == null || !verifier.verifyCycleString(line)) {
			throw new FileContentsException("Invalid game serialization");			
		}
		cycleCount = Integer.parseInt(line.split(verifier.getReadSeparator1())[1]);
	
		line = stream.readLine();
		if(line == null || !verifier.verifyLevelString(line)) {
			throw new FileContentsException("Invalid level serialization");
		}
		level = Level.parse(line.split(verifier.getReadSeparator1())[1]);
		
		line = stream.readLine();
		while( line != null && !line.isEmpty() ) {
			GameObject gameObject = GameObjectGenerator.parse(line.trim(), this, verifier);
			if (gameObject == null) {
				throw new FileContentsException("Invalid file, unrecognized line prefix");
			}
			
			//Detect if UCMShip and set reference
			if(line.split(verifier.getReadSeparator1())[0].equals(UcmShip.getPlayerSymbol())) {
				ucmShip = (UcmShip) gameObject;
			}
			
			board.add(gameObject);
			line = stream.readLine();
		}
		
		//Set UCMShip missile available state
		if(verifier.isMissileOnLoadedBoard()) {
			ucmShip.disableMissile();			
		} else {
			ucmShip.enableMissile();
		}
	}

	public static boolean isOutOfBounds(int r, int c) {
		if ( r < 0 || r >= Game.ROW_NUM) { return true; }
		if ( c < 0 || c >= Game.COL_NUM) { return true; }

		return false;
	}

	public DestroyerShip getBombOwner(int ref) {
		return (DestroyerShip) board.getLabelOwner(ref);  // ugly cast
	}
}
