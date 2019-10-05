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
	private int cycleCount = 0;
	private int score = 0;
	
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
		
	}
	
	public void update()
	{
		// Missile
		if(missile != null) {
			if(!missile.move(-1, 0)) {
				missile = null;
			}
		}
		
		handleCollisions();
	}
	
	public void handleCollisions()
	{
		//Missile
		if(missile != null) {
			int bomb = bombList.getIndexAtPosition(missile.getRow(), missile.getCol());
			int regular = regularList.getIndexAtPosition(missile.getRow(), missile.getCol());
			int destructor = destroyerList.getIndexAtPosition(missile.getRow(), missile.getCol());
			
			if(bomb >= 0) {
				bombList.remove(bomb);
				missile = null;
			} else if(regular >= 0) {
				damageRegular(regular, missile.getHarm());
				missile = null;
			} else if(destructor >= 0){
				damageDestroyer(destructor, missile.getHarm());
				missile = null;
			} else if(ovni != null && ovni.isAt(missile.getRow(), missile.getCol())){
				damageOvni(missile.getHarm());
				missile = null;
			}
		}
			
	}

	String characterAtToString(int r, int c)
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
	 * @return True if ovni is still alive, false otherwise
	 */	
	public boolean damageOvni(int harm)
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
	
	public boolean damageDestroyer(int index, int harm)
	{
		int newScore = destroyerList.damage(index, harm);
		
		if(newScore < 0) {
			return true;
		} else {
			score += newScore;
			return false;
		}
	}
	
	public boolean damageRegular(int index, int harm)
	{
		int newScore = regularList.damage(index, harm);
		
		if(newScore < 0) {
			return true;
		} else {
			score += newScore;
			return false;
		}
	}
	
}
