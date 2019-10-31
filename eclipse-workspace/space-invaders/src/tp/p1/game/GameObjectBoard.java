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
		for(GameObject e : arr) {
			e.computerAction();
		}
	}
	
	/**
	 * Call update on every game object
	 */
	public void update() {
		int i = 0;
		while(i < num) {
			GameObject object = arr[i];
		
			if(!object.update()){
				remove(i);
				i--;
			}
			i++;
		}
	}
	
}
