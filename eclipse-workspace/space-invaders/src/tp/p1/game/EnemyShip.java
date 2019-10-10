package tp.p1.game;

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
	public EnemyShip(int r, int c, int health, int score)
	{
		super(r, c, health);
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
	

}
