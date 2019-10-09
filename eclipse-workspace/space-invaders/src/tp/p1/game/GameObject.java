package tp.p1.game;

public class GameObject {

	private int r;
	private int c;

	public GameObject(int r, int c)
	{
		this.r = r;
		this.c = c;
	}

	protected int getRow() {
		return r;
	}

	protected void setRow(int r) {
		this.r = r;
	}

	protected int getCol() {
		return c;
	}

	protected void setCol(int c) {
		this.c = c;
	}
	
	public boolean isAt(int r, int c)
	{
		return getCol() == c && getRow() == r;
	}	
	
	/**
	 * Tries to move the object
	 * @param dr Delta of row to move
	 * @param dc Delta of column to move
	 * @return True if could move, false otherwise
	 */
	public boolean move(int dr, int dc)
	{
		if(!canMove(dr, dc)) {return false;}
		
		r += dr;
		c += dc;
		
		return true;
	}
	
	/**
	 * Returns if object can move
	 * @param dr Delta of row to move
	 * @param dc Delta of column to move
	 * @return True if can move, false otherwise
	 */
	public boolean canMove(int dr, int dc)
	{
		int newR = r + dr, newC = c + dc; 
		if ( newR < 0 || newR >= Game.ROW_NUM) { return false; }
		if ( newC < 0 || newC >= Game.COL_NUM) { return false; }
		
		return true;
	}
	
	public boolean isOutOfBounds()
	{
		if ( r < 0 || r >= Game.ROW_NUM) { return true; }
		if ( c < 0 || c >= Game.COL_NUM) { return true; }
		
		return false;
	}
	
	public boolean isAtColumnBorder(Direction direction)
	{
		return direction == Direction.LEFT && c == 0 || direction == Direction.RIGHT && c == Game.COL_NUM - 1;
	}
	
	public boolean isAtLowestRow()
	{
		return r == Game.ROW_NUM - 1;
	}
}
