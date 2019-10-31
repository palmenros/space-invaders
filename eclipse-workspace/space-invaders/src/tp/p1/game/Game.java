package tp.p1.game;

import java.util.Random;
import tp.p1.util.*;

/**
 * Class that represents a game
 * @author Martín Gómez y Pedro Palacios
 */
public class Game {

	/**
	 * Number of rows in board
	 */
	public static final int ROW_NUM = 8;
	
	/**
	 * Number of columns in board 
	 */
	public static final int COL_NUM = 9;
	
	/**
	 * Initial regular aliens per row
	 */
	private static final int INITIAL_COMMON_ALIENS_PER_ROW = 4;
	
	/**
	 * Row where regular aliens are initially spawned 
	 */
	private static final int INITIAL_COMMON_ALIENS_ROW = 1;
	
	/**
	 * Column where regular aliens are initially spawned 
	 */
	private static final int INITIAL_COMMON_ALIENS_COLUMN = 3;	
	
	
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
	 * Number of cycles since last joined alien move
	 */
	private int cyclesSinceLastMove;
		
	/**
	 *	User score 
	 */
	private int score;
	
	
	/**
	 *  Direction where the aliens are moving
	 */
	private Direction alienDirection;
	
	/**
	 * Player's ship
	 */
	private UcmShip ucmShip;
	
	/**
	 * UFO
	 */
	private Ovni ovni;
	
	/**
	 * Missile
	 */
	private UCMMissile missile;
	
	/**
	 * SuperPower
	 */
	private ShockWave superPower;
	
	/**
	 * List of bombs
	 */
	private BombList bombList;
	
	/**
	 * List of destroyers
	 */
	private DestroyerShipList destroyerList;
	
	/**
	 * List of regular lists
	 */
	private RegularShipList regularList;
	
	
	/**
	 * Create new game with given level and seed
	 * @param level	Level
	 * @param seed Random seed
	 */
	public Game(Level level, int seed)
	{
		this.level = level;
		rand = new Random(seed);
		
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
		cyclesSinceLastMove = Integer.MAX_VALUE;
		
		alienDirection = Direction.LEFT;
		
		ucmShip = new UcmShip(this);
		ovni = null;
		missile = null;
		superPower = null;
		bombList = new BombList();
		destroyerList = new DestroyerShipList();
		regularList = new RegularShipList();
		
		//Initialize default position of aliens
		int regularShipNum = level.getCommonShipNum();
		int row;
		for(row = 0; row < regularShipNum / INITIAL_COMMON_ALIENS_PER_ROW; row ++)
		{
			for(int col = 0; col  < INITIAL_COMMON_ALIENS_PER_ROW; col++)
			{
				regularList.insert(new RegularShip( this, INITIAL_COMMON_ALIENS_ROW + row, INITIAL_COMMON_ALIENS_COLUMN + col ));
			}
		}
		
		int destroyerShipNum = level.getDestroyerNum();
		int columnOffset = (INITIAL_COMMON_ALIENS_PER_ROW - destroyerShipNum) / 2;
		for (int i = 0; i < destroyerShipNum; i++)
		{
			destroyerList.insert(new DestroyerShip(this, INITIAL_COMMON_ALIENS_ROW + row, INITIAL_COMMON_ALIENS_COLUMN + columnOffset + i));
		}
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
		//Destroyers
		destroyerList.computerAction(rand, level, bombList);
		
		//Ovni
		if(ovni == null && rand.nextFloat() <= level.getUfoRate())
		{
			ovni = new Ovni(this);
		}
	}
	
	/**
	 * Update game objects
	 */
	private void update()
	{
		// Update missile
		if(missile != null && !missile.update()) {
			missile = null;
		}
		
		//Handle missile collision with bombs to detect possible intersections
		handleMissileCollisions();
			
		//Bomb
		bombList.update();
		
		//Update aliens
		moveAliens();
	
		//Update ovni
		if(ovni != null && !ovni.update()){
			ovni = null;
		}
		
		//Handle collisions
		handleCollisions();
		
		//Increase cycles
		cycleCount++;
		cyclesSinceLastMove++;
	}
	
	/**
	 * Handle missile collisions
	 */
	private void handleMissileCollisions()
	{ 
		if(missile != null && damageEnemy(missile.getRow(), missile.getCol(), missile.getHarm())) {
			missile = null;
		}
	}
	
	/**
	 * Handle collisions of missiles and bombs
	 */
	private void handleCollisions()
	{
		handleMissileCollisions();
				
		bombList.removeIf(new BombCallback() {
			public boolean shouldRemove(Bomb bomb) {
				return damagePlayer(bomb.getRow(), bomb.getCol(), bomb.getHarm());
			}
		});
	}
			
