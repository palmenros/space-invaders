package tp.p2.gameObjects;

import tp.p2.game.Game;

public class DiagonalMissile extends UCMMissile {
	
	private boolean moveLeft;
	/**
	 * Construct a diagonal new missile at position with default harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm which the missile produces
	 */
	private static final String SYMBOL = "DM";
	public DiagonalMissile(Game game, int r, int c)
	{
		super(game, r, c, DEFAULT_HARM, SYMBOL);
		moveLeft = true;
	}
	
	

	/**
	 * Get string representation of missile
	 */
	@Override
	public String toString()
	{
		return "<oo>";
	}
		
	/**
	 * Update missile position
	 */
	@Override
	public void update()
	{
		if (moveLeft)
		{
			move(-1,-1);
			moveLeft = false;
		}
		else
		{
			move(-1,1);
			moveLeft = true;
		}
	}
}
