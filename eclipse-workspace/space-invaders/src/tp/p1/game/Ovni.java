package tp.p1.game;

import tp.p1.game.EnemyShip;

/**
 * Class that represents a UFO
 * @author Martín Gómez y Pedro Palacios
 */
public class Ovni extends EnemyShip {

	/**
	 * Default health
	 */
	private static final int HEALTH = 1;
	
	/**
	 * Default score given to player when killed
	 */
	private static final int SCORE = 25;
	
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
		super(game, r, c, HEALTH, SCORE);
	}
	
	/**
	 * Get string representation of ovni
	 */
	public String toString()
	{
		return "O[" + getHealth() + "]"; 
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
	public boolean update()
	{
		return move(0,-1);
	}
}