	/**
	 * Return string of object at position
	 * @param r	Row
	 * @param c	Column
	 * @return String representation of object
	 */
	public String characterAtToString(int r, int c)
	{
		if(ucmShip != null && ucmShip.isAt(r, c)) { return ucmShip.toString(); } 
		if(ovni != null && ovni.isAt(r, c)) { return ovni.toString(); } 
		if(missile != null && missile.isAt(r, c)) { return missile.toString(); } 
		
		Bomb bomb = bombList.getAtPosition(r, c);
		if(bomb != null) { return bomb.toString(); } 
		
		DestroyerShip destroyer = destroyerList.getAtPosition(r, c);
		if (destroyer != null) { return destroyer.toString(); }
		
		RegularShip ship = regularList.getAtPosition(r, c);
		if(ship != null) { return ship.toString(); }
		
		return "";
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
		
		int remainingAliens = destroyerList.length() + regularList.length() + ( ovni == null ? 0 : 1 );
	
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
	public boolean useSuperPower()
	{
		if(superPower == null) { return false; }
	
		int damage = superPower.getHarm();
		superPower = null;
		
		if(ovni != null) {	
			damageOvni(damage);
		}
				
		score += destroyerList.damageAll(damage);
		score += regularList.damageAll(damage);						
		
		return true;
	}
	
	/**
	 * Shoot a missile
	 * @return true if succeeded, false oterwise
	 */
	public boolean shoot()
	{
		if(missile != null) { return false; }
		
		missile = new UCMMissile(this, ucmShip.getRow(), ucmShip.getCol());
		
		return true;
	}
	
	/**
	 * Damage Ovni
	 * @param harm Harm caused to the UFO
	 * @return True if UFO is still alive, false otherwise
	 */	
	private boolean damageOvni(int harm)
	{
		if(!ovni.damage(harm))
		{
			score += ovni.getScore();
			ovni = null;
			superPower = new ShockWave(this, 0, 0);			
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Damage destroyer at position row, column
	 * @param row Row
	 * @param column Column
	 * @param harm Harm to cause
	 * @return True if hit something, false otherwise
	 */	
	private boolean damageDestroyerAtPosition(int row, int column, int harm) 
	{
		int newScore = destroyerList.damageAtPosition(row, column, harm);
		
		if(newScore == -2) {
			//Didn't hit anything
			return false;
		} 

		//Hit something
		if(newScore > 0){
			score += newScore;
		}	
		return true;
	}
	
	/**
	 * Damage regular ship at position row, column
	 * @param row Row
	 * @param column Column
	 * @param harm Harm to cause
	 * @return True if hit something, false otherwise
	 */	
	private boolean damageRegularAtPosition(int row, int column, int harm) 
	{
		int newScore = regularList.damageAtPosition(row, column, harm);
		
		if(newScore == -2) {
			//Didn't hit anything
			return false;
		} 

		//Hit something
		if(newScore > 0){
			score += newScore;
		}	
		return true;
	}
	
	/**
	 * Damage enemy at location
	 * @param r Row
	 * @param c Column
	 * @param harm Harm to cause
	 * @return True if hit anything, false otherwise
	 */	
	private boolean damageEnemy(int r, int c, int harm) 
	{		
		if(bombList.damageBombAtPosition(r, c)) {
			return true;
		} else if(damageRegularAtPosition(r, c, harm)) {
			return true;
		} else if(damageDestroyerAtPosition(r, c, harm)){	
			return true;
		} else if(ovni != null && ovni.isAt(r, c)){
			damageOvni(harm);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Damage player-affine objects
	 * @param r Row
	 * @param c Column
	 * @param harm Harm to cause
	 * @return True if hit player or missile, false otherwise
	 */	
	private boolean damagePlayer(int r, int c, int harm)
	{
		//UcmShip
		if(ucmShip.isAt(r, c)) {
			ucmShip.damage(harm);
			return true;
		}
		
		//Missile
		if(missile != null && missile.isAt(r, c)) {
			missile = null;
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Move aliens down if touching border
	 * @return True if moved aliens down
	 */
	private boolean moveAliensDown() 
	{
		boolean isAlienAtBorder = regularList.isAnyAtColumnBorder(alienDirection) 
									|| destroyerList.isAnyAtColumnBorder(alienDirection);
		
		if(isAlienAtBorder)
		{			
			regularList.moveAll(1, 0);
			destroyerList.moveAll(1, 0);
			
			alienDirection = alienDirection.getOppositeDirection();
		}
		
		return isAlienAtBorder;
	}
	
	/**
	 * Move aliens horizontally according to direction
	 */
	private void moveAliensHorizontally() 
	{
		regularList.moveAll(0, alienDirection.getDeltaCol());
		destroyerList.moveAll(0, alienDirection.getDeltaCol());
	}

	/**
	 * Move aliens
	 */
	private void moveAliens() 
	{
		if(moveAliensDown()) {
			cyclesSinceLastMove = 0;
		} else {
			if(cyclesSinceLastMove >= level.getSpeed()) {
				moveAliensHorizontally();
				cyclesSinceLastMove = 0;
			}
		}
	}
	
	/**
	 * Get Game State
	 * @return game state
	 */
	private GameState getGameState() 
	{
		int remainingAliens = destroyerList.length() + regularList.length() + ( ovni == null ? 0 : 1 );
		if(remainingAliens == 0) {
			return GameState.PLAYER_WIN;
		}
				
		if(ucmShip.getHealth() == 0) {
			return GameState.ALIEN_WIN;
		}
		
		boolean isAnyAlienLastRow = regularList.isAnyAtLowestRow() || destroyerList.isAnyAtLowestRow();
		
		if(isAnyAlienLastRow) {
			return GameState.ALIEN_WIN;
		}
		
		return GameState.IN_PROGRESS;
	}
}
