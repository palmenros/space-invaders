package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

/**
 * Class that represents a missile thrown by the UCM-Ship
 * @author Martín Gómez y Pedro Palacios
 */
public class UCMMissile extends Weapon {

	/**
	 * Default harm that the missile will do to enemies
	 */
	public static final int DEFAULT_HARM = 1;
	
	private static final String SYMBOL = "M";

	/**
	 * Construct a new missile at position with default harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm which the missile produces
	 */
	public UCMMissile(Game game, int r, int c)
	{
		this(game, r, c, DEFAULT_HARM, SYMBOL);
	}
	
	/**
	 * Construct a new missile at position with given harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm
	 */
	public UCMMissile(Game game, int r, int c, int harm, String symbol)
	{
		super(game, r, c, harm, symbol);
	}

	public UCMMissile() {
		this(null, -1, -1);
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
	@Override
	public void update()
	{
		move(-1,0);
	}
	
	@Override
	public boolean performAttack(GameObject other)
	{
		boolean result = isAt(other) && other.receiveMissileAttack(getHarm());
		if(result) {
			kill();
		}
		
		return result;
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
	
	/**
	 * Called on destroy
	 */
	@Override
	public void destroy()
	{
		game.enableMissile();
		super.destroy();
	}
	
	private static UCMMissile createInstance(Game game, int r, int c, int label) {
		UCMMissile missile = new UCMMissile(game, r, c);
		missile.setLabel(label);
		return missile;
	}
	
	@Override
	public 	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		if(super.parse(string, game, verifier) == null) { return null; }
	
		string = string.split(labelRefSeparator)[0];
		if(!verifier.verifyWeaponString(string, game)) { throw new FileContentsException("Invalid missile serialization"); }
		
		//Load data		
		return createInstance(game, getRow(), getCol(), label);
	}
		
}
