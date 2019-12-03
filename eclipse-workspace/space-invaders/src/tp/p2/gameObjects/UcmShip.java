package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.exceptions.GameActionException;
import tp.p2.exceptions.MissileInFlightException;
import tp.p2.exceptions.NoShockWaveException;
import tp.p2.exceptions.NoSuperMissileException;
import tp.p2.exceptions.NotEnoughScoreException;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

/**
 * Class that represents the ship that the user controls
 * @author Martín Gómez y Pedro Palacios
 */
public class UcmShip extends Ship {

	/**
	 *	User score 
	 */
	private int score = 0;
	
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
	private boolean missileAvailable = true;
		
	private boolean shockWaveAvailable = false;
	
	private int superMissileNum = 0;
	
	private static String SYMBOL = "P";
	
	/**
	 * Construct new UCM ship at location
	 * @param r Row
	 * @param c Column
	 */
	public UcmShip(Game game, int r, int c)
	{
		super(game, r, c, HEALTH, SYMBOL);
	}
	
	/**
	 * Construct new UCM ship at default location 
	 */
	public UcmShip(Game game)
	{
		super(game, DEFAULT_ROW, DEFAULT_COL, HEALTH, SYMBOL);
	}
	
	
	public UcmShip() {
		this(null);
	}
	
	public static String getPlayerSymbol() {
		return SYMBOL;
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
	public void shoot(boolean superMissile) throws GameActionException
	{
		if(!missileAvailable) { throw new MissileInFlightException(); }
		
		UCMMissile missile;
		if(superMissile) {
			if(superMissileNum <= 0) { throw new NoSuperMissileException(); }
			superMissileNum--;			
			missileAvailable = false;
			missile = new SuperMissile(game, getRow(), getCol());
		} else {
			missile = new UCMMissile(game, getRow(), getCol());
			missileAvailable = false;
		}
		
		game.addObject(missile);
		
	}
	
	public boolean hasShockWave() {
		return shockWaveAvailable;
	}
	
	public void enableShockWave() {
		shockWaveAvailable = true;
	}
	
	public void useShockWave() throws GameActionException {
		if(!hasShockWave()) {
			throw new NoShockWaveException();
		}
		
		game.addObject(new ShockWave(game, -1, -1));
		shockWaveAvailable = false;	
	}
	
	/**
	 * Enables missile so it can be shot again
	 */
	public void enableMissile()
	{
		missileAvailable = true;
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

	
	public int getSuperMissileNum() {
		return superMissileNum;
	}
	
	@Override
	public String serialize() {
		return super.serialize() + ";" + score + ";" + shockWaveAvailable + ";" + getSuperMissileNum();
	}
	
	public void receiveScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return score;
	}

	public void buySuperMissile(int cost) throws GameActionException {
		if(cost > score) {
			throw new NotEnoughScoreException(); 
		}
		
		score -= cost;
		superMissileNum++;
	}
	
	@Override
	public 	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		if(super.parse(string, game, verifier) == null) { return null; }
		if(!verifier.verifyPlayerString(string, game, HEALTH)) { throw new FileContentsException("Invalid player serialization"); }
		
		//Load data
		
		String[] words = string.split(verifier.getReadSeparator1());

		setHealth(Integer.parseInt(words[2]));
		score = Integer.parseInt(words[3]);
		shockWaveAvailable = words[4].equals("true");
		superMissileNum = Integer.parseInt(words[5]);
		
		return this;
	}

	public void disableMissile() {
		missileAvailable = false;
	}
}
