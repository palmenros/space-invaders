package tp.p1.game;

/**
 * GameObject board
 * @author Martín Gómez y Pedro Palacios
 */
public class GameObjectBoard {

	/**
	 * Array which represents the list
	 */
	private GameObject[] arr;
	
	/**
	 *  Initial size of reserved storage
	 */
	private static final int INITIAL_MAX_NUM = 5;
	
	/**
	 *  Number of actual stored objects in list
	 */
	private int num;
	
	/**
	 * Create a new list with INITIAL_MAX_NUM reserved slots
	 */
	public GameObjectBoard()
	{
		num = 0;
		arr = new GameObject[INITIAL_MAX_NUM];
	}
	
	/**
	 * Inserts an element at the end of the list
	 * If necessary, increments storage capacity
	 * @param el Element to be inserted
	 */
	public void add(GameObject el)
	{
		if (arr.length == num)
		{
			GameObject[] arr2 = new GameObject[2 * arr.length];
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
	 * Remove element at index
	 * @param i Index
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
	 *  Call computer action in every game object
	 */
	public void computerAction()
	{		
		for(int i = 0; i < num; i++) {
			arr[i].computerAction();
		}
	}
	
	/**
	 * Call update on every game object
	 */
	public void update() {
		
		for(int i = 0; i < num; i++) {
			arr[i].update();
		}	
		removeDead();
	}
	
	/**
	 * Remove all dead game objects
	 */
	private void removeDead() {
		int i = 0;
		while(i < num) {
			GameObject object = arr[i];
		
			if(!object.isAlive()){
				remove(i);
				i--;
			}
			i++;
		}

	}
	
	/**
	 * Returns object at position
	 * @param r Row
	 * @param c Column
	 * @return GameObject if found, null otherwise
	 */
	public GameObject getAt(int r, int c) {
		for(int i = 0; i < num; i++) {
			GameObject object = arr[i];
			if(object.isAt(r, c)) {
				return object;
			}
		}

		return null;
	}
	
	/**
	 * Get string representation of object at position
	 * @param r Row
	 * @param c Column
	 * @return String representation of object at position
	 */
	public String toString(int r, int c) {
		GameObject obj = getAt(r, c);
		if(obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}
}
