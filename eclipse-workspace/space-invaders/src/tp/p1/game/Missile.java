package tp.p1.game;

/**
 * Class that represents a missile thrown by the UCM-Ship
 * @author Martín Gómez y Pedro Palacios
 */
public class Missile extends GameObject {

	/**
	 * Default harm that the missile will do to enemies
	 */
	public static final int DEFAULT_HARM = 1;
	
	/**
	 *  Harm this missile will do to enemies
	 */
	private int harm;
	
	/**
	 * Construct a new missile at position with default harm
	 * @param r Row
	 * @param c Column
	 */
	public Missile(int r, int c)
	{
		this(r, c, DEFAULT_HARM);
	}
	
	/**
	 * Construct a new missile at position with given harm
	 * @param r Row
	 * @param c Column
	 * @param harm Harm
	 */
	public Missile(int r, int c, int harm)
	{
		super(r, c);
		this.harm = harm;
	}

	/**
	 * Get string representation of missile
	 */
	public String toString()
	{
		return "oo";
	}
	
	/**
	 * Get harm
	 * @return harm
	 */
	public int getHarm()
	{
		return harm;
	}
	
	/**
	 * Update missile position
	 */
	public boolean update()
	{
		return move(-1,0);
	}
}
