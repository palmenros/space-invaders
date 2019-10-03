package tp.p1.game;

import java.util.Random;
import tp.p1.util.*;

public class Game {

	private static final int ROW_NUM = 8;
	private static final int COL_NUM = 9;
	
	private final Level level;
	private Random rand;
	private int cycleCount = 0;
	private int score = 0;
	
	private UcmShip ucmShip;
	private Ovni ovni;
	private Missile missile;
	
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
		ucmShip = new UcmShip();
		ovni = null;
		missile = null;
		bombList = new BombList();
		destroyerList = new DestroyerShipList();
		regularList = new RegularShipList();
	}
	
	public void draw()
	{
		GamePrinter gamePrinter = new GamePrinter(this, ROW_NUM, COL_NUM);
		System.out.print(gamePrinter);
	}
	
	public void computerAction()
	{
		
	}
	
	public void update()
	{
		
	}

	String characterAtToString(int r, int c)
	{
		if(ucmShip != null && ucmShip.isAt(r, c)) { return ucmShip.toString(); } 
		if(ovni != null && ovni.isAt(r, c)) { return ovni.toString(); } 
		if(missile != null && missile.isAt(r, c)) { return missile.toString(); } 
		
		for(int i = 0; i < bombList.length(); i++) {
			if(bombList.get(i).isAt(r, c)) { return bombList.get(i).toString(); }
		}
		
		for(int i = 0; i < destroyerList.length(); i++) {
			if(destroyerList.get(i).isAt(r, c)) { return destroyerList.get(i).toString(); }
		}
		
		for(int i = 0; i < regularList.length(); i++) {
			if(regularList.get(i).isAt(r, c)) { return regularList.get(i).toString(); }
		}
		
		
		return "";
	}
}
