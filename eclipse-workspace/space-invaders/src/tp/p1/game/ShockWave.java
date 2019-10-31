package tp.p1.game;

/**
 * SuperPower
 * @author Martín Gómez y Pedro Palacios
 */
public class ShockWave extends Weapon {

	/**
	 * Default damage that the super power inflicts to all enemies
	 */
	private static final int DEFAULT_HARM = 1;
	
	/**
	 * Constructor with default damage
	 */
	public ShockWave(Game game, int r, int c)
	{
		this(game, r, c, DEFAULT_HARM);
	}
	
	
	/**
	 * Shockwave with specific harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm of the shockwave
	 */
	public ShockWave(Game game, int r, int c, int harm)
	{
		
		super(game, r, c, harm);
	}
	
	/**
	 * The shockwave does not update, so this method will not be implemented
	 */
	@Override
	public boolean update()
	{
		return false;
	}
	

}
