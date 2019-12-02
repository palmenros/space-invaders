package tp.p2.game;

/**
 * Enum that represents a direction: left or right
 * @author Martín Gómez y Pedro Palacios
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
	private Direction(int deltaCol)
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
