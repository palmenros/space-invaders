package tp.p1.game;

/**
 * Abstract class that represents a game object on the board
 * @author Martín Gómez y Pedro Palacios
 */
public abstract class GameObject {

	/**
	 * Row position at the board
	 */
	private int r;
	
	/**
	 * Column position at the board
	 */
	private int c;

	/**
	 * Game to which the object belongs
	 */
	protected Game game;
	
	/**
	 * Create new GameObject at position
	 * @param r Row
	 * @param c Column
	 */
	public GameObject(Game game, int r, int c)
	{
		this.game = game;
		this.r = r;
		this.c = c;
	}

	/**
	 * Get row
	 * @return Row
	 */
	protected int getRow() {
		return r;
	}

	
	/**
	 * Set row
	 * @param r Row
	 */
	protected void setRow(int r) {
		this.r = r;
	}

	/**
	 * Get column
	 * @return Column
	 */
	protected int getCol() {
		return c;
	}

	/**
	 * Set column
	 * @param c Column
	 */
	protected void setCol(int c) {
		this.c = c;
	}
	
	/**
	 * Is object at given position?
	 * @param r Row
	 * @param c Column
	 * @return True if object is at given position, false otherwise
	 */
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
	
	/**
	 * Is object out of bounds?
	 * @return True if out of bounds, false otherwise
	 */
	public boolean isOutOfBounds()
	{
		if ( r < 0 || r >= Game.ROW_NUM) { return true; }
		if ( c < 0 || c >= Game.COL_NUM) { return true; }
		
		return false;
	}
	
	/**
	 * Is object at column border considering direction?
	 * @param direction Direction
	 * @return True if object is at column border, false otherwise
	 */
	public boolean isAtColumnBorder(Direction direction)
	{
		return direction == Direction.LEFT && c == 0 || direction == Direction.RIGHT && c == Game.COL_NUM - 1;
	}
	
	/**
	 * Is object at lowest row?
	 * @return True if is at lowest row, false otherwise
	 */
	public boolean isAtLowestRow()
	{
		return r == Game.ROW_NUM - 1;
	}
	
	/**
	 * Update this GameObject
	 * @return True if object should continue existing next cycle, false otherwise
	 */
	public abstract boolean update();

	/**
	 * Computer action
	 */
	public void computerAction()
	{
		//TODO: Implement in child classes
	}
		
	/**
	 *  Called on object destruction
	 */
	public void destroy()
	{
		//TODO: Implement custom destruction logic
	}
	
}
