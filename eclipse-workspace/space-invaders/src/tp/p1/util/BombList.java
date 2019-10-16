package tp.p1.util;

import tp.p1.game.*;

/**
 * List of bombs
 * @author Martín Gómez y Pedro Palacios
 */
public class BombList
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
	private Bomb[] arr;

	/**
	 * Create a new list with INITIAL_MAX_NUM reserved slots
	 */
	public BombList()
	{
		num = 0;
		arr = new Bomb[INITIAL_MAX_NUM];
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
	public void insert(Bomb el)
	{
		if (arr.length == num)
		{
			Bomb[] arr2 = new Bomb[2 * arr.length];
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
			arr[i].destroy();
			for (int j = i; j<num-1; j++)
			{
				arr[j] = arr[j+1];
			}
	
			num--;
		}
	}
	
	/**
	 * Retrieve bomb at index
	 * @param i Index
	 * @return Bomb at index i or null if out of bounds
	 */
	private Bomb get(int i)
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
	public Bomb getAtPosition(int row, int column)
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
	 * Update list of bombs
	 */
	public void update()
	{
		int i = 0;
		while(i < length()) {
			Bomb bomb = get(i);
		
			//Call update on bombs
			if(!bomb.update()){
				remove(i);
				i--;
			}
			i++;
		}
	}
	
	/**
	 * Damage bomb at position
	 * 
	 * @param r Row
	 * @param c Column
	 * @return true if hit bomb, false otherwise
	 */
	public boolean damageBombAtPosition(int r, int c)
	{
		int bomb = getIndexAtPosition(r, c);
		
		if(bomb >= 0) {
			remove(bomb);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove all bombs when callback called is true
	 * @param callback Callback to call for every bomb to determine if is removed
	 */
	public void removeIf(BombCallback callback)
	{
		int i = 0;
		while(i < length()) {
			Bomb bomb = get(i);
		
			if(callback.shouldRemove(bomb)){
				remove(i);
				i--;
			}
			i++;
		}
	}
}