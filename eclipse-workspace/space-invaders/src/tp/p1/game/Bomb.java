package tp.p1.game;

public class Bomb extends GameObject {

	private DestroyerShip owner;
	
	public Bomb(int r, int c, DestroyerShip owner)
	{
		super(r, c);
		this.owner = owner;
	}
	
	public String toString()
	{
		return ".";
	}
	
	public void destroy() {
		owner.resetBomb();
	}
	
	public int getHarm() {
		return owner.getHarm();
	}
}
