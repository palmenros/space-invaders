package tp.p1.game;

/**
 * @author Martín Gómez y Pedro Palacios
 *
 * Enum that represents a direction: left or right
 */
public enum Direction {
	LEFT(-1),
	RIGHT(1);
	
	/**
	 * Unitary difference of column 
	 */
	private int deltaCol;
		
	/**
	 * Create a Direction
	 * @param deltaCol Delta column
	 */
	Direction(int deltaCol)
	{
		this.deltaCol = deltaCol;
	}	
		
	/**
	 * Return unitary difference of column in case of move
	 * @return Delta column
	 */
	public int getDeltaCol()
	{
		return deltaCol;
	}
	
	/**
	 * Get opposite direction
	 * @return If left return right and vice versa
	 */
	public Direction getOppositeDirection()
	{
		switch(this){
		
		case LEFT:
			return RIGHT;
		case RIGHT:
		default:
			return LEFT;
		
		}
	}
}
