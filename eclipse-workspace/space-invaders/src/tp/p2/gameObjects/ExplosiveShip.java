//TODO: Review Area Damage

package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Direction;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

public class ExplosiveShip extends AlienShip {
	
	private static final int HARM_ON_DEATH = 1;
		
	/**
	 * Count of regular ships
	 */
	private static int explosiveCount = 0;
	
	/**
	 * Default health
	 */
	protected static final int HEALTH = 2;
	
	/**
	 * Default score
	 */
	protected static final int SCORE = 5;
	
	private static final String SYMBOL = "E";
	
	/**
	 * Create regular ship at location
	 * @param r Row
	 * @param c Column
	 */
	public ExplosiveShip(Game game, int r, int c, int health, int score)
	{
		super(game, r, c, health, score, SYMBOL);
		explosiveCount++;
	}
	
	public ExplosiveShip() {
		this(null, -1, -1, HEALTH, SCORE);
		alienShipCount--;
		explosiveCount--;
	}

	/**
	 * Return help message
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return EnemyShip.constructHelpMessage("Explosive ship", SCORE, HEALTH, HARM_ON_DEATH);
	}
	
	@Override
	public boolean receiveMissileAttack(int dmg) {
		boolean res = super.receiveMissileAttack(dmg);
		if(!isAlive()) { damageArea(); }
		
		return res;
	}

	@Override
	public boolean receiveShockWaveAttack(int dmg) {
		boolean res = super.receiveShockWaveAttack(dmg);
		if(!isAlive()) { damageArea(); }
		
		return res;
	}
	
	@Override
	public boolean receiveAreaAttack(int dmg) {
		boolean res = super.receiveAreaAttack(dmg);
		if(!isAlive()) { damageArea(); }
		
		return res;
	}

	/**
	 * Delete object
	 */
	public void damageArea() {
		game.addObject(new AreaDamage(game, getRow(), getCol(), HARM_ON_DEATH));
	}
	
	/**
	 * Delete object
	 */
	public void destroy() {
		explosiveCount--;
		super.destroy();
	}
	
	/**
	 * Return count of regular ships that are still alive
	 * @return count of alive regular ships
	 */
	public static int getExplosiveShipCount() {
		return explosiveCount;
	}
	
	@Override
	public 	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		if(super.parse(string, game, verifier) == null) { return null; }
	
		string = string.split(labelRefSeparator)[0];
		if(!verifier.verifyAlienShipString(string, game, HEALTH)) { throw new FileContentsException("Invalid player serialization"); }
		
		//Load data
		String[] words = string.split(verifier.getReadSeparator1());
		
		ExplosiveShip explosive = new ExplosiveShip(game, getRow(), getCol(), HEALTH, SCORE);
		explosive.setHealth(Integer.parseInt(words[2]));
		cyclesSinceLastMove = Integer.parseInt(words[3]);
		alienDirection = Direction.parse(words[4]);
		explosive.setLabel(label);
		
		return explosive;
	}
}
