package tp.p2.gameObjects;
import tp.p2.game.Game;

public class Wall extends GameObject {

	public Wall(Game game, int r, int c)
	{
		super(game,r,c,1,"W");
	}
	@Override
	public void update() {
		
	}
	@Override
	public boolean damage(int damagePoints)
	{
		return true;
	}
	public boolean performAttack(GameObject other)
	{
		if (isAt(other))
		{
		other.kill();
		}
		
		return false;
	}
	public String toString()
	{
		return "[WA]";
	}
	

}
