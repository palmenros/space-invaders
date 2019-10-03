package tp.p1.game;

public class RegularShip extends EnemyShip {
	
	private static final int HEALTH = 2;
	private static final int SCORE = 5;
	
	public RegularShip(int r, int c)
	{
		super(r, c, HEALTH, SCORE);
	}
	
	public String toString()
	{
		return "C[" + getHealth() + "]";
	}
}