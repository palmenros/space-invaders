package tp.p1.game;

public class DestroyerShip extends EnemyShip {

	private static final int HEALTH = 1;
	private static final int HARM = 1;
	private static final int SCORE = 10;
	
	public DestroyerShip(int r, int c)
	{
		super(r, c, HEALTH, SCORE);
	}
	
	public String toString()
	{
		return "D[" + getHealth() + "]";
	}
	
	static public String getHelpMessage()
	{
		return EnemyShip.getHelpMessage("Destroyer ship", SCORE, HEALTH, HARM);
	}
}
