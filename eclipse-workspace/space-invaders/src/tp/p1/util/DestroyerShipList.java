package tp.p1.util;

import java.util.Random;

import tp.p1.game.*;

/**
 * List of destroyer ships
 * @author Martín Gómez y Pedro Palacios
 */
public class DestroyerShipList
{
	
	/**
	 *  Initial size of reserved storage
	 */
	private static final int INITIAL_MAX_NUM = 5;
	
	/**
	 *  Number of actual stored objects in list
	 */
	private int num;
	
	/**
	 * Array which represents the list
	 */
	private DestroyerShip[] arr;

	/**
	 * Create a new list with INITIAL_MAX_NUM reserved slots
	 */
	public DestroyerShipList()
	{
		num = 0;
		arr = new DestroyerShip[INITIAL_MAX_NUM];
	}
	
	/**
	 * Returns list length
	 * @return length
	 */
	public int length() 
	{
		return num;
	}
	
	/**
	 * Is the list empty?
	 * @return true if list is empty, false otherwise
	 */
	public boolean empty()
	{
		return num == 0;
	}
	
	/**
	 * Inserts an element at the end of the list
	 * If necessary, increments storage capacity
	 * @param el Element to be inserted
	 */
	public void insert(DestroyerShip el)
	{
		if (arr.length == num)
		{
			DestroyerShip[] arr2 = new DestroyerShip[2 * arr.length];
			for(int i = 0; i<arr.length; i++)
			{
				arr2[i] = arr[i];
			}
			arr = arr2;
		}
	
		arr[num] = el;
		num++;
	}
	
	/**
	 * Removes element at index from list
	 * @param i Index to be removed
	 */
	private void remove(int i)
	{
		if (i>= 0 && i < num)
		{
			for (int j = i; j<num-1; j++)
			{
				arr[j] = arr[j+1];
			}
	
			num--;
		}
	}
	
	/**
	 * Retrieve ship at index
	 * @param i Index
	 * @return Ship at index i or null if out of bounds
	 */
	private DestroyerShip get(int i)
	{
		if (i>=0 && i<num)
		{
			return arr[i];
		}
		
		return null;
	}
	
	/**
	 *	Build string representation of list
	 */
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder("[");
		
		stringBuilder.append(num);
		stringBuilder.append("]{");
		
		if(num > 0)
		{
			stringBuilder.append(arr[0]);
		}
		
		for (int i = 1; i<num; i++)
		{
			stringBuilder.append(", ");
			stringBuilder.append(arr[i].toString());
		}
		
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
	
	/**
	 * Returns the element at position
	 * @param row	Row at which the element is
	 * @param column Column where the element is
	 * @return The element is found, null otherwise
	 */
	public DestroyerShip getAtPosition(int row, int column)
	{
		int index = getIndexAtPosition(row, column);
		if(index >= 0) {
			return arr[index];
		}
		return null;
	}
	
	/**
	 * Returns the index of the element at position
	 * @param row Row at which the element is
	 * @param column Column where the element is
	 * @return The index if the element is found, -1 otherwise
	 */
	private int getIndexAtPosition(int row, int column)
	{
		for(int i = 0; i < num; i++)
		{
			if(arr[i].isAt(row, column)) { return i; }
		} 
		return -1;
	}
	
	/**
	 * Damages ship and removes it if dead
	 * @param i		Index of ship to damage
	 * @param harm	Harm to inflict
	 * @return	Negative if alive, number of points if dead
	 */
	private int damage(int i, int harm)
	{
		if(i < 0 || i >= num) { return -1; }
		boolean alive = arr[i].damage(harm);
		
		if(!alive) {
			int score = arr[i].getScore();
			remove(i);
			return score;
		}
		
		return -1;
	}
	
	
	/**
	 * Execute computer action for destroyer ships
	 * 
	 * @param rand Random variable
	 * @param level Level which is being currently played
	 * @param bombList BombList where to shoot
	 */
	public void computerAction(Random rand, Level level, BombList bombList)
	{
		for(int i = 0; i < length(); i++) {
			if(rand.nextFloat() <= level.getFireRate()) {
				Bomb bomb = get(i).shoot();
				if(bomb != null) {
					bombList.insert(bomb);
				}
			}	
		}
	}
	
	/**
	 * Is any alien at column border?
	 * @param alienDirection Direction
	 * @return True if any alien is at column border
	 */
	public boolean isAnyAtColumnBorder(Direction alienDirection)
	{
		boolean isAlienAtBorder = false;
		int i = 0;
		while(!isAlienAtBorder && i < length())
		{
			isAlienAtBorder = get(i).isAtColumnBorder(alienDirection);
			i++;
		}
		return isAlienAtBorder;
	}
	
	/**
	 * Moves all aliens
	 * 
	 * @param dr Delta row
	 * @param dc Delta column
	 */
	public void moveAll(int dr, int dc)
	{
		for(int i = 0; i < length(); i++) {
			get(i).move(dr, dc);
		}
	}
	
	/**
	 * Is any alien at lowest row?
	 * @return True if any alien is at lowest row
	 */
	public boolean isAnyAtLowestRow()
	{
		boolean isAnyAlienLastRow = false;
		int i = 0;	
		while(!isAnyAlienLastRow && i < length()){
			isAnyAlienLastRow = get(i).isAtLowestRow();
			i++;
		}
		
		return isAnyAlienLastRow;
	}
	
	/**
	 * Damage alien at position
	 * @param r Row
	 * @param c Column
	 * @param harm Harm
	 * @return -2 if no alien was hurt, -1 if hurt but not killed, score if killed
	 */
	public int damageAtPosition(int r, int c, int harm)
	{
		int i = getIndexAtPosition(r, c);
		if(i >= 0) {
			return damage(i, harm);
		} else {
			return -2;
		}
	}
	
	/**
	 * Damage all aliens
	 * @param harm Harm
	 * @return Accumulated score of kills
	 */
	public int damageAll(int harm)
	{
		int scoreTotal = 0;

		int i = 0;
		while(i < length())	
		{
			int score = damage(i, harm);

			if(score > 0) {
				scoreTotal += score;
		 	}else{
				i++;
			}
		}
		
		return scoreTotal;
	}
	
	
}