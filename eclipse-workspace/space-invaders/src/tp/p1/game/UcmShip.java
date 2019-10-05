package tp.p1.game;

public class UcmShip extends Ship {

	private static final int DEFAULT_ROW = 7;
	private static final int DEFAULT_COL = 4;
	private static final int HEALTH = 3;
	
	public UcmShip(int r, int c)
	{
		super(r, c, HEALTH);
	}
	
	public UcmShip()
	{
		super(DEFAULT_ROW, DEFAULT_COL, HEALTH);
	}
	
	public String toString()
	{
		return "^__^";
	}
	
	static public String getHelpMessage()
	{
		return "^__^: Harm: " + Missile.DEFAULT_HARM + " - Shield: " + HEALTH;
	}
	
}
