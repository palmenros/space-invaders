package tp.p1.game;

public class EnemyShip extends Ship {

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
	
}
