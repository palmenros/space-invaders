package tp.p1.game;

import tp.p1.game.EnemyShip;

public class Ovni extends EnemyShip {

	private static final int HEALTH = 1;
	private static final int SCORE = 25;
	
	public Ovni(int r, int c)
	{
		super(r, c, HEALTH, SCORE);
	}
	
	public String toString()
	{
		return "O[" + getHealth() + "]"; 
	}
	
	static public String getHelpMessage()
	{
		return EnemyShip.getHelpMessage("Ovni", SCORE, HEALTH, 0);
	}
}
