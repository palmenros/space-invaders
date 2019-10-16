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
	 * Construct new UCM ship at location
	 * @param r Row
	 * @param c Column
	 */
	public UcmShip(int r, int c)
	{
		super(r, c, HEALTH);
	}
	
	/**
	 * Construct new UCM ship at default location 
	 */
	public UcmShip()
	{
		super(DEFAULT_ROW, DEFAULT_COL, HEALTH);
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
		return "^__^: Harm: " + Missile.DEFAULT_HARM + " - Shield: " + HEALTH;
	}
		
	/**
	 * Update the ship.
	 */
	public boolean update()
	{
		//Ship is always alive unless killed by enemies, which is handled by game.
		return true;
	}
}
