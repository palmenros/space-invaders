package tp.p2.gameObjects;

import tp.p2.game.Game;

/**
 * SuperPower
 * @author Martín Gómez y Pedro Palacios
 */
public class ShockWave extends Weapon {

	/**
	 * Default damage that the super power inflicts to all enemies
	 */
	private static final int DEFAULT_HARM = 1;
	
	private boolean firstUpdate = true;
	
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
		
		super(game, r, c, harm, "");
	}
	
	public ShockWave() {
		this(null, 0, 0);
	}


	/**
	 * The shockwave does not update, so this method will not be implemented
	 */
	@Override
	public void update()
	{
		if(firstUpdate) { 
			firstUpdate = false;
		} else { 
			kill(); 
		}
	}
	
	@Override
	public boolean performAttack(GameObject other)
	{
		return other.receiveShockWaveAttack(getHarm());
	}
	
	@Override
	public boolean receiveAreaAttack(int dmg)
	{
		return false;
	}

	@Override
	public boolean receiveShockWaveAttack(int dmg) 
	{
		return false;
	}
}
