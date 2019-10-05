package tp.p1.game;

abstract public class EnemyShip extends Ship {

	private int score;
	
	public EnemyShip(int r, int c, int health, int score)
	{
		super(r, c, health);
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	static public String getHelpMessage(String name, int score, int health, int harm)
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
