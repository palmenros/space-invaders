package tp.p1.game;

/**
 * Class that represents the ship that the user controls
 * @author Martín Gómez y Pedro Palacios
 */
public class UcmShip extends Ship {

	/**
	 * Initial row of the ship
	 */
	private static final int DEFAULT_ROW = 7;
	
	/**
	 * Initial column of the ship
	 */
	private static final int DEFAULT_COL = 4;
	
	/**
	 * Initial health of the ship
	 */
	private static final int HEALTH = 3;
	
	/**
	 * Reference to missile
	 */
	private UCMMissile missile = null;
		
	private boolean shockWaveAvailable = false;
	
	private int superMissileNum = 0;
	
	/**
	 * Construct new UCM ship at location
	 * @param r Row
	 * @param c Column
	 */
	public UcmShip(Game game, int r, int c)
	{
		super(game, r, c, HEALTH);
	}
	
	/**
	 * Construct new UCM ship at default location 
	 */
	public UcmShip(Game game)
	{
		super(game, DEFAULT_ROW, DEFAULT_COL, HEALTH);
	}
	
	
	/**
	 * Get string representation of UCM Ship
	 */
	public String toString()
	{
		if(getHealth() > 0) {
			return "^__^";			
		} else {
			return "!xx!";
		}
	}
	
	/**
	 * Get help message
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return "^__^: Harm: " + UCMMissile.DEFAULT_HARM + " - Shield: " + HEALTH;
	}
		
	/**
	 * Update the ship.
	 */
	public void update()
	{
		//Ship is always alive unless killed by enemies, which is handled by game.
	}
	
	/**
	 * Shoot a missile
	 * @param superMissile 
	 * @return true if succeeded, false otherwise
	 */
	public boolean shoot(boolean superMissile)
	{
		if(missile != null) { return false; }
		
		if(superMissile) {
			if(superMissileNum <= 0) { return false; }
			superMissileNum--;			
			missile = new SuperMissile(game, getRow(), getCol());
		} else {
			missile = new UCMMissile(game, getRow(), getCol());
		}
		
		game.addObject(missile);
		
		return true;
	}
	
	public boolean hasShockWave() {
		return shockWaveAvailable;
	}
	
	public void enableShockWave() {
		shockWaveAvailable = true;
	}
	
	public boolean useShockWave() {
		if(!hasShockWave()) {
			return false;
		}
		
		game.addObject(new ShockWave(game, -1, -1));
		shockWaveAvailable = false;	
		return true;
	}
	
	/**
	 * Enables missile so it can be shot again
	 */
	public void enableMissile()
	{
		missile = null;
	}
	
	/**
	 *	Receive bomb attack
	 *  @param dmg Damage to receive
	 *  @return True if affected
	 */
	@Override
	public boolean receiveBombAttack(int dmg) {
		damage(dmg);
		return true;
	}

	public void addSuperMissile() {
		superMissileNum++;
	}
	
	public int getSuperMissileNum() {
		return superMissileNum;
	}
	
}
