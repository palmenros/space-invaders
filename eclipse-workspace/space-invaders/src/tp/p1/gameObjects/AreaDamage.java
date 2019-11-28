package tp.p1.gameObjects;

import tp.p1.game.Game;

public class AreaDamage extends Weapon {

	
	private boolean firstUpdate = true;
		
	/**
	 * Area damage with specific harm
	 * @param game Game
	 * @param r Row
	 * @param c Column
	 * @param harm Harm
	 */
	public AreaDamage(Game game, int r, int c, int harm)
	{
		
		super(game, r, c, harm, "");
	}
	
	/**
	 * The shockwave does not update, so this method will not be implemented
	 */
	@Override
	public void update()
	{
		if(firstUpdate) { 
			firstUpdate = false;
		} else { 
			kill(); 
		}
	}
	
	@Override
	public boolean receiveAreaAttack(int dmg) {
		return false;
	}

	@Override
	public boolean receiveShockWaveAttack(int dmg) {
		return false;
	}
	
	@Override
	public boolean performAttack(GameObject other)
	{
		boolean adjacent = 	   (Math.abs(other.getCol() - getCol()) <= 1) && 
							   (Math.abs(other.getRow() - getRow()) <= 1);
		
		if(!adjacent) { return false; }
		
		return other.receiveAreaAttack(getHarm());
	}

	@Override
	public String toString() {
		return "";
	}
}
