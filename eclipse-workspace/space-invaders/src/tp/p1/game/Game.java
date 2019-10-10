package tp.p1.game;

import java.util.Random;
import tp.p1.util.*;

/**
 * @author Martín Gómez y Pedro Palacios
 *
 * Class that represents a game
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
	private Missile missile;
	
	/**
	 * SuperPower
	 */
	private SuperPower superPower;
	
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
		
		ucmShip = new UcmShip();
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
				regularList.insert(new RegularShip( INITIAL_COMMON_ALIENS_ROW + row, INITIAL_COMMON_ALIENS_COLUMN + col ));
			}
		}
		
		int destroyerShipNum = level.getDestroyerNum();
		int columnOffset = (INITIAL_COMMON_ALIENS_PER_ROW - destroyerShipNum) / 2;
		for (int i = 0; i < destroyerShipNum; i++)
		{
			destroyerList.insert(new DestroyerShip(INITIAL_COMMON_ALIENS_ROW + row, INITIAL_COMMON_ALIENS_COLUMN + columnOffset + i));
		}
	}
	
	/**
	 * Draw the game on screen
	 */
	public void draw()
	{
		System.out.print(this.toString());
	}
	
	/**
	 * Do another game cycle
	 * @return Game state that indicates if game has finished
	 */
	public GameState tick()
	{
		computerAction();
		update();
		draw();		
	
		return getGameState();
	}
	
	/**
	 * Calculate what the AI will do
	 */
	private void computerAction()
	{
		//Destroyers
		for(int i = 0; i < destroyerList.length(); i++) {
			if(rand.nextFloat() <= level.getFireRate()) {
				Bomb bomb = destroyerList.get(i).shoot();
				if(bomb != null) {
					bombList.insert(bomb);
				}
			}	
		}
		
		//Ovni
		if(ovni == null && rand.nextFloat() <= level.getUfoRate())
		{
			ovni = new Ovni();
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
		int i = 0;
		while(i < bombList.length()) {
			Bomb bomb = bombList.get(i);
		
			//Move
			if(!bomb.update()){
				bombList.remove(i);
				i--;
			}
			i++;
		}
		
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
		
		int i = 0;
		while(i < bombList.length()) {
			Bomb bomb = bombList.get(i);
		
			//Collide
			if(damagePlayer(bomb.getRow(), bomb.getCol(), bomb.getHarm())){
				bombList.remove(i);
				i--;
			}
			i++;
		}
		
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
		stringBuilder.append("Superpower: " + ( superPower == null ? "NO" : "YES" ) + "\n");
		
		stringBuilder.append(gamePrinter.toString());
		
		return stringBuilder.toString();
	}
	
	/**
	 * Reset game
	 */
	public void reset()
	{
		initGame();
		draw();
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
	
		if(ovni != null)
		{	
			ovni.damage(superPower.getDamage());
		}
			
		int i = 0;
		while(i < destroyerList.length())	
		{
			if(damageDestroyer(i, superPower.getDamage())) {
				i++;
			}
		}
		
		int j = 0;
		while(j < regularList.length())	
		{	
			if(damageRegular(j, superPower.getDamage())) {
				j++;
			}
		}
						
		superPower = null;
		
		return true;
	}
	
	/**
	 * Shoot a missile
	 * @return true if succeeded, false oterwise
	 */
	public boolean shoot()
	{
		if(missile != null) { return false; }
		
		missile = new Missile(ucmShip.getRow(), ucmShip.getCol());
		
		return true;
	}
	
	/**
	 * Damage Ovni
	 * @return True if UFO is still alive, false otherwise
	 */	
	private boolean damageOvni(int harm)
	{
		if(!ovni.damage(harm))
		{
			score += ovni.getScore();
			ovni = null;
			superPower = new SuperPower();			
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Damage destroyer
	 * @return True if still alive, false otherwise
	 */	
	private boolean damageDestroyer(int index, int harm) 
	{
		int newScore = destroyerList.damage(index, harm);
		
		if(newScore < 0) {
			return true;
		} else {
			score += newScore;
			return false;
		}
	}
	
	/**
	 * Damage regular ship
	 * @return True if still alive, false otherwise
	 */	
	private boolean damageRegular(int index, int harm) 
	{
		int newScore = regularList.damage(index, harm);
		
		if(newScore < 0) {
			return true;
		} else {
			score += newScore;
			return false;
		}
	}
	
	/**
	 * Damage enemy
	 * @return True if hit anything, false otherwise
	 */	
	private boolean damageEnemy(int r, int c, int harm) 
	{
		int bomb = bombList.getIndexAtPosition(r, c);
		int regular = regularList.getIndexAtPosition(r, c);
		int destructor = destroyerList.getIndexAtPosition(r, c);
		
		if(bomb >= 0) {
			bombList.remove(bomb);
			return true;
		} else if(regular >= 0) {
			damageRegular(regular, harm);
			return true;
		} else if(destructor >= 0){
			damageDestroyer(destructor, harm);
			return true;
		} else if(ovni != null && ovni.isAt(r, c)){
			damageOvni(harm);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Damage player
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
		boolean isAlienAtBorder = false;
		int i = 0;
		while(!isAlienAtBorder && i < regularList.length())
		{
			isAlienAtBorder = regularList.get(i).isAtColumnBorder(alienDirection);
			i++;
		}
		
		i = 0;
		while(!isAlienAtBorder && i < destroyerList.length())
		{
			isAlienAtBorder = destroyerList.get(i).isAtColumnBorder(alienDirection);
			i++;
		}
		
		if(isAlienAtBorder)
		{			
			for(int j = 0; j < regularList.length(); j++) { regularList.get(j).move(1, 0); }
			for(int j = 0; j < destroyerList.length(); j++) { destroyerList.get(j).move(1, 0); }
			
			alienDirection = alienDirection.getOppositeDirection();
		}
		
		return isAlienAtBorder;
	}
	
	/**
	 * Move aliens horizontally according to direction
	 */
	private void moveAliensHorizontally() 
	{
		for(int j = 0; j < regularList.length(); j++) { regularList.get(j).move(0, alienDirection.getDeltaCol()); }
		for(int j = 0; j < destroyerList.length(); j++) { destroyerList.get(j).move(0, alienDirection.getDeltaCol()); }
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
		
		boolean isAnyAlienLastRow = false;
		
		int i = 0;	
		while(!isAnyAlienLastRow && i < regularList.length()){
			isAnyAlienLastRow = regularList.get(i).isAtLowestRow();
			i++;
		}
		
		i = 0;	
		while(!isAnyAlienLastRow && i < destroyerList.length()){
			isAnyAlienLastRow = destroyerList.get(i).isAtLowestRow();
			i++;
		}
		
		if(isAnyAlienLastRow) {
			return GameState.ALIEN_WIN;
		}
		
		return GameState.IN_PROGRESS;
	}
}
