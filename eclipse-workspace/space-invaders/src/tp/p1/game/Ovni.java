package tp.p1.game;

import tp.p1.game.EnemyShip;

public class Ovni extends EnemyShip {

	private static final int HEALTH = 1;
	private static final int SCORE = 25;
	
	public Ovni()
	{
		this(0, Game.COL_NUM);
	}
	
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
		return EnemyShip.constructHelpMessage("Ovni", SCORE, HEALTH, 0);
	}
	public boolean update()
	{
		return move(0,-1);
	}
}
