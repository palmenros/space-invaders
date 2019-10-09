package tp.p1.game;

public class DestroyerShip extends EnemyShip {

	private static final int HEALTH = 1;
	private static final int HARM = 1;
	private static final int SCORE = 10;
	
	private boolean canShoot = true;
	
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
	
	public Bomb shoot()
	{
		if(!canShoot) {
			return null;
		}
		canShoot = false;
		return new Bomb(getRow(), getCol(), this);
	}

	public void resetBomb()
	{
		canShoot = true;
	}
	
	public int getHarm()
	{
		return HARM;
	}
}
