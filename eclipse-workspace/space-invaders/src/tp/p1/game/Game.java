package tp.p1.game;

import java.util.Random;
import tp.p1.util.*;

public class Game {

	public static final int ROW_NUM = 8;
	public static final int COL_NUM = 9;
	
	private static final int INITIAL_COMMON_ALIENS_PER_ROW = 4;
	private static final int INITIAL_COMMON_ALIENS_ROW = 1;
	private static final int INITIAL_COMMON_ALIENS_COLUMN = 3;	
	
	private final Level level;
	private Random rand;
	private int cycleCount;
	private int cyclesSinceLastMove;
	private int score;
	
	private Direction alienDirection;
	
	private UcmShip ucmShip;
	private Ovni ovni;
	private Missile missile;
	private SuperPower superPower;
	
	private BombList bombList;
	private DestroyerShipList destroyerList;
	private RegularShipList regularList;
	
	public Game(Level level, int seed)
	{
		this.level = level;
		rand = new Random(seed);
		
		initGame();
	}
	
	public void initGame()
	{
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
	
	public void draw()
	{
		System.out.print(this.toString());
	}
	
	public void computerAction()
	{
		//Shoot bomb
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
	
	public void update()
	{
		// Missile
		if(missile != null) {
			
			//Move 
			if(!missile.move(-1, 0)) {
				missile = null;
			} else if(damageEnemy(missile.getRow(), missile.getCol(), missile.getHarm()))
			{
				//Collisions	
				missile = null;
			}
		}
		
		//Bomb
		int i = 0;
		while(i < bombList.length()) {
			Bomb bomb = bombList.get(i);
		
			//Move
			if(!bomb.move(1, 0) || damagePlayer(bomb.getRow(), bomb.getCol(), bomb.getHarm())){
				bombList.remove(i);
				i--;
			}
			i++;
		}
		
		moveAliens();
	
		if(ovni != null)
		{
			if(!ovni.move(0, -1)) {
				ovni = null;
			}
		}
		
		cycleCount++;
		cyclesSinceLastMove++;
	}
	
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
	
	public void reset()
	{
		initGame();
		draw();
	}
	
	public UcmShip getUcmShip()
	{
		return ucmShip;
	}

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
	
	public boolean shoot()
	{
		if(missile != null) { return false; }
		
		missile = new Missile(ucmShip.getRow(), ucmShip.getCol());
		
		return true;
	}
	
	/**
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
	
	private void moveAliensHorizontally() 
	{
		for(int j = 0; j < regularList.length(); j++) { regularList.get(j).move(0, alienDirection.getDeltaCol()); }
		for(int j = 0; j < destroyerList.length(); j++) { destroyerList.get(j).move(0, alienDirection.getDeltaCol()); }
	}
	
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
	
	public GameState shouldExit() 
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
