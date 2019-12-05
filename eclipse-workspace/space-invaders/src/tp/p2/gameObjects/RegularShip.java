package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Direction;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

/**
 * Class that represents a common ship that cannot shoot
 * @author Martín Gómez y Pedro Palacios
 */
public class RegularShip extends AlienShip {
	
	/**
	 * Count of regular ships
	 */
	private static int regularCount = 0;
	
	/**
	 * Default health
	 */
	protected static final int HEALTH = 2;
	
	/**
	 * Default score
	 */
	protected static final int SCORE = 5;
	
	private static final String SYMBOL = "R";
	
	/**
	 * Create regular ship at location
	 * @param r Row
	 * @param c Column
	 */
	public RegularShip(Game game, int r, int c)
	{
		this(game, r, c, HEALTH, SCORE);
	}
	
	protected RegularShip(Game game, int r, int c, int health, int score)
	{
		super(game, r, c, health, score, SYMBOL);
		regularCount++;
	}
	
	public RegularShip() {
		this(null, -1, -1);
		alienShipCount--;
		regularCount--;
	}

	/**
	 * Return string representation of regular ship
	 */
	public String toString()
	{
		return "C[" + getHealth() + "]";
	}
	
	/**
	 * Return help message
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return EnemyShip.constructHelpMessage("Regular ship", SCORE, HEALTH, 0);
	}
	/**
	 * Delete object
	 */
	public void destroy() {
		regularCount--;
		super.destroy();
	}
	
	/**
	 * Return count of regular ships that are still alive
	 * @return count of alive regular ships
	 */
	public static int getRegularShipCount() {
		return regularCount;
	}
	
	@Override
	public void computerAction()
	{
		super.computerAction();
		if (IExecuteRandomActions.shouldBecomeExplosiveShip(game))
		{
			game.addObject(new ExplosiveShip(game, getRow(), getCol(), getHealth(), getScore()));
			kill();
		}
	}
	
	private static RegularShip createInstance(Game game, int r, int c, int health, int label) {
		RegularShip ship = new RegularShip(game, r, c);
		ship.setHealth(health);
		ship.setLabel(label);	
		return ship;
	}
	
	@Override
	public 	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		if(super.parse(string, game, verifier) == null) { return null; }
	
		string = string.split(labelRefSeparator)[0];
		if(!verifier.verifyAlienShipString(string, game, HEALTH)) { throw new FileContentsException("Invalid regular ship serialization"); }
		
		//Load data
		String[] words = string.split(verifier.getReadSeparator1());
		
		//Load static data
		cyclesSinceLastMove = Integer.parseInt(words[3]);
		alienDirection = Direction.parse(words[4]);
		
		//Create ship
		return createInstance(game, getRow(), getCol(), Integer.parseInt(words[2]), label);
	}
	
}
