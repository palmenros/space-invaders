package tp.p1.game;

/**
 * SuperPower
 * @author Mart�n G�mez y Pedro Palacios
 */
public class SuperPower {

	/**
	 * Default damage that the super power inflicts to all enemies
	 */
	private static final int DEFAULT_DAMAGE = 1;
	
	/**
	 * Actual damage that the super power inflicts to all enemies
	 */
	private int damage;
	
	/**
	 * Constructor with default damage
	 */
	public SuperPower()
	{
		this(DEFAULT_DAMAGE);
	}
	
	/**
	 * Super power with specific damage
	 * @param damage Damage to inflict
	 */
	public SuperPower(int damage)
	{
		this.damage = damage;
	}
	
	/**
	 * Returns damage that this super power inflicts
	 * @return damage
	 */
	public int getDamage()
	{
		return damage;
	}
}
