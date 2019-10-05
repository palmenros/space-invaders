package tp.p1.util;

import tp.p1.game.*;

/**
 * @author Mart�n G�mez y Pedro Palacios
 * 
 * List of destroyer ships
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
	public void remove(int i)
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
	public DestroyerShip get(int i)
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
	public int getIndexAtPosition(int row, int column)
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
	public int damage(int i, int harm)
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
}