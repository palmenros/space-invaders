package tp.p2.gameObjects;

import tp.p2.game.Game;

/**
 * Abstract class that represents an enemy ship
 * @author Martín Gómez y Pedro Palacios
 */
abstract public class EnemyShip extends Ship {

	/**
	 * Score that is given to the player when the ship is destroyed 
	 */
	private int score;
	
	/**
	 * Create a new enemy ship at given position
	 * @param r Row
	 * @param c Column
	 * @param health Health
	 * @param score Score
	 */
	public EnemyShip(Game game, int r, int c, int health, int score, String symbol)
	{
		super(game, r, c, health, symbol);
		this.score = score;
	}
	
	/**
	 * Get score
	 * @return score
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Returns a summary of the ship details
	 * @param name Ship name
	 * @param score Score given to the user
	 * @param health Health
	 * @param harm Harm that the ship inflicts
	 * @return String with constructed summary
	 */
	static protected String constructHelpMessage(String name, int score, int health, int harm)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		stringBuilder.append(name.substring(0, 1));
		stringBuilder.append("]");
		stringBuilder.append(name.substring(1));
		stringBuilder.append(": Points: " + score + " - Harm: " + harm + " - Shield: " + health);
		
		return stringBuilder.toString();
	}
	
	/**
	 *	Receive missile attack
	 *  @param dmg Damage to receive
	 *  @return True if affected
	 */
	@Override
	public boolean receiveMissileAttack(int dmg) {
		damage(dmg);
		return true;
	}
	
	/**
	 *	Receive ShockWave attack
	 *  @param dmg Damage to receive
	 *  @return True if affected
	 */
	@Override
	public boolean receiveShockWaveAttack(int dmg) {
		damage(dmg);		
		return true;
	}
	
	@Override
	public boolean damage(int dmg)
	{
		boolean result = super.damage(dmg);
		if(! isAlive() ) {
			game.receivePoints(score);			
		}
		return result;
	}
	
	/**
	 * Called on destroy
	 */
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	public String toString()
	{
		return getSymbol() + "[" + getHealth() + "]"; 
	}

	
}
