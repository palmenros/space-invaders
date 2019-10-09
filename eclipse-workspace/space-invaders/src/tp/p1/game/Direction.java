package tp.p1.game;

public enum Direction {
	LEFT(-1),
	RIGHT(1);
	
	private int deltaCol;
	
	Direction(int deltaCol)
	{
		this.deltaCol = deltaCol;
	}	
	
	public int getDeltaCol()
	{
		return deltaCol;
	}
	
	public Direction getOppositeDirection()
	{
		switch(this)
		{
		case LEFT:
			return RIGHT;
		case RIGHT:
		default:
			return LEFT;
		}
	}
}
