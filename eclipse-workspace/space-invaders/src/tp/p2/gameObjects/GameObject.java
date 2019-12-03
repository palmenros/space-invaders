package tp.p2.gameObjects;

import tp.p2.exceptions.FileContentsException;
import tp.p2.game.Direction;
import tp.p2.game.Game;
import tp.p2.input.FileContentsVerifier;

/**
 * Abstract class that represents a game object on the board
 * @author Martín Gómez y Pedro Palacios
 */
public abstract class GameObject implements IAttack {

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
	 *  Health of the object. Always greater or equals to 0.
	 */
	private int health;

	private String symbol;

	public static final String labelRefSeparator = " - ";

	protected int label = 0;
	
	/**
	 * Create new GameObject at position
	 * @param r Row
	 * @param c Column
	 */
	public GameObject(Game game, int r, int c)
	{
		this(game,r , c, 1, "");
	}

	/**
	 * Create new GameObject with health
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param health Health
	 */
	public GameObject(Game game, int r, int c, int health)
	{
		this(game, r, c, health, "");
	}
	
	/**
	 * Create new GameObject with health
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param health Health
	 * @param symbol Symbol
	 */
	public GameObject(Game game, int r, int c, int health, String symbol)
	{
		this.game = game;
		this.r = r;
		this.c = c;
		this.health = Math.max(0, health);
		this.symbol = symbol;
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
	 * Is object at the same position of other object
	 * @param object Object to compare with
	 * @return True if object is at given position, false otherwise
	 */
	public boolean isAt(GameObject object)
	{
		return getCol() == object.getCol() && getRow() == object.getRow();
	}
		
	/**
	 * Tries to move the object
	 * @param dr Delta of row to move
	 * @param dc Delta of column to move
	 * @return True if could move, false otherwise
	 */
	public void move(int dr, int dc)
	{
		if(!canMove(dr, dc)) { kill(); }
		
		r += dr;
		c += dc;
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
	
		return !Game.isOutOfBounds(newR, newC);
	}
	
	/**
	 * Is object out of bounds?
	 * @return True if out of bounds, false otherwise
	 */
	public boolean isOutOfBounds()
	{
		return Game.isOutOfBounds(r, c);
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
	 */
	public abstract void update();

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
	
	/**
	 * Damage the ship
	 * @param damagePoints Health to remove
	 * @return True if the ship is still alive, false otherwise
	 */
	public boolean damage(int damagePoints)
	{
		health = Math.max(0, health - damagePoints);
		return health > 0;
	}
	
	/**
	 * Get health
	 * @return Health
	 */
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	/**
	 * @return True if alive
	 */
	public boolean isAlive()
	{
		return health > 0;
	}
	
	/**
	 * Kill object 
	 */
	public void kill()
	{
		health = 0;
	}
	
	public boolean receiveAreaAttack(int dmg) {
		damage(dmg);
		return true;
	}
	
	public String serialize() {
		if(symbol.equals("")){
			return "";
		}
		
		return String.format("%s;%d,%d", symbol, getCol(), getRow());
	}
	
	protected String getSymbol() {
		return symbol;
	}
	
	public int getLabel() {
		return label;
	}
	
	public boolean isOwner(int ref) {
		return label == ref;
	}
	
	GameObject parse(String string, Game game, FileContentsVerifier verifier) throws FileContentsException, NumberFormatException {
		
		//Handle labels
		String[] arr = string.split(labelRefSeparator);
		if(arr.length > 1) {
			label = Integer.parseInt(arr[1]);
			string = arr[0];
		} else {
			label = 0;
		}
		
		String[] words = string.split(verifier.getReadSeparator1());
		if(words[0].equals(symbol)) {	
			this.game = game;
			
			//x = c = coords[0], y = r = coords[1]
			String[] coords = words[1].split (verifier.getReadSeparator2());
			c = Integer.parseInt(coords[0]);
			r = Integer.parseInt(coords[1]);	
			return this;
		} else {
			return null;
		}
	}
	
	protected void setLabel(int newLabel) {
		label = newLabel;
	}
}
