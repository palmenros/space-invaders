package tp.p1.game;

/**
 * Class that represents a missile thrown by the UCM-Ship
 * @author Martín Gómez y Pedro Palacios
 */
public class UCMMissile extends Weapon {

	/**
	 * Default harm that the missile will do to enemies
	 */
	public static final int DEFAULT_HARM = 1;
	
	/**
	 * Construct a new missile at position with default harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm which the missile produces
	 */
	public UCMMissile(Game game, int r, int c)
	{
		this(game, r, c, DEFAULT_HARM);
	}
	
	/**
	 * Construct a new missile at position with given harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm
	 */
	public UCMMissile(Game game, int r, int c, int harm)
	{
		super(game, r, c, harm);
	}

	/**
	 * Get string representation of missile
	 */
	public String toString()
	{
		return "oo";
	}
	
	
	
	/**
	 * Update missile position
	 */
	public boolean update()
	{
		return move(-1,0);
	}
}
