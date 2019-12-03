package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Direction;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

/**
 * Destroyer ship
 * @author Martín Gómez y Pedro Palacios 
 */
public class DestroyerShip extends AlienShip implements IExecuteRandomActions {

	/**
	 * Count of destroyer ships
	 */
	private static int destroyerCount = 0;
	
	/**
	 * Default health of destroyer ship
	 */
	private static final int HEALTH = 1;

	/**
	 * Default harm of destroyer ship 
	 */
	private static final int HARM = 1;
	
	/**
	 * Default score points of destroyer ship
	 */
	private static final int SCORE = 10;
	
	/**
	 * Can shoot another bomb?
	 */
	private boolean canShoot = true;
	
	private static int currentSerialNumber;
	
	private static final String SYMBOL = "D";
	
	/**
	 * Create a new destroyer at row, column
	 * @param r Row
	 * @param c Column
	 */
	public DestroyerShip(Game game, int r, int c)
	{
		super(game, r, c, HEALTH, SCORE, SYMBOL);
		destroyerCount++;
	}
	
	public DestroyerShip() {
		this(null, -1, -1);
		alienShipCount--;
		destroyerCount--;
	}

	/**
	 * Get help message of destroyer 
	 * @return Help message
	 */
	static public String getHelpMessage()
	{
		return EnemyShip.constructHelpMessage("Destroyer ship", SCORE, HEALTH, HARM);
	}
	
	/**
	 * Reset value to be able to shoot again
	 */
	public void resetBomb()
	{
		canShoot = true;
	}
	
	/**
	 * Get destroyer harm
	 * @return Harm that destroyers inflict
	 */
	public static int getHarm()
	{
		return HARM;
	}
	
	@Override
	public void computerAction()
	{
		if(canShoot && IExecuteRandomActions.canGenerateRandomBomb(game))
		{
			canShoot = false;
			game.addObject(new Bomb(game, getRow(), getCol(), this));
		}
	}
	
	/**
	 * Update destroyer ship
	 */
	@Override
	public void update()
	{
		//Call parent update
		super.update();
	}
	
	/**
	 * Delete object
	 */
	public void destroy() {
		destroyerCount--;
		super.destroy();
	}
	
	/**
	 * Return count of destroyer ships that are still alive
	 * @return count of alive destroyer ships
	 */
	public static int getDestroyerShipCount() {
		return destroyerCount;
	}

	private void initialiseLabelling() {
		currentSerialNumber = 1;
	}

	private String generateSerializingLabel() {
		label = currentSerialNumber;
		currentSerialNumber++;
		return labelRefSeparator + label;
	}
	
	@Override
	public String serialize() {
		if (!game.isSerializing()) {
			game.setSerializing();
			initialiseLabelling();
		}
		return super.serialize() + generateSerializingLabel();
	}

	public boolean isOwner(int ref) {
		boolean itsMe = super.isOwner(ref);
		if (itsMe) { canShoot = false; }
		return itsMe;
	}

	@Override
	public 	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		if(super.parse(string, game, verifier) == null) { return null; }
	
		string = string.split(labelRefSeparator)[0];
		if(!verifier.verifyAlienShipString(string, game, HEALTH)) { throw new FileContentsException("Invalid player serialization"); }
		
		//Load data
		String[] words = string.split(verifier.getReadSeparator1());
		
		DestroyerShip destroyer = new DestroyerShip(game, getRow(), getCol());
		destroyer.setHealth(Integer.parseInt(words[2]));
		cyclesSinceLastMove = Integer.parseInt(words[3]);
		alienDirection = Direction.parse(words[4]);
		destroyer.setLabel(label);
		
		return destroyer;
	}
}
