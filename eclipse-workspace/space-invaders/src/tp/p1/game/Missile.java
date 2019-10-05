package tp.p1.game;

public class Missile extends GameObject {

	public static final int DEFAULT_HARM = 1;
	
	private int harm;
	
	public Missile(int r, int c)
	{
		this(r, c, DEFAULT_HARM);
	}
	
	public Missile(int r, int c, int harm)
	{
		super(r, c);
		this.harm = harm;
	}

	public String toString()
	{
		return "oo";
	}
	
	public int getHarm()
	{
		return harm;
	}
}
