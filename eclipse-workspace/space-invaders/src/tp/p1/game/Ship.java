package tp.p1.game;

public class Ship extends GameObject {
	
	private int health;
	
	public Ship(int r, int c, int health)
	{
		super(r, c);
		this.health = health;
	}
	
	boolean damage(int damagePoints)
	{
		health -= damagePoints;
		return health > 0;
	}
	
	public int getHealth()
	{
		return health;
	}
}
