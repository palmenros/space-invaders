package tp.p1.util;

import tp.p1.game.*;

/**
 * @author Martín Gómez y Pedro Palacios
 * 
 * List of regular ships
 */
public class RegularShipList
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
	private RegularShip[] arr;

	/**
	 * Create a new list with INITIAL_MAX_NUM reserved slots
	 */
	public RegularShipList()
	{
		num = 0;
		arr = new RegularShip[INITIAL_MAX_NUM];
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
	public void insert(RegularShip el)
	{
		if (arr.length == num)
		{
			RegularShip[] arr2 = new RegularShip[2 * arr.length];
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
	public RegularShip get(int i)
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
	
}